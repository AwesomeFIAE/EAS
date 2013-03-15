package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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

@Entity
@Table(name = "eas_employee")
@ManagedBean
@RequestScoped
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "label")
	private String type;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "workgroup")
	private String workgroup;

	@Column(name = "subsidiary")
	private String subsidiary;

	@Column(name = "official_handy_nr")
	private String officialHandyNumber;

	@Column(name = "private_handy_nr")
	private String privateHandyNumber;

	@Column(name = "nr_at_home")
	private String telephoneNumberAtHome;

	@Column(name = "surname")
	private String surname;

	@Transient
	private List<SelectItem> options;

	@Transient
	private static List<String> columns;

	@Transient
	private static List<Employee> employees;
	
	public Employee() {
		columns = new ArrayList<String>();
		columns.add("Bezeichnung");
		columns.add("Vorname");
		columns.add("Nachname");
		columns.add("Arbeitsgruppe");
		columns.add("Dienstl. Handynummer");
		columns.add("Private Handynummer");
		columns.add("Telefonnummer");
		columns.add("Nst");	
	}
	
	public void updateEmployeeList() {
		 EmployeeDAO empDAO = new EmployeeDAOImpl();
		 employees = empDAO.getAllEmployees();
		 System.out.println("hgdhgchg997");
	}
	
	public void addToDb() {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAOImpl();
			employeeDAO.addNewEmployee(this);
			employees.clear();
			employees = employeeDAO.getAllEmployees();
			
			String info = this.type + ": " + this.surname + ", "
					+ this.firstname + "\n wurde erfolgreich hinzugefügt.";
			
			FacesContext context = FacesContext.getCurrentInstance();
			
			context.addMessage("addInfo", new FacesMessage(
					FacesMessage.SEVERITY_INFO, info, ""));
		} catch (HibernateException ex) {
			System.err
					.println("Person konnte nicht gespeichert werden. [Class: EmployeeDAOImp]");
			ex.printStackTrace();
			HibernateUtil.rollbackTransaction();

			String info = "Es konnte keine neue Person hinzugefügt werden.";

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("addInfo", new FacesMessage(
					FacesMessage.SEVERITY_FATAL, info, ""));
		}
	}
	
	public List<SelectItem> getOptions() {
		this.options = new ArrayList<SelectItem>();

		for (int i = 0; i < EmployeeType.values().length; i++) {
			switch (EmployeeType.values()[i]) {
			case EMPLOYEE:
				this.options.add(new SelectItem("Mitarbeiter"));
				break;
			case CHIEF:
				this.options.add(new SelectItem("Chef"));
				break;
			case EXTERNAL_EMPLOYEE:
				this.options.add(new SelectItem("Externer Mitarbeiter"));
				break;
			case OTHER:
				this.options.add(new SelectItem("Sonstige"));
				break;
			}

		}

		return options;
	}

	public String getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(String workgroup) {
		this.workgroup = workgroup;
	}

	public String getOfficialHandyNumber() {
		return officialHandyNumber;
	}

	public void setOfficialHandyNumber(String officialHandyNumber) {
		this.officialHandyNumber = officialHandyNumber;
	}

	public String getPrivateHandyNumber() {
		return privateHandyNumber;
	}

	public void setPrivateHandyNumber(String privateHandyNumber) {
		this.privateHandyNumber = privateHandyNumber;
	}

	public String getTelephoneNumberAtHome() {
		return telephoneNumberAtHome;
	}

	public void setTelephoneNumberAtHome(String telephoneNumberAtHome) {
		this.telephoneNumberAtHome = telephoneNumberAtHome;
	}

	public String getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOptions(List<SelectItem> options) {
		this.options = options;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
