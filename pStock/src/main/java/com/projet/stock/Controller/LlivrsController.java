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

import com.projet.stock.model.Llivrs;

import com.projet.stock.repository.LlivrsRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LlivrsController {
	@Autowired
	LlivrsRepository repository;
	 @GetMapping("/Llivrss")
	  public List<Llivrs> getAllLlivrss() {
	    System.out.println("Get all Llivrss...");
	 
	    List<Llivrs> Llivrss = new ArrayList<>();
	    repository.findAll().forEach(Llivrss::add);
	 
	    return Llivrss;
	  }
	 
	 @GetMapping("/Llivrss/{numero}")
	  public List<Llivrs> getAllByNumero(@PathVariable(value = "numero") int numero) {
	    System.out.println("Get all Lbon...");
	 
	    List<Llivrs> Llivrss = new ArrayList<>();
	    repository.findAllByNumero(numero).forEach(Llivrss::add);
	 
	    return Llivrss;
	  }
	


	@PostMapping("/Llivrss")
	public @Valid Llivrs createLlivrs(@Valid @RequestBody Llivrs Llivrs) {
		
		return repository.save(Llivrs);
		
		 
	}
	

	@DeleteMapping("/Llivrss/{id}")
	public Map<String, Boolean> deleteLlivrs(@PathVariable(value = "id") Long LlivrsId)
			throws ResourceNotFoundException {
		Llivrs Llivrs = repository.findById(LlivrsId)
				.orElseThrow(() -> new ResourceNotFoundException("Llivrs not found  id :: " + LlivrsId));

		repository.delete(Llivrs);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Llivrss/delete")
	  public ResponseEntity<String> deleteAllLlivrss() {
	    System.out.println("Delete All Llivrss...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Llivrss have been deleted!", HttpStatus.OK);
	  }
	 
	

	 

}
