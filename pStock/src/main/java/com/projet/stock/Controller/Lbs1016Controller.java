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
import com.projet.stock.model.Lbs1016;

import com.projet.stock.repository.Lbs1016Repository;


public class Lbs1016Controller {
	@Autowired
	Lbs1016Repository repository;
	
	 @GetMapping("/Lbs1016s")
	  public List<Lbs1016> getAllLbs1016s() {
	    System.out.println("Get all Lbs1016s...");
	    List<Lbs1016> Lbs1016s = new ArrayList<>();
	    repository.findAll().forEach(Lbs1016s::add);
	    return Lbs1016s;
	  }
	
	@GetMapping("/Lbs1016s/{id}")
	public ResponseEntity<Lbs1016> getLbs1016ById(@PathVariable(value = "id") Long Lbs1016Id)
			throws ResourceNotFoundException {
		Lbs1016 Lbs1016 = repository.findById(Lbs1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Lbs1016 not found for this id :: " + Lbs1016Id));
		return ResponseEntity.ok().body(Lbs1016);
	}

	@PostMapping("/Lbs1016s")
	public @Valid Lbs1016 createLbs1016(@Valid @RequestBody Lbs1016 Lbs1016) {
		
		return repository.save(Lbs1016);
		 
	}

	@DeleteMapping("/Lbs1016s/{id}")
	public Map<String, Boolean> deleteLbs1016(@PathVariable(value = "id") Long Lbs1016Id)
			throws ResourceNotFoundException {
		Lbs1016 Lbs1016 = repository.findById(Lbs1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Lbs1016 not found  id :: " + Lbs1016Id));
		repository.delete(Lbs1016);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Lbs1016s/delete")
	  public ResponseEntity<String> deleteAllLbs1016s() {
	    System.out.println("Delete All Lbs1016s...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lbs1016s have been deleted!", HttpStatus.OK);
	  }
	 
	
}
