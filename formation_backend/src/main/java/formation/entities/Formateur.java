package formation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity

public class Formateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formateurId;

    private String formateurName;

    private String formateurLastname;

    @Email
    private String email;

    private Integer tel;

    @ManyToOne(cascade=CascadeType.ALL) 
    private Organisme organisme;
    
    
    @OneToMany(mappedBy="formateur")
    @JsonIgnore
    private List<SessionFormation> sessionsFormateurs=new ArrayList<SessionFormation>();
    
    private String type;

	public Long getFormateurId() {
		return formateurId;
	}

	public void setFormateurId(Long formateurId) {
		this.formateurId = formateurId;
	}

	public String getFormateurName() {
		return formateurName;
	}

	public void setFormateurName(String formateurName) {
		this.formateurName = formateurName;
	}

	public String getFormateurLastname() {
		return formateurLastname;
	}

	public void setFormateurLastname(String formateurLastname) {
		this.formateurLastname = formateurLastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTel() {
		return tel;
	}

	public void setTel(Integer tel) {
		this.tel = tel;
	}

	public Organisme getOrganisme() {
		return organisme;
	}

	public void setOrganisme(Organisme org) {
		this.organisme = org;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	


   
	

	public List<SessionFormation> getSessionsFormateurs() {
		return sessionsFormateurs;
	}

	public void setSessionsFormateurs(List<SessionFormation> sessionsFormateurs) {
		this.sessionsFormateurs = sessionsFormateurs;
	}



	public Formateur(Long formateurId, String formateurName, String formateurLastname, @Email String email, Integer tel,
			Organisme org, List<SessionFormation> sessionsFormateurs, String type) {
		super();
		this.formateurId = formateurId;
		this.formateurName = formateurName;
		this.formateurLastname = formateurLastname;
		this.email = email;
		this.tel = tel;
		this.organisme = org;
		this.sessionsFormateurs = sessionsFormateurs;
		this.type = type;
	}

	public Formateur() {
		super();
	}
    
    
}
