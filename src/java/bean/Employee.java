package bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eas_employee")
@Named
@RequestScoped
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
        @RequestScoped
	private Long id;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
