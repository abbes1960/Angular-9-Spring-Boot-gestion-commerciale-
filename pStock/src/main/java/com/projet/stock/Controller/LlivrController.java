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
import com.projet.stock.model.Llivr;
import com.projet.stock.model.Residence;
import com.projet.stock.repository.LlivrRepository;
import com.projet.stock.repository.ResidenceRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LlivrController {
	@Autowired
	LlivrRepository repository;
	 @GetMapping("/LLlivrs")
	  public List<Llivr> getAllLlivrs() {
	    System.out.println("Get all Llivrs...");
	 
	    List<Llivr> Llivrs = new ArrayList<>();
	    repository.findAll().forEach(Llivrs::add);
	 
	    return Llivrs;
	  }
	
	@GetMapping("/Llivrs/{id}")
	public ResponseEntity<Llivr> getLlivrById(@PathVariable(value = "id") Long LlivrId)
			throws ResourceNotFoundException {
		Llivr Llivr = repository.findById(LlivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Llivr not found for this id :: " + LlivrId));
		return ResponseEntity.ok().body(Llivr);
	}

	@PostMapping("/Llivrs")
	public @Valid Llivr createLlivr(@Valid @RequestBody Llivr Llivr) {
		
		return repository.save(Llivr);
		
		 
	}
	

	@DeleteMapping("/Llivrs/{id}")
	public Map<String, Boolean> deleteLlivr(@PathVariable(value = "id") Long LlivrId)
			throws ResourceNotFoundException {
		Llivr Llivr = repository.findById(LlivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Llivr not found  id :: " + LlivrId));

		repository.delete(Llivr);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Llivrs/delete")
	  public ResponseEntity<String> deleteAllLlivrs() {
	    System.out.println("Delete All Llivrs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Llivrs have been deleted!", HttpStatus.OK);
	  }
	 
	

	 

}
