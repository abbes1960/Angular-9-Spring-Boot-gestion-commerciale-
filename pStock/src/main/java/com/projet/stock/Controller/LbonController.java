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
import com.projet.stock.model.Lbon;
import com.projet.stock.repository.LbonRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LbonController {
	@Autowired
	LbonRepository repository;
	
	 @GetMapping("/lbons")
	  public List<Lbon> getAllLbons() {
	    System.out.println("Get all Lbons...");
	    List<Lbon> Lbons = new ArrayList<>();
	    repository.findAll().forEach(Lbons::add);
	    return Lbons;
	  }
	 
	 @GetMapping("/lbons/{numero}")
	  public List<Lbon> getAllByNumero(@PathVariable(value = "numero") int numero) {
	    System.out.println("Get all Lbon...");
	 
	    List<Lbon> Lbons = new ArrayList<>();
	    repository.findAllByNumero(numero).forEach(Lbons::add);
	 
	    return Lbons;
	  }
	 
	 
	
	

	@PostMapping("/lbons")
	public @Valid Lbon createLbon(@Valid @RequestBody Lbon Lbon) {
		
		return repository.save(Lbon);
		 
	}

	@DeleteMapping("/lbons/{id}")
	public Map<String, Boolean> deleteLbon(@PathVariable(value = "id") Long LbonId)
			throws ResourceNotFoundException {
		Lbon Lbon = repository.findById(LbonId)
				.orElseThrow(() -> new ResourceNotFoundException("Lbon not found  id :: " + LbonId));
		repository.delete(Lbon);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/lbons/delete")
	  public ResponseEntity<String> deleteAllLbons() {
	    System.out.println("Delete All Lbons...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lbons have been deleted!", HttpStatus.OK);
	  }
	 
	
}

