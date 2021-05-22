package formation.controllers;

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

import formation.entities.Organisme;
import formation.entities.Participant;
import formation.entities.Pays;
import formation.entities.Profil;
import formation.repositories.OrganismeRepository;
import formation.repositories.ParticipantRepository;
import formation.repositories.PaysRepository;
import formation.repositories.ProfilRepository;


@RestController
@RequestMapping("/participants")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParticipantController {
    
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private OrganismeRepository organismeRepository;
    @Autowired
    private PaysRepository paysRepository;
    @Autowired
    private ProfilRepository profilRepository;

    @GetMapping(value = "/all/")
    public List<Participant> getparticipants(){
        return participantRepository.findAll();
    }

    @GetMapping(value = "/ById/{id}")
    public Participant getparticipant(@PathVariable Long id){
        return participantRepository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/add/")
    public ResponseEntity<String> addparticipant(@RequestBody Participant participant){
        participantRepository.save(participant);
        return new ResponseEntity<>("Participant added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAll/")
    public ResponseEntity<String> deleteallparticipants(){
        participantRepository.deleteAll();
        return new ResponseEntity<>("All participants deleted successfully", HttpStatus.OK);
    }

 /*   @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteparticipant(@PathVariable Long id){
        participantRepository.deleteById(id);
        return new ResponseEntity<>("Participant deleted successfully", HttpStatus.OK);
    }*/
    
    @DeleteMapping("/deleteOnePar/{id}")
    public ResponseEntity<String> deleteaParticipantOrg(@PathVariable Long id){
        Optional<Participant> p=participantRepository.findById(id);
        p.get().setOrganisme(null);
        p.get().setPays(null);
        Participant p2=p.get();
        participantRepository.deleteById(p2.getParticipanId());
    	return new ResponseEntity<>("Participant deleted successfully", HttpStatus.OK);

}
    @PutMapping("/addParticipant/{id}/{idPays}/{idProfil}")
    public Participant updateParticipantOrg(@RequestBody Participant participant,
    		@PathVariable Long id,
    		@PathVariable Long idPays
    		,@PathVariable Long idProfil) {
       
      
        Optional<Organisme> org= organismeRepository.findById(id);        
        Organisme organisme=org.get();
        participant.setOrganisme(organisme);
        
        Optional<Pays> pays= paysRepository.findById(idPays);        
        Pays p=pays.get();
        participant.setPays(p);
        
        Optional<Profil> profil= profilRepository.findById(idProfil);      
        
        Profil profile=profil.get();
        participant.setProfil(profile);
        
        return participantRepository.save(participant);
    }
    @PutMapping("/SetPaysParticipant/{id}")
    public Participant updateParticipantPays(@RequestBody Participant participant,@PathVariable Long id) {
       
      
       
        Optional<Pays> p= paysRepository.findById(id);
        
        Pays pays=p.get();
        participant.setPays(pays);
        return participantRepository.save(participant);
    }
    
    }
