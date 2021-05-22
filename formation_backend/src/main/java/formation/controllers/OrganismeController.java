package formation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionRepositoryUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formation.entities.Formateur;
import formation.entities.Organisme;
import formation.entities.Participant;
import formation.entities.SessionFormation;
import formation.repositories.FormateurRepository;
import formation.repositories.OrganismeRepository;
import formation.repositories.ParticipantRepository;
import formation.repositories.SessionFormationRepository;

@RestController
@RequestMapping("/organismes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrganismeController {
    
    @Autowired
    private OrganismeRepository organismeRepository;
    
    @Autowired
    private SessionFormationRepository sessionRepository;
    
    @Autowired
    private FormateurRepository formateurRepository; 
    
    @Autowired
    private ParticipantRepository participantRepository; 
    
    
    
    @GetMapping(value = "/All/")
    public List<Organisme> getorganismes(){
        return organismeRepository.findAll();
    }

    @GetMapping(value = "/ById/{id}")
    public Organisme getAnorganisme(@PathVariable Long id){
        return organismeRepository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/newOrg/")
    public ResponseEntity<String> addOrganisme(@RequestBody Organisme organisme){
        organismeRepository.save(organisme);
        return new ResponseEntity<>("Organisme added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAll/")
    public ResponseEntity<String> deleteallorganismes(){
        organismeRepository.deleteAll();
        return new ResponseEntity<>("All organismes deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteByID/{id}")
    public ResponseEntity<String> deleteanorganisme(@PathVariable Long id){
    	List<Participant> participants=participantRepository.findAll();
    	List<Formateur> formateurs=formateurRepository.findAll();
    	List<SessionFormation > sessions=sessionRepository.findAll();
    	
    	for (Formateur formateur : formateurs) {
			if(formateur.getOrganisme()!=null)
				if(formateur.getOrganisme().getOrgId()==id)
					formateur.setOrganisme(null);
		}
    	for (Participant participant : participants) {
			if(participant.getOrganisme()!=null)
				if(participant.getOrganisme().getOrgId()==id)
					participant.setOrganisme(null);
		}
    	for (SessionFormation sessionFormation : sessions) {
			if(sessionFormation.getOrganisme()!=null)
				if(sessionFormation.getOrganisme().getOrgId()==id)
					sessionFormation.setOrganisme(null);
		}
    	
    	organismeRepository.deleteById(id);
        return new ResponseEntity<>("Organisme deleted successfully", HttpStatus.OK);
    }

}
