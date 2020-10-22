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
import com.projet.stock.model.TypePatrimoine;
import com.projet.stock.repository.TypePatrimoineRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TypePatrimoineController {
	@Autowired
	TypePatrimoineRepository repository;
	
	 @GetMapping("/typePatrimoines")
	  public List<TypePatrimoine> getAllTypePatrimoines() {
	    System.out.println("Get all TypePatrimoines...");
	 
	    List<TypePatrimoine> TypePatrimoines = new ArrayList<>();
	    repository.findAll().forEach(TypePatrimoines::add);
	 
	    return TypePatrimoines;
	  }
	
	@GetMapping("/typePatrimoines/{code}")
	public ResponseEntity<TypePatrimoine> getTypePatrimoineByCode(@PathVariable(value = "code") String code)
			throws ResourceNotFoundException {
		TypePatrimoine TypePatrimoine = repository.findByCode(code)
				.orElseThrow(() -> new ResourceNotFoundException("TypePatrimoine not found for this code :: " + code ));
		return ResponseEntity.ok().body(TypePatrimoine);
	}

	@PostMapping("/typePatrimoines")
	public TypePatrimoine createTypePatrimoine(@Valid @RequestBody TypePatrimoine TypePatrimoine) {
		
		return repository.save(TypePatrimoine);
	}
	

	@DeleteMapping("/typePatrimoines/{id}")
	public Map<String, Boolean> deleteTypePatrimoine(@PathVariable(value = "id") Long TypePatrimoineId)
			throws ResourceNotFoundException {
		TypePatrimoine TypePatrimoine = repository.findById(TypePatrimoineId)
				.orElseThrow(() -> new ResourceNotFoundException("TypePatrimoine not found  id :: " + TypePatrimoineId));

		repository.delete(TypePatrimoine);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/typePatrimoines/delete")
	  public ResponseEntity<String> deleteAllTypePatrimoines() {
	    System.out.println("Delete All TypePatrimoines...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All TypePatrimoines have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/typePatrimoines/{id}")
	  public ResponseEntity<TypePatrimoine> updateTypePatrimoine(@PathVariable("id") long id, @RequestBody TypePatrimoine TypePatrimoine) {
	    System.out.println("Update TypePatrimoine with ID = " + id + "...");
	 
	    Optional<TypePatrimoine> TypePatrimoineInfo = repository.findById(id);
	 
	    if (TypePatrimoineInfo.isPresent()) {
	    	TypePatrimoine typePatrimoine = TypePatrimoineInfo.get();
	          typePatrimoine.setLibelle(TypePatrimoine.getLibelle());
	           
	      return new ResponseEntity<>(repository.save(TypePatrimoine), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
