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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.projet.stock.exception.ResourceNotFoundException;
import com.projet.stock.model.Ldevis;
import com.projet.stock.repository.LdevisRepository;

public class LdevisController {
	@Autowired
	LdevisRepository repository;
	 @GetMapping("/LLdeviss")
	  public List<Ldevis> getAllLdeviss() {
	    System.out.println("Get all Ldeviss...");
	 
	    List<Ldevis> Ldeviss = new ArrayList<>();
	    repository.findAll().forEach(Ldeviss::add);
	 
	    return Ldeviss;
	  }
	
	@GetMapping("/Ldeviss/{id}")
	public ResponseEntity<Ldevis> getLdevisById(@PathVariable(value = "id") Long LdevisId)
			throws ResourceNotFoundException {
		Ldevis Ldevis = repository.findById(LdevisId)
				.orElseThrow(() -> new ResourceNotFoundException("Ldevis not found for this id :: " + LdevisId));
		return ResponseEntity.ok().body(Ldevis);
	}

	@PostMapping("/Ldeviss")
	public @Valid Ldevis createLdevis(@Valid @RequestBody Ldevis Ldevis) {
		
		return repository.save(Ldevis);
		
		 
	}
	

	@DeleteMapping("/Ldeviss/{id}")
	public Map<String, Boolean> deleteLdevis(@PathVariable(value = "id") Long LdevisId)
			throws ResourceNotFoundException {
		Ldevis Ldevis = repository.findById(LdevisId)
				.orElseThrow(() -> new ResourceNotFoundException("Ldevis not found  id :: " + LdevisId));

		repository.delete(Ldevis);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Ldeviss/delete")
	  public ResponseEntity<String> deleteAllLdeviss() {
	    System.out.println("Delete All Ldeviss...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Ldeviss have been deleted!", HttpStatus.OK);
	  }
	 
	

	 
}
