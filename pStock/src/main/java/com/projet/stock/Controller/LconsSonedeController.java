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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projet.stock.exception.ResourceNotFoundException;
import com.projet.stock.model.LconsSonede;

import com.projet.stock.repository.LconsSonedeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LconsSonedeController {
	@Autowired
	LconsSonedeRepository repository;
	 @GetMapping("/LLconsSonedes")
	  public List<LconsSonede> getAllLconsSonedes() {
	    System.out.println("Get all LconsSonedes...");
	 
	    List<LconsSonede> LconsSonedes = new ArrayList<>();
	    repository.findAll().forEach(LconsSonedes::add);
	 
	    return LconsSonedes;
	  }
	
	@GetMapping("/LconsSonedes/{id}")
	public ResponseEntity<LconsSonede> getLconsSonedeById(@PathVariable(value = "id") Long LconsSonedeId)
			throws ResourceNotFoundException {
		LconsSonede LconsSonede = repository.findById(LconsSonedeId)
				.orElseThrow(() -> new ResourceNotFoundException("LconsSonede not found for this id :: " + LconsSonedeId));
		return ResponseEntity.ok().body(LconsSonede);
	}

	@PostMapping("/LconsSonedes")
	public @Valid LconsSonede createLconsSonede(@Valid @RequestBody LconsSonede LconsSonede) {
		
		return repository.save(LconsSonede);
		
		 
	}
	

	@DeleteMapping("/LconsSonedes/{id}")
	public Map<String, Boolean> deleteLconsSonede(@PathVariable(value = "id") Long LconsSonedeId)
			throws ResourceNotFoundException {
		LconsSonede LconsSonede = repository.findById(LconsSonedeId)
				.orElseThrow(() -> new ResourceNotFoundException("LconsSonede not found  id :: " + LconsSonedeId));

		repository.delete(LconsSonede);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/LconsSonedes/delete")
	  public ResponseEntity<String> deleteAllLconsSonedes() {
	    System.out.println("Delete All LconsSonedes...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All LconsSonedes have been deleted!", HttpStatus.OK);
	  }
	 
	

	

}
