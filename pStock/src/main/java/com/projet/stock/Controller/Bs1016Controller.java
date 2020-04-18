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

import com.projet.stock.model.Bs1016;
import com.projet.stock.model.Compteur;

import com.projet.stock.model.Lbs1016;

import com.projet.stock.repository.Bs1016Repository;
import com.projet.stock.repository.CompteurRepository;
import com.projet.stock.repository.Lbs1016Repository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class Bs1016Controller {
	@Autowired 	Bs1016Repository repository;
	@Autowired Lbs1016Repository repo;
	@Autowired CompteurRepository comptrepo;
	@Autowired  ServletContext context;
	 @GetMapping("/Bs1016s")
	  public List<Bs1016> getAllBs1016s() {
	    System.out.println("Get all Bs1016s...");
	    List<Bs1016> Bs1016s = new ArrayList<>();
	    repository.findAll().forEach(Bs1016s::add);
	    return Bs1016s;
	  }
	
	@GetMapping("/Bs1016s/{id}")
	public ResponseEntity<Bs1016> getBs1016ById(@PathVariable(value = "id") Long Bs1016Id)
			throws ResourceNotFoundException {
		Bs1016 Bs1016 = repository.findById(Bs1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Bs1016 not found for this id :: " + Bs1016Id));
		return ResponseEntity.ok().body(Bs1016);
	}

	@PostMapping("/Bs1016s")
	public ResponseEntity<Bs1016> createBs1016(@Valid @RequestBody Bs1016 Bs1016)  throws JsonParseException , JsonMappingException , Exception{
	  repository.save(Bs1016);
	  List<Lbs1016> lbs1016s = Bs1016.getLbs1016s();
	    for (Lbs1016 lc : lbs1016s) {
          	lc.setNumero(Bs1016.getNumero());
          	repo.save(lc);
	       }	 
	    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Bs1016.getAnnee());
     	if (CompteurInfo.isPresent()) {
	    	Compteur compteur = CompteurInfo.get();
	           compteur.setNumbs(compteur.getNumbs()+1);
	         compteur =   comptrepo.save(compteur);
     	}
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/Bs1016s/{id}")
	public Map<String, Boolean> deleteBs1016(@PathVariable(value = "id") Long Bs1016Id)
			throws ResourceNotFoundException {
		Bs1016 Bs1016 = repository.findById(Bs1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Bs1016 not found  id :: " + Bs1016Id));
		repository.delete(Bs1016);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Bs1016s/delete")
	  public ResponseEntity<String> deleteAllBs1016s() {
	    System.out.println("Delete All Bs1016s...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Bs1016s have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Bs1016s/{id}")
	  public ResponseEntity<Bs1016> updateBs1016(@PathVariable("id") long id, @RequestBody Bs1016 Bs1016) {
	    System.out.println("Update Bs1016 with ID = " + id + "...");
	    Optional<Bs1016> Bs1016Info = repository.findById(id);
	    if (Bs1016Info.isPresent()) {
	    	Bs1016 bs1016 = Bs1016Info.get();
	    	bs1016.setLibelle(Bs1016.getLibelle());
	      return new ResponseEntity<>(repository.save(Bs1016), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
