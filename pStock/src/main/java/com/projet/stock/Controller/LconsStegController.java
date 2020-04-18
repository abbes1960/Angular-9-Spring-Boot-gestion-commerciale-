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

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projet.stock.exception.ResourceNotFoundException;
import com.projet.stock.model.LconsSteg;

import com.projet.stock.repository.LconsStegRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LconsStegController {
	@Autowired
	LconsStegRepository repository;
	 @GetMapping("/LLconsStegs")
	  public List<LconsSteg> getAllLconsStegs() {
	    System.out.println("Get all LconsStegs...");
	 
	    List<LconsSteg> LconsStegs = new ArrayList<>();
	    repository.findAll().forEach(LconsStegs::add);
	 
	    return LconsStegs;
	  }
	
	@GetMapping("/LconsStegs/{id}")
	public ResponseEntity<LconsSteg> getLconsStegById(@PathVariable(value = "id") Long LconsStegId)
			throws ResourceNotFoundException {
		LconsSteg LconsSteg = repository.findById(LconsStegId)
				.orElseThrow(() -> new ResourceNotFoundException("LconsSteg not found for this id :: " + LconsStegId));
		return ResponseEntity.ok().body(LconsSteg);
	}

	@PostMapping("/LconsStegs")
	public @Valid LconsSteg createLconsSteg(@Valid @RequestBody LconsSteg LconsSteg) {
		
		return repository.save(LconsSteg);
		
		 
	}
	

	@DeleteMapping("/LconsStegs/{id}")
	public Map<String, Boolean> deleteLconsSteg(@PathVariable(value = "id") Long LconsStegId)
			throws ResourceNotFoundException {
		LconsSteg LconsSteg = repository.findById(LconsStegId)
				.orElseThrow(() -> new ResourceNotFoundException("LconsSteg not found  id :: " + LconsStegId));

		repository.delete(LconsSteg);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/LconsStegs/delete")
	  public ResponseEntity<String> deleteAllLconsStegs() {
	    System.out.println("Delete All LconsStegs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All LconsStegs have been deleted!", HttpStatus.OK);
	  }
	 
	

	 

}
