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
import com.projet.stock.model.Bon;
import com.projet.stock.model.Cposte;

import com.projet.stock.model.Lbon;
import com.projet.stock.repository.BonRepository;
import com.projet.stock.repository.CposteRepository;
import com.projet.stock.repository.LbonRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BonController {
	@Autowired 	BonRepository repository;
	@Autowired  CposteRepository cposterepo;
	@Autowired  LbonRepository repo;
	@Autowired  ServletContext context;
	
	@GetMapping("/bons")
	public List<Bon> getAllBon() {
	    System.out.println("Get all Bons...");
	    List<Bon> Bons = new ArrayList<>();
	    repository.findAll().forEach(Bons::add);
	    return Bons;
	  }
	
	@GetMapping("/bons/{id}")
	public ResponseEntity<Bon> getBonById(@PathVariable(value = "id") Long BonId)
	
			throws ResourceNotFoundException {
		Bon Bon = repository.findById(BonId)
				.orElseThrow(() -> new ResourceNotFoundException("Bon not found for this id :: " + BonId));
		List<Lbon> lbons = new ArrayList<>();
    	repo.findAllByNumero(Bon.getNumero()).forEach(lbons::add);
    	Bon.setLbons(lbons);
		return ResponseEntity.ok().body(Bon);
	}

	

	@DeleteMapping("/bons/{id}")
	public Map<String, Boolean> deleteBon(@PathVariable(value = "id") Long BonId)
			throws ResourceNotFoundException {
		Bon Bon = repository.findById(BonId)
				.orElseThrow(() -> new ResourceNotFoundException("Bon not found  id :: " + BonId));
		repository.delete(Bon);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/bons/delete")
	  public ResponseEntity<String> deleteAllBons() {
	    System.out.println("Delete All Bons...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Bons have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/bons/{id}")
	  public ResponseEntity<Bon> updateBon(@PathVariable("id") long id, @RequestBody Bon Bon) {
	    System.out.println("Update Bon with ID = " + id + "...");
	    Optional<Bon> BonInfo = repository.findById(id);
	    if (BonInfo.isPresent()) {
	    	Bon bon = BonInfo.get();
	    	bon.setLibelle(Bon.getLibelle());
	      return new ResponseEntity<>(repository.save(Bon), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	
	  @PostMapping("/bons")
		public ResponseEntity<Bon> createBon(@Valid @RequestBody Bon Bon)  throws JsonParseException , JsonMappingException , Exception{
		  System.out.println(" save data");
		  repository.save(Bon);
		  List<Lbon> lbons = Bon.getLbons();
		    for (Lbon lc : lbons) {
	          	lc.setNumero(Bon.getNumero());
	          	 System.out.println(" save data ligne");
	          	repo.save(lc);
		       }	 
		    Optional<Cposte> CposteInfo = cposterepo.findByAnnee(Bon.getAnnee());
	     	if (CposteInfo.isPresent()) {
		    	Cposte Cposte = CposteInfo.get();
		        Cposte.setNumbon(Cposte.getNumbon()+1);
		        Cposte =   cposterepo.save(Cposte);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
			 
		}
	  
	  
	  
	
	  
}
