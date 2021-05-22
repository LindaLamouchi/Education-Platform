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

import formation.entities.Domaine;
import formation.entities.Formation;
import formation.repositories.DomaineRepository;
import formation.repositories.FormationRepository;

@RestController
@RequestMapping("/domaines")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DomaineController {
    
    @Autowired
    private DomaineRepository domaineRepository;
    
    @Autowired
    private FormationRepository formationRepository;

    
    @GetMapping(value = "/All")
    public List<Domaine> getdomaines(){
        return domaineRepository.findAll();
    }

    @GetMapping(value = "/ById/{id}")
    public Domaine getAdomaine(@PathVariable Long id){
        return domaineRepository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/aNewDomaine/")
    public ResponseEntity<String> adddomaine(@RequestBody Domaine domaine){
        domaineRepository.save(domaine);
        return new ResponseEntity<>("Domaine added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAll/")
    public ResponseEntity<String> deletealldomaines(){
        domaineRepository.deleteAll();
        return new ResponseEntity<>("All domaines deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<String> deleteadomaine(@PathVariable Long id){
    	List<Formation> l=formationRepository.findAll();
    	for (Formation formation : l) {
			if(formation.getDomaine()!=null)
    		if(formation.getDomaine().getDomaineId()==id)
				formation.setDomaine(null);
		}
        domaineRepository.deleteById(id);
        return new ResponseEntity<>("Domaine deleted successfully", HttpStatus.OK);
    }

}
