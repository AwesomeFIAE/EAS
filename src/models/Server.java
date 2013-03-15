package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.HibernateException;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import dao.HibernateUtil;
import dao.ServerDAO;
import dao.ServerDAOImpl;

@Entity
@Table(name = "eas_server")
@Named
@RequestScoped
public class Server implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "server_name")
	private String serverName;

	@Column(name = "customer_ip")
	private String cumstomerIP;

	@Column(name = "admin_ip")
	private String adminIP;

	@Transient
	private static List<String> columns;

	@Transient
	private static List<Server> serverList;

	static {
		columns = new ArrayList<String>();
		columns.add("Server Name");
		columns.add("Admin-IP");
		columns.add("Kunden-IP");

		 ServerDAO serverDAO = new ServerDAOImpl();
		 serverList = serverDAO.getAllServer();
	}

	public void addToDb() {
		try {
			ServerDAO serverDAO = new ServerDAOImpl();
			serverDAO.addNewServer(this);
			serverList.clear();
			serverList = serverDAO.getAllServer();

			String info = this.serverName + " wurde erfolgreich hinzugefügt.";
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage("serverMessages", new FacesMessage(
					FacesMessage.SEVERITY_INFO, info, ""));
		} catch (HibernateException ex) {
			System.err
					.println("Server konnte nicht gespeichert werden. [Class: ServerDAOImp]");
			ex.printStackTrace();
			HibernateUtil.rollbackTransaction();

			String info = "Es konnte kein neuer Server hinzugefügt werden.";

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("addInfo", new FacesMessage(
					FacesMessage.SEVERITY_FATAL, info, ""));
		}

	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getCumstomerIP() {
		return cumstomerIP;
	}

	public void setCumstomerIP(String cumstomerIP) {
		this.cumstomerIP = cumstomerIP;
	}

	public String getAdminIP() {
		return adminIP;
	}

	public void setAdminIP(String adminIP) {
		this.adminIP = adminIP;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		Server.columns = columns;
	}

	public List<Server> getServerList() {
		return serverList;
	}

	public void setServerList(List<Server> serverList) {
		Server.serverList = serverList;
	}

}
