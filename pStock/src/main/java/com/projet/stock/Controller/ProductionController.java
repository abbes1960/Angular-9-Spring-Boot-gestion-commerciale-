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
import com.projet.stock.model.Production;
import com.projet.stock.model.Bon;
import com.projet.stock.model.Cposte;
import com.projet.stock.model.Lbon;
import com.projet.stock.model.Lproduction;
import com.projet.stock.repository.ProductionRepository;
import com.projet.stock.repository.CposteRepository;
import com.projet.stock.repository.LproductionRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductionController {
	@Autowired 	ProductionRepository repository;
	@Autowired  CposteRepository cposterepo;
	@Autowired  LproductionRepository repo;
	@Autowired  ServletContext context;
	 
	
	
	@GetMapping("/productions")
	 
	  public List<Production> getAlLproduction() {
	    System.out.println("Get all Productions...");
	    List<Production> Productions = new ArrayList<>();
	    repository.findAll().forEach(Productions::add);
	    return Productions;
	  }
	
	@GetMapping("/productions/{id}")
	public ResponseEntity<Production> getProductionById(@PathVariable(value = "id") Long ProductionId)
			throws ResourceNotFoundException {
		Production Production = repository.findById(ProductionId)
				.orElseThrow(() -> new ResourceNotFoundException("Production not found for this id :: " + ProductionId));
		List<Lproduction> lproductions = new ArrayList<>();
    	repo.findAllByNumero(Production.getNumero()).forEach(lproductions::add);
    	Production.setLproductions(lproductions);
		return ResponseEntity.ok().body(Production);
	}

	

	@DeleteMapping("/productions/{id}")
	public Map<String, Boolean> deleteProduction(@PathVariable(value = "id") Long ProductionId)
			throws ResourceNotFoundException {
		Production Production = repository.findById(ProductionId)
				.orElseThrow(() -> new ResourceNotFoundException("Production not found  id :: " + ProductionId));
		repository.delete(Production);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/productions/delete")
	  public ResponseEntity<String> deleteAlLproductions() {
	    System.out.println("Delete All Productions...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Productions have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/productions/{id}")
	  public ResponseEntity<Production> updateProduction(@PathVariable("id") long id, @RequestBody Production Production) {
	    System.out.println("Update Production with ID = " + id + "...");
	    Optional<Production> ProductionInfo = repository.findById(id);
	    if (ProductionInfo.isPresent()) {
	    	Production production = ProductionInfo.get();
	    	production.setLibelle(Production.getLibelle());
	      return new ResponseEntity<>(repository.save(Production), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	
	  @PostMapping("/productions")
		public ResponseEntity<Production> createProduction(@Valid @RequestBody Production Production)  throws JsonParseException , JsonMappingException , Exception{
		  System.out.println("ffffgfgfggf");
		  repository.save(Production);
		  List<Lproduction> Lproductions = Production.getLproductions();
		    for (Lproduction lc : Lproductions) {
	          	lc.setNumero(Production.getNumero());
	          	repo.save(lc);
		       }	 
		    Optional<Cposte> CposteInfo = cposterepo.findByAnnee(Production.getAnnee());
	     	if (CposteInfo.isPresent()) {
		    	Cposte Cposte = CposteInfo.get();
		           Cposte.setNumpro(Cposte.getNumpro()+1);
		         Cposte =   cposterepo.save(Cposte);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		}
}





