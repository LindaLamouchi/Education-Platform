package formation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import formation.entities.Participant;
import formation.entities.Profil;
import formation.repositories.ParticipantRepository;
import formation.repositories.ProfilRepository;

@RestController
@RequestMapping("/profils")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfilController {
    
    @Autowired
    private ProfilRepository profilRepository;
    
    @Autowired
    private ParticipantRepository participantRepo;

    @GetMapping(value = "/all/")
    public List<Profil> getprofils(){
        return profilRepository.findAll();
    }

    @GetMapping(value = "/ById/{id}")
    public Profil getprofil(@PathVariable Long id){
        return profilRepository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/new/")
    public ResponseEntity<String> addprofil(@RequestBody Profil profil){
        profilRepository.save(profil);
        return new ResponseEntity<>("Profil added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/DeleteAll/")
    public ResponseEntity<String> deleteallprofils(){
        profilRepository.deleteAll();
        return new ResponseEntity<>("All profils deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteprofil(@PathVariable Long id){
        List<Participant> p=participantRepo.findAll();
        for (Participant participant : p) {
			if(participant.getProfil()!=null)
				if(participant.getProfil().getIdProfil()==id)
					participant.setProfil(null);
		}
    	profilRepository.deleteById(id);
        return new ResponseEntity<>("Profil deleted successfully", HttpStatus.OK);
    }


}
