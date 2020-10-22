package com.projet.stock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.projet.stock.exception.ResourceNotFoundException;
import com.projet.stock.model.Cposte;
import com.projet.stock.model.ConsSteg;
import com.projet.stock.model.LconsSteg;
import com.projet.stock.repository.CposteRepository;
import com.projet.stock.repository.ConsStegRepository;
import com.projet.stock.repository.LconsStegRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ConsStegController {
	@Autowired 	ConsStegRepository repository;
	@Autowired  LconsStegRepository repo;
    @Autowired CposteRepository comptrepo;
	@Autowired  ServletContext context;
	 @GetMapping("/stegs")
	  public List<ConsSteg> getAllConsStegs() {
	    System.out.println("Get all ConsStegs...");
	    List<ConsSteg> ConsStegs = new ArrayList<>();
	    repository.findAll().forEach(ConsStegs::add);
	    return ConsStegs;
	  }
	
	@GetMapping("/stegs/{id}")
	public ResponseEntity<ConsSteg> getConsStegById(@PathVariable(value = "id") Long ConsStegId)
			throws ResourceNotFoundException {
		ConsSteg ConsSteg = repository.findById(ConsStegId)
				.orElseThrow(() -> new ResourceNotFoundException("ConsSteg not found for this id :: " + ConsStegId));
		return ResponseEntity.ok().body(ConsSteg);
	}

	
	
	@PostMapping("/stegs")
	public ResponseEntity<ConsSteg> createConsSteg(@Valid @RequestBody ConsSteg ConsSteg)
     throws JsonParseException , JsonMappingException , Exception{
		repository.save(ConsSteg);
   	
		List<LconsSteg> lconsStegs = ConsSteg.getLconsStegs();
	    for (LconsSteg lc : lconsStegs) {
	    	
	        lc.setNumero(ConsSteg.getNumero());
	        lc.setAnnee(ConsSteg.getAnnee());
	        lc.setMois(ConsSteg.getMois());
	        System.out.println(lc.getQte());
	        System.out.println(lc.getCode_residence());
	        System.out.println(lc.getLib_residence());
       		repo.save(lc);
       	
	       }
	    Optional<Cposte> CposteInfo = comptrepo.findByAnnee(ConsSteg.getAnnee());
     	if (CposteInfo.isPresent()) {
	    	Cposte Cposte = CposteInfo.get();
	           Cposte.setNumcsteg(Cposte.getNumcsteg()+1);
	         Cposte =   comptrepo.save(Cposte);
     	}
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/stegs/{id}")
	public Map<String, Boolean> deleteConsSteg(@PathVariable(value = "id") Long ConsStegId)
			throws ResourceNotFoundException {
		ConsSteg ConsSteg = repository.findById(ConsStegId)
				.orElseThrow(() -> new ResourceNotFoundException("ConsSteg not found  id :: " + ConsStegId));
		repository.delete(ConsSteg);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/stegs/delete")
	  public ResponseEntity<String> deleteAllConsStegs() {
	    System.out.println("Delete All ConsStegs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All ConsStegs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/stegs/{id}")
	  public ResponseEntity<ConsSteg> updateConsSteg(@PathVariable("id") long id, @RequestBody ConsSteg ConsSteg) {
	    System.out.println("Update ConsSteg with ID = " + id + "...");
	    Optional<ConsSteg> ConsStegInfo = repository.findById(id);
	    if (ConsStegInfo.isPresent()) {
	    	ConsSteg consSteg = ConsStegInfo.get();
	    	consSteg.setLibelle(ConsSteg.getLibelle());
	      return new ResponseEntity<>(repository.save(ConsSteg), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
