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

public class Domaine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long domaineId;

    private String libelle;
    
    @OneToMany(mappedBy="domaine")
    @JsonIgnore
    private List<Formation> formations=new ArrayList<Formation>();

	public Long getDomaineId() {
		return domaineId;
	}

	public void setDomaineId(Long domaineId) {
		this.domaineId = domaineId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	
	

	

	public Domaine(Long domaineId, String libelle, List<Formation> formations) {
		super();
		this.domaineId = domaineId;
		this.libelle = libelle;
		this.formations = formations;
	}


	public Domaine() {
		super();
	}
    
    
}
