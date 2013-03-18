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
@Table(name="eas_assignmentgroup")
@Named
@RequestScoped
public class Assignmentgroup implements Serializable {
    
        private static final long serialVersionUID = 1L;
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
	private Long id;
        
        @Column(name="assignmentgroup_name")
	private String name;
	
        @Column(name="assignmentgroup_function")
        private String function;
	
        @Column(name="assignmentgroup_comment")
        private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
