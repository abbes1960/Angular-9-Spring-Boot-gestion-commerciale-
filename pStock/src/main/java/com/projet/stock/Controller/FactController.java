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

import com.projet.stock.model.Compteur;
import com.projet.stock.model.Fact;

import com.projet.stock.model.Lfact;

import com.projet.stock.repository.CompteurRepository;
import com.projet.stock.repository.FactRepository;
import com.projet.stock.repository.LfactRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FactController {
	@Autowired 	FactRepository repository;
	@Autowired LfactRepository repo;
	@Autowired CompteurRepository comptrepo;
	@Autowired  ServletContext context;
	 @GetMapping("/facts")
	  public List<Fact> getAllFacts() {
	    System.out.println("Get all Facts...");
	    List<Fact> Facts = new ArrayList<>();
	    repository.findAll().forEach(Facts::add);
	    return Facts;
	  }
	
	@GetMapping("/facts/{id}")
	public ResponseEntity<Fact> getFactById(@PathVariable(value = "id") Long FactId)
			throws ResourceNotFoundException {
		Fact Fact = repository.findById(FactId)
				.orElseThrow(() -> new ResourceNotFoundException("Fact not found for this id :: " + FactId));
		return ResponseEntity.ok().body(Fact);
	}

	@PostMapping("/facts")
	public ResponseEntity<Fact> createBs1016(@Valid @RequestBody Fact Fact)  throws JsonParseException , JsonMappingException , Exception{
		  repository.save(Fact);
		  List<Lfact> lfacts = Fact.getLfacts();
		    for (Lfact lc : lfacts) {
	          	lc.setNumero(Fact.getNumero());
	          	repo.save(lc);
		       }	 
		    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Fact.getAnnee());
	     	if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNumfac(compteur.getNumfac()+1);
		         compteur =   comptrepo.save(compteur);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		} 
	

	@DeleteMapping("/facts/{id}")
	public Map<String, Boolean> deleteFact(@PathVariable(value = "id") Long FactId)
			throws ResourceNotFoundException {
		Fact Fact = repository.findById(FactId)
				.orElseThrow(() -> new ResourceNotFoundException("Fact not found  id :: " + FactId));
		repository.delete(Fact);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/facts/delete")
	  public ResponseEntity<String> deleteAllFacts() {
	    System.out.println("Delete All Facts...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Facts have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Facts/{id}")
	  public ResponseEntity<Fact> updateFact(@PathVariable("id") long id, @RequestBody Fact Fact) {
	    System.out.println("Update Fact with ID = " + id + "...");
	    Optional<Fact> FactInfo = repository.findById(id);
	    if (FactInfo.isPresent()) {
	    	Fact fact = FactInfo.get();
	    	fact.setLibelle(Fact.getLibelle());
	      return new ResponseEntity<>(repository.save(Fact), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
