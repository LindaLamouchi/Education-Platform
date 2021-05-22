package formation.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity

public class Participant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participanId;

    private String type;

    @ManyToOne(cascade=CascadeType.ALL)
    private Organisme organisme;

    @ManyToOne(cascade=CascadeType.ALL)
    private Pays pays;
     

    @OneToOne
	@JoinColumn(name="idProfil",nullable = true)
	private Profil profil;

    @ManyToMany
    @JoinTable(
        name = "Participant_Session", 
        joinColumns = @JoinColumn(name = "sessionId"), 
        inverseJoinColumns = @JoinColumn(name = "participantId"))
    @JsonIgnore
    private List<SessionFormation> sessions;

    @Email
    private String email;

    private Integer tel;

	public Long getParticipanId() {
		return participanId;
	}

	public void setParticipanId(Long participanId) {
		this.participanId = participanId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Organisme getOrganisme() {
		return organisme;
	}

	public void setOrganisme(Organisme org) {
		this.organisme = org;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public List<SessionFormation> getSessions() {
		return sessions;
	}

	public void setSessions(List<SessionFormation> sessions) {
		this.sessions = sessions;
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

	public Participant(Long participanId, String type, Organisme org, Pays pays, Profil profil,
			List<SessionFormation> sessions, @Email String email, Integer tel) {
		super();
		this.participanId = participanId;
		this.type = type;
		this.organisme = org;
		this.pays = pays;
		this.profil = profil;
		this.sessions = sessions;
		this.email = email;
		this.tel = tel;
	}

	public Participant() {
		super();
	}
    
    


}
