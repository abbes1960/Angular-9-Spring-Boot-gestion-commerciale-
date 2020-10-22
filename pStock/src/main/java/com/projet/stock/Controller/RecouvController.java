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
import com.projet.stock.model.Lrecouf;
import com.projet.stock.model.Lrecouv;
import com.projet.stock.model.Recouf;
import com.projet.stock.model.Recouv;
import com.projet.stock.repository.CompteurRepository;
import com.projet.stock.repository.LrecouvRepository;
import com.projet.stock.repository.RecouvRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RecouvController {
	@Autowired 	RecouvRepository repository;
	@Autowired LrecouvRepository repo;
	@Autowired CompteurRepository comptrepo;
	@Autowired  ServletContext context;
	 @GetMapping("/Recouvs")
	  public List<Recouv> getAllRecouvs() {
	    System.out.println("Get all Recouvs...");
	    List<Recouv> Recouvs = new ArrayList<>();
	    repository.findAll().forEach(Recouvs::add);
	    return Recouvs;
	  }
	
	@GetMapping("/Recouvs/{id}")
	public ResponseEntity<Recouv> getRecouvById(@PathVariable(value = "id") Long RecouvId)
			throws ResourceNotFoundException {
		Recouv Recouv = repository.findById(RecouvId)
				.orElseThrow(() -> new ResourceNotFoundException("Recouv not found for this id :: " + RecouvId));
		return ResponseEntity.ok().body(Recouv);
	}

	@PostMapping("/Recouvs")
	public ResponseEntity<Recouv> createRecouf(@Valid @RequestBody Recouv Recouv)  throws JsonParseException , JsonMappingException , Exception{
		  repository.save(Recouv);
		  List<Lrecouv> lrecouvs = Recouv.getLrecouvs();
		    for (Lrecouv lc : lrecouvs) {
	          	lc.setNumero(Recouv.getNumero());
	          	repo.save(lc);
		       }	 
		    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Recouv.getAnnee());
	     	if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNumregf(compteur.getNumregf()+1);
		         compteur =   comptrepo.save(compteur);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		} 

	@DeleteMapping("/Recouvs/{id}")
	public Map<String, Boolean> deleteRecouv(@PathVariable(value = "id") Long RecouvId)
			throws ResourceNotFoundException {
		Recouv Recouv = repository.findById(RecouvId)
				.orElseThrow(() -> new ResourceNotFoundException("Recouv not found  id :: " + RecouvId));
		repository.delete(Recouv);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Recouvs/delete")
	  public ResponseEntity<String> deleteAllRecouvs() {
	    System.out.println("Delete All Recouvs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Recouvs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Recouvs/{id}")
	  public ResponseEntity<Recouv> updateRecouv(@PathVariable("id") long id, @RequestBody Recouv Recouv) {
	    System.out.println("Update Recouv with ID = " + id + "...");
	    Optional<Recouv> RecouvInfo = repository.findById(id);
	    if (RecouvInfo.isPresent()) {
	    	Recouv recouv = RecouvInfo.get();
	    	recouv.setLibelle(Recouv.getLibelle());
	      return new ResponseEntity<>(repository.save(Recouv), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }		
}
