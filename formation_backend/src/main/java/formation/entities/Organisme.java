package  formation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity

public class Organisme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orgId;

    private String libelle;
    
    
    @OneToMany(mappedBy="organisme")
    @JsonIgnore
    private List<Participant> participants=new ArrayList<Participant>();
    
    
    @OneToMany(mappedBy="organisme")
    @JsonIgnore
    private List<Formateur> formateurs=new ArrayList<Formateur>();
    
    @OneToMany(mappedBy="organisme")
    @JsonIgnore
    private List<SessionFormation > sessions=new ArrayList<SessionFormation>();
   

	public List<SessionFormation> getSessions() {
		return sessions;
	}

	public void setSessions(List<SessionFormation> sessions) {
		this.sessions = sessions;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	



	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}
	
	
    
	
	
 
	

	public Organisme(Long orgId, String libelle, List<Participant> participants, List<Formateur> formateurs,
			List<SessionFormation> sessions) {
		super();
		this.orgId = orgId;
		this.libelle = libelle;
		this.participants = participants;
		this.formateurs = formateurs;
		this.sessions = sessions;
	}

	public Organisme() {
		super();
	}
    
    
    
}
