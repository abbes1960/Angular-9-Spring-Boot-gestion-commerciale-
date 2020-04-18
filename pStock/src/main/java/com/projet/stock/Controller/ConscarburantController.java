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
import com.projet.stock.model.ConsCarburant;
import com.projet.stock.model.Cposte;
import com.projet.stock.repository.CposteRepository;
import com.projet.stock.repository.ConsCarburantRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class ConscarburantController {
	@Autowired 	ConsCarburantRepository repository;
	@Autowired CposteRepository comptrepo;
	@Autowired  ServletContext context;
	
	 @GetMapping("/ConsCarburants")
	  public List<ConsCarburant> getAllConsCarburants() {
	    System.out.println("Get all ConsCarburants...");
	 
	    List<ConsCarburant> ConsCarburants = new ArrayList<>();
	    repository.findAll().forEach(ConsCarburants::add);
	 
	    return ConsCarburants;
	  }
	
	@GetMapping("/consCarburants/{id}")
	public ResponseEntity<ConsCarburant> getConsCarburantById(@PathVariable(value = "id") Long ConsCarburantId)
			throws ResourceNotFoundException {
		ConsCarburant ConsCarburant = repository.findById(ConsCarburantId)
				.orElseThrow(() -> new ResourceNotFoundException("ConsCarburant not found for this id :: " + ConsCarburantId));
		return ResponseEntity.ok().body(ConsCarburant);
	}
	
	 @GetMapping("/lconsCarburants/{code_dir}")
		
	    public ResponseEntity<List<ConsCarburant>> listConsommation(@PathVariable int code_dir) {
	        
			List<ConsCarburant> consCarburants = repository.findByCode(code_dir);
	       
	        return new ResponseEntity<List<ConsCarburant>>(consCarburants, HttpStatus.OK);
	    }

	@PostMapping("/consCarburants")
	
	public ResponseEntity<ConsCarburant> createConsCarburant(@Valid @RequestBody ConsCarburant ConsCarburant)  throws JsonParseException , JsonMappingException , Exception{
		repository.save(ConsCarburant);
		
	    Optional<Cposte> CposteInfo = comptrepo.findByAnnee(ConsCarburant.getAnnee());
     	if (CposteInfo.isPresent()) {
	    	Cposte Cposte = CposteInfo.get();
	           Cposte.setNumcsonede(Cposte.getNumcsonede()+1);
	         Cposte =   comptrepo.save(Cposte);
     	}
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	

	@DeleteMapping("/consCarburants/{id}")
	public Map<String, Boolean> deleteConsCarburant(@PathVariable(value = "id") Long ConsCarburantId)
			throws ResourceNotFoundException {
		ConsCarburant ConsCarburant = repository.findById(ConsCarburantId)
				.orElseThrow(() -> new ResourceNotFoundException("ConsCarburant not found  id :: " + ConsCarburantId));

		repository.delete(ConsCarburant);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/consCarburants/delete")
	  public ResponseEntity<String> deleteAllConsCarburants() {
	    System.out.println("Delete All ConsCarburants...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All ConsCarburants have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/consCarburants/{id}")
	  public ResponseEntity<ConsCarburant> updateConsCarburant(@PathVariable("id") long id, @RequestBody ConsCarburant ConsCarburant) {
	    System.out.println("Update ConsCarburant with ID = " + id + "...");
	 
	    Optional<ConsCarburant> ConsCarburantInfo = repository.findById(id);
	 
	    if (ConsCarburantInfo.isPresent()) {
	    	ConsCarburant consCarburant = ConsCarburantInfo.get();
	           consCarburant.setLibelle(ConsCarburant.getLibelle());
	           consCarburant.setQte1(ConsCarburant.getQte1());
	           consCarburant.setQte2(ConsCarburant.getQte2());
	           consCarburant.setQte3(ConsCarburant.getQte3());
	           consCarburant.setQte4(ConsCarburant.getQte4());
	       return new ResponseEntity<>(repository.save(ConsCarburant), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
