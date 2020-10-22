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
import com.projet.stock.model.Depot;
import com.projet.stock.model.Cposte;

import com.projet.stock.model.Ldepot;
import com.projet.stock.model.Scategorie;
import com.projet.stock.repository.DepotRepository;
import com.projet.stock.repository.CposteRepository;
import com.projet.stock.repository.LdepotRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DepotController {
	@Autowired 	DepotRepository repository;
	@Autowired  CposteRepository cposterepo;
	@Autowired  LdepotRepository repo;
	@Autowired  ServletContext context;
	
	@GetMapping("/depots")
	public List<Depot> getAllDepot() {
	    System.out.println("Get all Depots...");
	    List<Depot> Depots = new ArrayList<>();
	    repository.findAll().forEach(Depots::add);
	    return Depots;
	  }
	
	@GetMapping("/depots/{id}")
	public ResponseEntity<Depot> getDepotById(@PathVariable(value = "id") Long DepotId)
	
			throws ResourceNotFoundException {
		Depot depot = repository.findById(DepotId)
				.orElseThrow(() -> new ResourceNotFoundException("Depot not found for this id :: " + DepotId));
		List<Ldepot> ldepots = new ArrayList<>();
    	repo.findAllByNumero(depot.getNumero()).forEach(ldepots::add);
    	depot.setLdepots(ldepots);
		return ResponseEntity.ok().body(depot);
	}

	
	 @GetMapping("/ldepots/{code}")
	 public ResponseEntity<List<Depot>> listdepot(@PathVariable int code) {
		 System.out.println("Get all Depot fffff...");  
			List<Depot> depotss = repository.findByCode(code);
	       
	        return new ResponseEntity<List<Depot>>(depotss, HttpStatus.OK);
	    }
	

	@DeleteMapping("/depots/{id}")
	public Map<String, Boolean> deleteDepot(@PathVariable(value = "id") Long DepotId)
			throws ResourceNotFoundException {
		Depot Depot = repository.findById(DepotId)
				.orElseThrow(() -> new ResourceNotFoundException("Depot not found  id :: " + DepotId));
		repository.delete(Depot);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/depots/delete")
	  public ResponseEntity<String> deleteAllDepots() {
	    System.out.println("Delete All Depots...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Depots have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/depots/{id}")
	  public ResponseEntity<Depot> updateDepot(@PathVariable("id") long id, @RequestBody Depot Depot) {
	    System.out.println("Update Depot with ID = " + id + "...");
	    Optional<Depot> DepotInfo = repository.findById(id);
	    if (DepotInfo.isPresent()) {
	    	Depot depot = DepotInfo.get();
	    	depot.setLibelle(Depot.getLibelle());
	      return new ResponseEntity<>(repository.save(Depot), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	
	  @PostMapping("/depots")
		public ResponseEntity<Depot> createDepot(@Valid @RequestBody Depot Depot)  throws JsonParseException , JsonMappingException , Exception{
		  System.out.println(" save data");
		  repository.save(Depot);
		  List<Ldepot> ldepots = Depot.getLdepots();
		    for (Ldepot lc : ldepots) {
	          	lc.setNumero(Depot.getNumero());
	          	 System.out.println(" save data ligne");
	          	repo.save(lc);
		       }	 
		    Optional<Cposte> CposteInfo = cposterepo.findByAnnee(Depot.getAnnee());
	     	if (CposteInfo.isPresent()) {
		    	Cposte Cposte = CposteInfo.get();
		        Cposte.setNumdepot(Cposte.getNumdepot()+1);
		        Cposte =   cposterepo.save(Cposte);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
			 
		}
	  
	

}
