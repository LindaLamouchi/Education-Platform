package formation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity

public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paysId;

    private String nom;
    
    @OneToMany(mappedBy="pays")
    @JsonIgnore
    private List<Participant> participants=new ArrayList<Participant>();

	public Long getPaysId() {
		return paysId;
	}

	public void setPaysId(Long paysId) {
		this.paysId = paysId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public List<Participant> getParticipants() {
		return participants;
	}


	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}


	public Pays(Long paysId, String nom, List<Participant> participants) {
		super();
		this.paysId = paysId;
		this.nom = nom;
		this.participants = participants;
	}
	public Pays() {
		super();
	}
    
    

}
