package controller;

import bean.AssignmentGroup;
import bean.UploadType;
import dao.AssignmentGroupDAO;
import dao.HibernateUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.HibernateException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@SessionScoped
public class AssignmentgroupService implements Serializable {

    private static final long serialVersionUID = 1L;
    private AssignmentGroup assignGroup = new AssignmentGroup();
    @Inject
    private AssignmentGroupDAO assignDAO;
    private List<AssignmentGroup> groups;
    private UploadedFile file;

    public void startupList() {
        groups = assignDAO.getAllAssignmentGroups();
    }    

    public void addToDb() {
        try {
            assignGroup.setId(null);

            FacesContext context;

            context = FacesContext.getCurrentInstance();

            String info = "Assignment Group: " + assignGroup.getName() + " wurde erfolgreich hinzugefügt.";

            assignDAO.saveOrUpdateAssignment(assignGroup);
            updateList();

            context.addMessage("assignGroupMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, info, ""));
            
            handleFileUpload();

        } catch (HibernateException ex) {
            System.err.println(ex);
            HibernateUtil.rollbackTransaction();

            String info = "Es konnte keine neue Assignment Group hinzugefügt werden.\nBitte zeigen sie dies dem Admin: " + ex.toString();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("addInfo", new FacesMessage(FacesMessage.SEVERITY_FATAL, info, ""));
        }
    }
    
    private void updateList() {
        groups.clear();
        groups = assignDAO.getAllAssignmentGroups();
    }
    
    public void handleFileUpload() {
        assignGroup.setFilePath(file.getFileName());
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, assignGroup.getFilePath(), ""));
    }
    
    public List<AssignmentGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<AssignmentGroup> groups) {
        this.groups = groups;
    }

    public AssignmentGroup getAssignGroup() {
        return assignGroup;
    }

    public void setAssignGroup(AssignmentGroup assignGroup) {
        this.assignGroup = assignGroup;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    
}
