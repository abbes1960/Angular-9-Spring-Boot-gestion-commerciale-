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
import com.projet.stock.model.Lflivr;
import com.projet.stock.model.Residence;
import com.projet.stock.repository.LflivrRepository;
import com.projet.stock.repository.ResidenceRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LflivrController {
	@Autowired
	LflivrRepository repository;
	 @GetMapping("/LLflivrs")
	  public List<Lflivr> getAllLflivrs() {
	    System.out.println("Get all Lflivrs...");
	 
	    List<Lflivr> Lflivrs = new ArrayList<>();
	    repository.findAll().forEach(Lflivrs::add);
	 
	    return Lflivrs;
	  }
	
	@GetMapping("/Lflivrs/{id}")
	public ResponseEntity<Lflivr> getLflivrById(@PathVariable(value = "id") Long LflivrId)
			throws ResourceNotFoundException {
		Lflivr Lflivr = repository.findById(LflivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Lflivr not found for this id :: " + LflivrId));
		return ResponseEntity.ok().body(Lflivr);
	}

	@PostMapping("/Lflivrs")
	public @Valid Lflivr createLflivr(@Valid @RequestBody Lflivr Lflivr) {
		
		return repository.save(Lflivr);
		
		 
	}
	

	@DeleteMapping("/Lflivrs/{id}")
	public Map<String, Boolean> deleteLflivr(@PathVariable(value = "id") Long LflivrId)
			throws ResourceNotFoundException {
		Lflivr Lflivr = repository.findById(LflivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Lflivr not found  id :: " + LflivrId));

		repository.delete(Lflivr);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Lflivrs/delete")
	  public ResponseEntity<String> deleteAllLflivrs() {
	    System.out.println("Delete All Lflivrs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lflivrs have been deleted!", HttpStatus.OK);
	  }
	 
	

	 

}
