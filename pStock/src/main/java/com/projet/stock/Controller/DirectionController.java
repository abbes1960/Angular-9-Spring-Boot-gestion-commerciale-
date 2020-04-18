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
import com.projet.stock.model.Direction;
import com.projet.stock.repository.DirectionRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DirectionController {
	@Autowired
	DirectionRepository repository;
	
	 @GetMapping("/directions")
	  public List<Direction> getAllDirections() {
	    System.out.println("Get all Directions...");
	 
	    List<Direction> Directions = new ArrayList<>();
	    repository.findAll().forEach(Directions::add);
	 
	    return Directions;
	  }
	
	@GetMapping("/directions/{id}")
	public ResponseEntity<Direction> getDirectionById(@PathVariable(value = "id") Long DirectionId)
			throws ResourceNotFoundException {
		Direction Direction = repository.findById(DirectionId)
				.orElseThrow(() -> new ResourceNotFoundException("Direction not found for this id :: " + DirectionId));
		return ResponseEntity.ok().body(Direction);
	}

	@PostMapping("/directions")
	public Direction createDirection(@Valid @RequestBody Direction Direction) {
		return repository.save(Direction);
	}
	

	@DeleteMapping("/directions/{id}")
	public Map<String, Boolean> deleteDirection(@PathVariable(value = "id") Long DirectionId)
			throws ResourceNotFoundException {
		Direction Direction = repository.findById(DirectionId)
				.orElseThrow(() -> new ResourceNotFoundException("Direction not found  id :: " + DirectionId));

		repository.delete(Direction);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/directions/delete")
	  public ResponseEntity<String> deleteAllDirections() {
	    System.out.println("Delete All Directions...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Directions have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/directions/{id}")
	  public ResponseEntity<Direction> updateDirection(@PathVariable("id") long id, @RequestBody Direction Direction) {
	    System.out.println("Update Direction with ID = " + id + "...");
	 
	    Optional<Direction> DirectionInfo = repository.findById(id);
	 
	    if (DirectionInfo.isPresent()) {
	    	Direction direction = DirectionInfo.get();
	          direction.setLibelle(Direction.getLibelle());
	           
	      return new ResponseEntity<>(repository.save(Direction), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
