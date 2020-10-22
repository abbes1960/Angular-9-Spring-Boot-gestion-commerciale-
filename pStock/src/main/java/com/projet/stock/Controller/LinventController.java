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
import com.projet.stock.model.Linvent;
import com.projet.stock.model.Residence;
import com.projet.stock.repository.LinventRepository;
import com.projet.stock.repository.ResidenceRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LinventController {
	@Autowired
	LinventRepository repository;
	 @GetMapping("/linvents")
	  public List<Linvent> getAllLinvents() {
	    System.out.println("Get all Linvents...");
	    List<Linvent> Linvents = new ArrayList<>();
	    repository.findAll().forEach(Linvents::add);
	    return Linvents;
	  }
	 
	 @GetMapping("/linvents/{numero}")
	  public List<Linvent> getAllByNumero(@PathVariable(value = "numero") int numero) {
	    System.out.println("Get all Lbon...");
	 
	    List<Linvent> Linvents = new ArrayList<>();
	    repository.findAllByNumero(numero).forEach(Linvents::add);
	 
	    return Linvents;
	  }
	


	@PostMapping("/linvents")
	public @Valid Linvent createLinvent(@Valid @RequestBody Linvent Linvent) {
		
		return repository.save(Linvent);
		
		 
	}
	

	@DeleteMapping("/linvents/{id}")
	public Map<String, Boolean> deleteLinvent(@PathVariable(value = "id") Long LinventId)
			throws ResourceNotFoundException {
		Linvent Linvent = repository.findById(LinventId)
				.orElseThrow(() -> new ResourceNotFoundException("Linvent not found  id :: " + LinventId));

		repository.delete(Linvent);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/linvents/delete")
	  public ResponseEntity<String> deleteAllLinvents() {
	    System.out.println("Delete All Linvents...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Linvents have been deleted!", HttpStatus.OK);
	  }
	 
	

	  
	  }


