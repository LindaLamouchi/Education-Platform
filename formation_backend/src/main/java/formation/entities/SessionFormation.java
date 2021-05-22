package formation.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity

public class SessionFormation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;
    
    @ManyToOne(cascade=CascadeType.ALL) 
    private Organisme organisme;

    private String lieu;

    @ManyToOne(cascade=CascadeType.ALL) 
    private Formateur formateur;

    @ManyToOne(cascade=CascadeType.ALL) 
    private Formation formation;

    @ManyToMany(mappedBy = "sessions")
    private List<Participant> participants;

    private String dateDebut;

    private String dateFin;

    private Integer nbParticipants;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Organisme getOrganisme() {
		return organisme;
	}

	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getNbParticipants() {
		return nbParticipants;
	}

	public void setNbParticipants(Integer nbParticipants) {
		this.nbParticipants = nbParticipants;
	}

	public SessionFormation(Long sessionId, Organisme organisme, String lieu, Formateur formateur, Formation formation,
			List<Participant> participants, String dateDebut, String dateFin, Integer nbParticipants) {
		super();
		this.sessionId = sessionId;
		this.organisme = organisme;
		this.lieu = lieu;
		this.formateur = formateur;
		this.formation = formation;
		this.participants = participants;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbParticipants = nbParticipants;
	}

	public SessionFormation() {
		super();
	}
    
    
}
