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
import com.projet.stock.model.Invent;
import com.projet.stock.model.Linvent;
import com.projet.stock.model.Lrecouf;
import com.projet.stock.model.Recouf;
import com.projet.stock.repository.CompteurRepository;
import com.projet.stock.repository.InventRepository;
import com.projet.stock.repository.LinventRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class InventController {
	@Autowired InventRepository repository;
	@Autowired LinventRepository repo;
	@Autowired CompteurRepository comptrepo;
	@Autowired  ServletContext context;
	 @GetMapping("/invents")
	  public List<Invent> getAllInvents() {
	    System.out.println("Get all Invents...");
	    List<Invent> Invents = new ArrayList<>();
	    repository.findAll().forEach(Invents::add);
	    return Invents;
	  }
	
	@GetMapping("/invents/{id}")
	public ResponseEntity<Invent> getInventById(@PathVariable(value = "id") Long InventId)
			throws ResourceNotFoundException {
		Invent Invent = repository.findById(InventId)
				.orElseThrow(() -> new ResourceNotFoundException("Invent not found for this id :: " + InventId));
		return ResponseEntity.ok().body(Invent);
	}

	@PostMapping("/invents")
	public ResponseEntity<Invent> createInvent(@Valid @RequestBody Invent Invent)  throws JsonParseException , JsonMappingException , Exception{
		System.out.println("Save invent..."); 
		repository.save(Invent);
		  List<Linvent> linvents = Invent.getLinvents();
		    for (Linvent lc : linvents) {
	          	lc.setNumero(Invent.getNumero());
	        	System.out.println("Save linvent..."); 
	          	repo.save(lc);
		       }	 
		    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Invent.getAnnee());
	     	if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNuminv(compteur.getNuminv()+1);
		         compteur =   comptrepo.save(compteur);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		} 

	@DeleteMapping("/invents/{id}")
	public Map<String, Boolean> deleteInvent(@PathVariable(value = "id") Long InventId)
			throws ResourceNotFoundException {
		Invent Invent = repository.findById(InventId)
				.orElseThrow(() -> new ResourceNotFoundException("Invent not found  id :: " + InventId));
		repository.delete(Invent);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/invents/delete")
	  public ResponseEntity<String> deleteAllInvents() {
	    System.out.println("Delete All Invents...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Invents have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/invents/{id}")
	  public ResponseEntity<Invent> updateInvent(@PathVariable("id") long id, @RequestBody Invent Invent) {
	    System.out.println("Update Invent with ID = " + id + "...");
	    Optional<Invent> InventInfo = repository.findById(id);
	    if (InventInfo.isPresent()) {
	    	Invent invent = InventInfo.get();
	    	invent.setLibelle(Invent.getLibelle());
	      return new ResponseEntity<>(repository.save(Invent), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
