
package controller;

import bean.Assignmentgroup;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AssignmentgroupService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Assignmentgroup assignGroup = new Assignmentgroup();

    public Assignmentgroup getAssignGroup() {
        return assignGroup;
    }

    public void setAssignGroup(Assignmentgroup assignGroup) {
        this.assignGroup = assignGroup;
    }
    
    
}
