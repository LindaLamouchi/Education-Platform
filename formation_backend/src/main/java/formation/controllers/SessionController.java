package formation.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formation.entities.Formateur;
import formation.entities.Formation;
import formation.entities.Organisme;
import formation.entities.Participant;
import formation.entities.SessionFormation;
import formation.repositories.FormateurRepository;
import formation.repositories.FormationRepository;
import formation.repositories.OrganismeRepository;
import formation.repositories.ParticipantRepository;
import formation.repositories.SessionFormationRepository;

@RestController
@RequestMapping("/sessions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SessionController {
    
    @Autowired
    private SessionFormationRepository sessionFormationRepository;
    @Autowired
    private OrganismeRepository organismeRepository;
    @Autowired
    private FormateurRepository formateurRepository;
    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @GetMapping(value = "/all/{id}")
    public List<SessionFormation> getsessions(@PathVariable Long id){
    	List<SessionFormation> s= sessionFormationRepository.findAll();
    	List<SessionFormation> x = new ArrayList<SessionFormation>();
    	for (SessionFormation sessionFormation : s) {
			if(sessionFormation.getFormation()!=null)
				if(sessionFormation.getFormation().getFormationId()==id) {
					x.add(sessionFormation);
				}
		}
    	return x;
    	
    }
    
    @GetMapping(value = "/all")
    public List<SessionFormation> getsessios(){
    	List<SessionFormation> s= sessionFormationRepository.findAll();
    	
    	
    	return s;
    	
    }

    @GetMapping(value = "/ById/{id}")
    public SessionFormation getsession(@PathVariable Long id){
        return sessionFormationRepository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/new/")
    public ResponseEntity<String> addsession(@RequestBody SessionFormation session){
        sessionFormationRepository.save(session);
        return new ResponseEntity<>("Session added successfully", HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/add/{idorg}/{idPart}/{idF}/{idfrmtn}")
    public ResponseEntity<String> newsession(@RequestBody SessionFormation session, @PathVariable Long idorg,
    		@PathVariable Long idPart,@PathVariable Long idF,@PathVariable Long idfrmtn){
       
    	Organisme o=organismeRepository.findById(idorg).get();
    	Participant p=participantRepository.findById(idPart).get();
    	Formateur f=formateurRepository.findById(idF).get();
    	Formation form=formationRepository.findById(idfrmtn).get();
    	List<Participant> p2=new ArrayList<Participant>();
    	p2.add(p);
    	
    	session.setOrganisme(o);
    	session.setParticipants(p2);
    	session.setFormateur(f);
    	session.setFormation(form);
    	sessionFormationRepository.save(session);
        return new ResponseEntity<>("Session added successfully", HttpStatus.OK);
    }
    
    

    @DeleteMapping(value = "/deleteAll/")
    public ResponseEntity<String> deleteallsessions(){
        sessionFormationRepository.deleteAll();
        return new ResponseEntity<>("All sessions deleted successfully", HttpStatus.OK);
    }

   /* @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletesession(@PathVariable Long id){
        sessionFormationRepository.deleteById(id);
        return new ResponseEntity<>("Session deleted successfully", HttpStatus.OK);
    }*/
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSession(@PathVariable Long id){
        Optional<SessionFormation> s=sessionFormationRepository.findById(id);
        s.get().setOrganisme(null);
        s.get().setFormateur(null);
        s.get().setFormation(null);
        SessionFormation s2=s.get();
        sessionFormationRepository.deleteById(s2.getSessionId());
    	return new ResponseEntity<>("Session deleted successfully", HttpStatus.OK);}
    
    
    @PutMapping("/SetOrgSession/{id}")
    public SessionFormation updateSessionFormationOrg(@RequestBody SessionFormation session,@PathVariable Long id) {
       
      
        Optional<Organisme> org= organismeRepository.findById(id);        
        Organisme organisme=org.get();
        session.setOrganisme(organisme);
        return sessionFormationRepository.save(session);
    }
    
    @PutMapping("/SetFormteurSession/{id}")
    public SessionFormation updateSessionFormationFormateur(@RequestBody SessionFormation session,@PathVariable Long id) {
       
      
        Optional<Formateur> f= formateurRepository.findById(id);        
        Formateur formateur=f.get();
        session.setFormateur(formateur);
        return sessionFormationRepository.save(session);
    }
    
    @PutMapping("/SetFormtionSession/{id}")
    public SessionFormation updateSessionFormation(@RequestBody SessionFormation session,@PathVariable Long id) {
       
      
        Optional<Formation> f= formationRepository.findById(id);        
        Formation formation=f.get();
        session.setFormation(formation);
        return sessionFormationRepository.save(session);
    }
}
