

package com.projet.stock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.projet.stock.model.Tarif;
import com.projet.stock.repository.TarifRepository;
import com.projet.stock.model.Bon;
import com.projet.stock.model.Cposte;
import com.projet.stock.model.Destination;
import com.projet.stock.model.Lbon;
import com.projet.stock.repository.DestinationRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TarifController  {
	@Autowired TarifRepository repository;
	@Autowired DestinationRepository repo;
	 @GetMapping("/tarifs")
	  public List<Tarif> getAllTarifs() {
	    System.out.println("Get all Tarifs...");
	 
	    List<Tarif> Tarifs = new ArrayList<>();
	    repository.findAll().forEach(Tarifs::add);
	 
	    return Tarifs;
	  }
	
	@GetMapping("/tarifs/{id}")
	public ResponseEntity<Tarif> getTarifById(@PathVariable(value = "id") Long TarifId)
			throws ResourceNotFoundException {
		Tarif Tarif = repository.findById(TarifId)
				.orElseThrow(() -> new ResourceNotFoundException("Tarif not found for this id :: " + TarifId));
		return ResponseEntity.ok().body(Tarif);
	}
	

	
	@PostMapping("/tarifs")
	public ResponseEntity<Destination> createTarif(@Valid @RequestBody Destination Destination)  throws JsonParseException , JsonMappingException , Exception{
		 System.out.println(" Debut ......"); 
		List<Tarif> ltarifs = Destination.getLtarifs();
      	 System.out.println(" Debut ......11");
	    for (Tarif tarif : ltarifs) {
	    	 System.out.println(Destination.getCode());
          	tarif.setCode(Destination.getCode());
          	System.out.println(Destination.getLibelle());
          	tarif.setLibelle(Destination.getLibelle());
          	tarif.setDeb(tarif.getDeb());
          	tarif.setFin(tarif.getFin());
          	tarif.setMontant(tarif.getMontant());
          	 System.out.println(" save data ligne");
          	repository.save(tarif);
	       }	
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@DeleteMapping("/tarifs/{id}")
	public Map<String, Boolean> deleteTarif(@PathVariable(value = "id") Long TarifId)
			throws ResourceNotFoundException {
		Tarif Tarif = repository.findById(TarifId)
				.orElseThrow(() -> new ResourceNotFoundException("Tarif not found  id :: " + TarifId));

		repository.delete(Tarif);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/tarifs/delete")
	  public ResponseEntity<String> deleteAllTarifs() {
	    System.out.println("Delete All Tarifs...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Tarifs have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/tarifs/{id}")
	  public ResponseEntity<Tarif> updateTarif(@PathVariable("id") long id, @RequestBody Tarif Tarif) {
	    System.out.println("Update Tarif with ID = " + id + "...");
	 
	    Optional<Tarif> TarifInfo = repository.findById(id);
	 
	    if (TarifInfo.isPresent()) {
	    	Tarif tarif = TarifInfo.get();
	          tarif.setCode(Tarif.getCode());
	           
	      return new ResponseEntity<>(repository.save(Tarif), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}

