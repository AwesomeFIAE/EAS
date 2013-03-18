package controller;

import dao.HibernateUtil;
import dao.ServerDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import bean.Server;
import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;

@Named
@SessionScoped
public class ServerService implements Serializable {

    @Inject
    private ServerDAO serverDAO;
    private Server server = new Server();
    private List<String> columns;
    private List<Server> serverList;
    private List<SelectItem> options;
    private List<Server> dataTableList;
    private String chosenServer;

    public ServerService() {
        columns = new ArrayList<String>();
        columns.add("Servername");
        columns.add("Admin-IP");
        columns.add("Kunden-IP");

        serverList = new ArrayList<Server>();
    }

    public void updateList() {
        serverList.clear();
        serverList = serverDAO.getAllServer();
        dataTableList.clear();
        dataTableList = serverDAO.getAllServerForTable();
    }

    public void editServerInformation() {
        
        if(useDatabaseQuery()) {
            server = serverDAO.findServerByName(chosenServer);
        } else {
            setServerBeanByName(chosenServer);
        }

        RequestContext.getCurrentInstance().execute("editServerDialog.show()");
    }

    public void updateServer() {
        serverDAO.saveOrUpdateServer(server);
        updateList();
        clearServerBean();
    }

    public void deleteServer() {
        
        if(useDatabaseQuery()) {
            server = serverDAO.findServerByName(chosenServer);
        } else {
            setServerBeanByName(chosenServer);
        }

        serverDAO.deleteServer(server);
        updateList();
        clearServerBean();
    }

    public void addToDb() {
        try {
            server.setId(null);

            FacesContext context;

            String info = server.getServerName() + " wurde erfolgreich hinzugefügt.";

            serverDAO.saveOrUpdateServer(server);
            updateList();

            context = FacesContext.getCurrentInstance();

            context.addMessage("serverMessages", new FacesMessage(
                    FacesMessage.SEVERITY_INFO, info, ""));
        } catch (HibernateException ex) {
            HibernateUtil.rollbackTransaction();

            String info = "Es konnte kein neuer Server hinzugef�gt werden.\nBitte zeigen Sie dies dem Admin: " + ex.toString();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("addInfo", new FacesMessage(FacesMessage.SEVERITY_FATAL, info, ""));
        }

    }

    private void clearServerBean() {
        server.setAdminIP(null);
        server.setCumstomerIP(null);
        server.setId(null);
        server.setServerName(null);
    }

    private boolean useDatabaseQuery() {
        if(serverList.size() > 1000) {
            return true;
        }
        
        return false;
    }
    
    private void setServerBeanByName(String servername) {
        for (Server s : serverList) {
            if (s.getServerName().equals(servername)) {
                server.setAdminIP(s.getAdminIP());
                server.setCumstomerIP(s.getCumstomerIP());
                server.setId(s.getId());
                server.setServerName(s.getServerName());
                break;
            }
        }
    }

    public List<SelectItem> getOptions() {
        options = new ArrayList<SelectItem>();

        for (Server s : serverList) {
            options.add(new SelectItem(s.getServerName()));
        }

        return options;
    }

    public void startupList() {
        serverList = serverDAO.getAllServer();
        dataTableList = serverDAO.getAllServerForTable();
    }

    public List<Server> getDataTableList() {
        return dataTableList;
    }

    public void setDataTableList(List<Server> dataTableList) {
        this.dataTableList = dataTableList;
    }

    public ServerDAO getServerDAO() {
        return serverDAO;
    }

    public void setServerDAO(ServerDAO serverDAO) {
        this.serverDAO = serverDAO;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Server> getServerList() {
        return serverList;
    }

    public void setServerList(List<Server> serverList) {
        this.serverList = serverList;
    }

    public String getChosenServer() {
        return chosenServer;
    }

    public void setChosenServer(String chosenServer) {
        this.chosenServer = chosenServer;
    }

    public void setOptions(List<SelectItem> options) {
        this.options = options;
    }
}
