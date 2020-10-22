

package com.projet.stock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
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
import com.projet.stock.model.Destination;
import com.projet.stock.repository.DestinationRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DestinationController  {
	@Autowired
	DestinationRepository repository;
	@Autowired  ServletContext context;

	 @GetMapping("/destinations")
	  public List<Destination> getAllDestinations() {
	    System.out.println("Get all Destinations...");
	 
	    List<Destination> Destinations = new ArrayList<>();
	    repository.findAll().forEach(Destinations::add);
	 
	    return Destinations;
	  }
	
	@GetMapping("/destinations/{id}")
	public ResponseEntity<Destination> getDestinationById(@PathVariable(value = "id") Long DestinationId)
			throws ResourceNotFoundException {
		Destination Destination = repository.findById(DestinationId)
				.orElseThrow(() -> new ResourceNotFoundException("Destination not found for this id :: " + DestinationId));
		return ResponseEntity.ok().body(Destination);
	}

	@PostMapping("/destinations")
	public Destination createDestination(@Valid @RequestBody Destination Destination) {
		return repository.save(Destination);
	}
	

	@DeleteMapping("/destinations/{id}")
	public Map<String, Boolean> deleteDestination(@PathVariable(value = "id") Long DestinationId)
			throws ResourceNotFoundException {
		Destination Destination = repository.findById(DestinationId)
				.orElseThrow(() -> new ResourceNotFoundException("Destination not found  id :: " + DestinationId));

		repository.delete(Destination);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/destinations/delete")
	  public ResponseEntity<String> deleteAllDestinations() {
	    System.out.println("Delete All Destinations...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Destinations have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/destinations/{id}")
	  public ResponseEntity<Destination> updateDestination(@PathVariable("id") long id, @RequestBody Destination Destination) {
	    System.out.println("Update Destination with ID = " + id + "...");
	 
	    Optional<Destination> DestinationInfo = repository.findById(id);
	  
	    if (DestinationInfo.isPresent()) {
	    	Destination destination = DestinationInfo.get();
	          destination.setLibelle(Destination.getLibelle());
	           
	      return new ResponseEntity<>(repository.save(destination), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
