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
import com.projet.stock.model.Lavoir;
import com.projet.stock.repository.LavoirRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LavoirController {
	@Autowired
	LavoirRepository repository;
	
	 @GetMapping("/Lavoirs")
	  public List<Lavoir> getAllLavoirs() {
	    System.out.println("Get all Lavoirs...");
	    List<Lavoir> Lavoirs = new ArrayList<>();
	    repository.findAll().forEach(Lavoirs::add);
	    return Lavoirs;
	  }
	
	@GetMapping("/Lavoirs/{id}")
	public ResponseEntity<Lavoir> getLavoirById(@PathVariable(value = "id") Long LavoirId)
			throws ResourceNotFoundException {
		Lavoir Lavoir = repository.findById(LavoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Lavoir not found for this id :: " + LavoirId));
		return ResponseEntity.ok().body(Lavoir);
	}

	@PostMapping("/Lavoirs")
	public @Valid Lavoir createLavoir(@Valid @RequestBody Lavoir Lavoir) {
		
		return repository.save(Lavoir);
		 
	}

	@DeleteMapping("/Lavoirs/{id}")
	public Map<String, Boolean> deleteLavoir(@PathVariable(value = "id") Long LavoirId)
			throws ResourceNotFoundException {
		Lavoir Lavoir = repository.findById(LavoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Lavoir not found  id :: " + LavoirId));
		repository.delete(Lavoir);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Lavoirs/delete")
	  public ResponseEntity<String> deleteAllLavoirs() {
	    System.out.println("Delete All Lavoirs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lavoirs have been deleted!", HttpStatus.OK);
	  }
	 
	  
}
