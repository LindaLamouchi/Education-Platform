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

import formation.entities.Formateur;
import formation.entities.Organisme;
import formation.repositories.FormateurRepository;
import formation.repositories.OrganismeRepository;

@RestController
@RequestMapping("/formateurs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FormateurController {
    
    @Autowired
    private FormateurRepository formateurRepository;
    @Autowired
    private OrganismeRepository organismeRepository;

    @GetMapping(value = "/All")
    public List<Formateur> getformateurs(){
        return formateurRepository.findAll();
    }

    @GetMapping(value = "/getById/{id}")
    public Formateur getAformateur(@PathVariable Long id){
        return formateurRepository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/newFormer/")
    public ResponseEntity<String> addFormateur(@RequestBody Formateur formateur){
        formateurRepository.save(formateur);
        return new ResponseEntity<>("Formateur added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAll/")
    public ResponseEntity<String> deleteallformateur(){
        formateurRepository.deleteAll();
        return new ResponseEntity<>("All formateurs deleted successfully", HttpStatus.OK);
    }

   /* @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteauformateur(@PathVariable Long id){
        formateurRepository.deleteById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }*/
    
    
    @DeleteMapping("/deleteFormer/{id}")
    public ResponseEntity<String> deleteaformateur(@PathVariable Long id){
        Optional<Formateur> f=formateurRepository.findById(id);
        f.get().setOrganisme(null);
        Formateur f2=f.get();
    	formateurRepository.deleteById(f2.getFormateurId());
    	return new ResponseEntity<>("Formateur deleted successfully", HttpStatus.OK);
}
    
    @PutMapping("/SetOrganismeFormateur/{id}")
    public Formateur updateFormateurO(@RequestBody Formateur formateur,@PathVariable Long id) {
       
      
        Optional<Organisme> org= organismeRepository.findById(id);
        Organisme organisme=org.get();
        formateur.setOrganisme(organisme);
        return formateurRepository.save(formateur);
    }
}
