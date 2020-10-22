package com.projet.stock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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

import com.projet.stock.exception.ResourceNotFoundException;
import com.projet.stock.model.Cposte;

import com.projet.stock.repository.CposteRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CposteControlleur {
	@Autowired
	CposteRepository repository;
	
	 @GetMapping("/cpostes")
	  public List<Cposte> getAllCpostes() {
	    System.out.println("Get all Cpostes...");
	 
	    List<Cposte> Cpostes = new ArrayList<>();
	    repository.findAll().forEach(Cpostes::add);
	 
	    return Cpostes;
	  }
	
	@GetMapping("/cpostes/{annee}")
	public ResponseEntity<Cposte> getCposteByAnnee(@PathVariable(value = "annee") int annee)
			throws ResourceNotFoundException {
		Cposte Cposte = repository.findByAnnee(annee)
				.orElseThrow(() -> new ResourceNotFoundException("Cposte not found for this id :: " + annee));
		return ResponseEntity.ok().body(Cposte);
	}

	@PostMapping("/cpostes")
	public Cposte createCposte(@Valid @RequestBody Cposte Cposte) {
		return repository.save(Cposte);
	}
	

	@DeleteMapping("/cpostes/{id}")
	public Map<String, Boolean> deleteCposte(@PathVariable(value = "id") Long CposteId)
			throws ResourceNotFoundException {
		Cposte Cposte = repository.findById(CposteId)
				.orElseThrow(() -> new ResourceNotFoundException("Cposte not found  id :: " + CposteId));

		repository.delete(Cposte);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/cpostes/delete")
	  public ResponseEntity<String> deleteAllCpostes() {
	    System.out.println("Delete All Cpostes...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Cpostes have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/cpostes/{id}")
	  public ResponseEntity<Cposte> updateCposte(@PathVariable("id") long id, @RequestBody Cposte Cposte) {
	    System.out.println("Update Cposte with ID = " + id + "...");
	 
	    Optional<Cposte> CposteInfo = repository.findById(id);
	 
	    if (CposteInfo.isPresent()) {
	    	Cposte cposte = CposteInfo.get();
	          
	           cposte.setNumbon(Cposte.getNumbon());
	           cposte.setNumbs(Cposte.getNumbs());
	          
	      return new ResponseEntity<>(repository.save(Cposte), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
