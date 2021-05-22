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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity

public class Formation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formationId;

    private String titre;

    private String type;

    private String annee;

    private Integer nbsession;
    
    @OneToMany(mappedBy="organisme")
    @JsonIgnore
    private List<SessionFormation> sessionsFormation=new ArrayList<SessionFormation>();
   
    
    @OneToOne(mappedBy = "formation")
    @JsonIgnore
    private ImageModel imageModel;
    
 

	@ManyToOne(cascade=CascadeType.ALL) 
    private Domaine domaine;

    private Double budget;

	public Long getFormationId() {
		return formationId;
	}

	public void setFormationId(Long formationId) {
		this.formationId = formationId;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public Integer getNbsession() {
		return nbsession;
	}

	public void setNbsession(Integer nbsession) {
		this.nbsession = nbsession;
	}

	public Domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}
     
	
	public List<SessionFormation> getSessionsFormation() {
		return sessionsFormation;
	}

	public void setSessionsFormation(List<SessionFormation> sessionsFormation) {
		this.sessionsFormation = sessionsFormation;
	}

  
	public Formation(Long formationId, String titre, String type, String annee, Integer nbsession,
			List<SessionFormation> sessionsFormation, Domaine domaine, Double budget) {
		super();
		this.formationId = formationId;
		this.titre = titre;
		this.type = type;
		this.annee = annee;
		this.nbsession = nbsession;
		this.sessionsFormation = sessionsFormation;
		this.domaine = domaine;
		this.budget = budget;
	}

	   public ImageModel getImageModel() {
			return imageModel;
		}

		public void setImageModel(ImageModel imageModel) {
			this.imageModel = imageModel;
		}
	public Formation() {
		super();
	}
    
    
}
