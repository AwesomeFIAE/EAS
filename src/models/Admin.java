package models;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;

import util.Util;


import dao.AdminDAO;
import dao.AdminDAOImpl;
import dao.HibernateUtil;


@Entity
@Table(name = "eas_admin")
@ManagedBean
@SessionScoped
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "username")
	private String username;

	@Column(name = "userPassword")
	private String password;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "surname")
	private String surname;

	@Column(name = "email")
	private String email;
	
	
	public void addToDb() {
		this.username = this.firstname + "." + this.surname;
		this.password = Util.MD5(this.password);
		
		try {
			AdminDAO adminDAO = new AdminDAOImpl();
			adminDAO.addNewAdmin(this);
			FacesContext.getCurrentInstance().addMessage("registerPanel", new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin wurde erfolgreich hinzugefügt", ""));
		} catch(HibernateException ex) {
			FacesContext.getCurrentInstance().addMessage("registerPanel", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Admin konnte nicht hinzugefügt werden.", ""));
			System.err.println("Admin konnte nicht gespeichert werden. [Class: AdminDAOImp]");
			ex.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}
	
	public void login() {
		try {
			AdminDAO adminDao = new AdminDAOImpl();
			Admin admin = adminDao.login(this.username, Util.MD5(this.password));
			this.setId(admin.getId());
			this.setEmail(admin.getEmail());
			this.setFirstname(admin.getFirstname());
			this.setSurname(admin.getSurname());
			
		} catch(HibernateException ex) {
			ex.printStackTrace();
		}
		catch(NullPointerException ex) {
			FacesContext.getCurrentInstance().addMessage("loginPanel", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Benutzername und/oder Passwort falsch", ""));
		}
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
