package models;

public class Employee {

	private String name;
	private String workgroup;
	private String officialHandyNumber;
	private String privateHandyNumber;
	private String telephoneNumberAtHome;
	private String subsidiary;

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

	private EmployeeType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EmployeeType getType() {
		return type;
	}

	public void setType(EmployeeType type) {
		this.type = type;
	}

}
