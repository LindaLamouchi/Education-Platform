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

import formation.entities.Domaine;
import formation.entities.Formation;
import formation.repositories.DomaineRepository;
import formation.repositories.FormationRepository;

@RestController
@RequestMapping("/formations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FormationController {
    
    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private DomaineRepository DomaineRepository;

    @GetMapping(value = "/All/")
    public List<Formation> getformations(){
    	
        return formationRepository.findAll();
    }

    @GetMapping(value = "/ById/{id}")
    public Formation getAformation(@PathVariable Long id){
        return formationRepository.findById(id).orElseThrow();
    }
    
    @GetMapping("/getFormationsD/{id}")
    public List<Formation> getFormationD(@PathVariable Long id){
    	List<Formation> list=  formationRepository.findAll();
    	List<Formation> listf =new ArrayList<Formation>();
    	
    	for (Formation formation : list) {
			if(formation.getDomaine()!=null)
    		if(formation.getDomaine().getDomaineId()==id)
				listf.add(formation);
		}
    	
    	return listf;
    	
    }

    @PostMapping(value = "/addFormation/")
    public ResponseEntity<String> addformation(@RequestBody Formation formation){
        formationRepository.save(formation);
        return new ResponseEntity<>("Formation added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAll/")
    public ResponseEntity<String> deleteallformations(){
        formationRepository.deleteAll();
        return new ResponseEntity<>("All formations deleted successfully", HttpStatus.OK);
    }

  /*  @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteaformation(@PathVariable Long id){
        formationRepository.deleteById(id);
        return new ResponseEntity<>("Formation deleted successfully", HttpStatus.OK);
    }*/
    
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteaformation(@PathVariable Long id){
        Optional<Formation> f=formationRepository.findById(id);
        f.get().setDomaine(null);
        Formation f2=f.get();
    	formationRepository.deleteById(f2.getFormationId());
    	return new ResponseEntity<>("Formation deleted successfully", HttpStatus.OK);

}
    @PutMapping("/SetDomaineFormation/{id}")
    public Formation updateFormationD(@RequestBody Formation formation,@PathVariable Long id) {
       
      
        Optional<Domaine> D= DomaineRepository.findById(id);
        Domaine domaine=D.get();
        formation.setDomaine(domaine);
        return formationRepository.save(formation);
    }

}
