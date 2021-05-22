package formation.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity

public class Profil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfil;

    private String libelle;
    
    @OneToOne(mappedBy = "profil")
    @JsonIgnore
    private Participant participant;
    
    
	public Long getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(Long idProfil) {
		this.idProfil = idProfil;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	

	public Profil(Long idProfil, String libelle, Participant participant) {
		super();
		this.idProfil = idProfil;
		this.libelle = libelle;
		this.participant = participant;
	}

	public Profil() {
		super();
	}
    
    
}
