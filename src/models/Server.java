package models;

public class Server {
	private String serverName;
	private String cumstomerIP;
	private String adminIP;

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

}
