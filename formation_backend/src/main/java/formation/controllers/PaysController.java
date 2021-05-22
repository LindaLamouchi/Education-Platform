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

import formation.entities.Pays;
import formation.repositories.PaysRepository;


@RestController
@RequestMapping("/pays")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaysController {
    
    @Autowired
    private PaysRepository paysRepository;

    @GetMapping(value = "/all/")
    public List<Pays> getallpays(){
        return paysRepository.findAll();
    }

    @GetMapping(value = "/byId/{id}")
    public Pays getpays(@PathVariable Long id){
        return paysRepository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/new/")
    public ResponseEntity<String> addpays(@RequestBody Pays pays){
        paysRepository.save(pays);
        return new ResponseEntity<>("Pays added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAll/")
    public ResponseEntity<String> deleteallpays(){
        paysRepository.deleteAll();
        return new ResponseEntity<>("All pays deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deletepays(@PathVariable Long id){
        paysRepository.deleteById(id);
        return new ResponseEntity<>("Pays deleted successfully", HttpStatus.OK);
    }

}
