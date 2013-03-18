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
@Table(name = "eas_server")
@Named
@RequestScoped
public class Server implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "server_name")
	private String serverName;

	@Column(name = "customer_ip")
	private String customerIP;

	@Column(name = "admin_ip")
	private String adminIP;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getCumstomerIP() {
		return customerIP;
	}

	public void setCumstomerIP(String cumstomerIP) {
		this.customerIP = cumstomerIP;
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
        

        public void setId(Long id) {
            this.id = id;
        }
}
