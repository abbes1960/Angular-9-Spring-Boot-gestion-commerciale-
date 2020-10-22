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
import com.projet.stock.model.Lb1016;
import com.projet.stock.model.Lcomm;
import com.projet.stock.repository.Lb1016Repository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class Lb1016Controller {
	@Autowired
	Lb1016Repository repository;
	
	 @GetMapping("/lb1016s")
	  public List<Lb1016> getAllLb1016s() {
	    System.out.println("Get all Lb1016s...");
	    List<Lb1016> Lb1016s = new ArrayList<>();
	    repository.findAll().forEach(Lb1016s::add);
	    return Lb1016s;
	  }
	 
	 @GetMapping("/lb1016s/{numero}")
	  public List<Lb1016> getAllByNumero(@PathVariable(value = "numero") int numero) {
	    System.out.println("Get all Lb1016...");
	 
	    List<Lb1016> Lb1016s = new ArrayList<>();
	    repository.findAllByNumero(numero).forEach(Lb1016s::add);
	 
	    return Lb1016s;
	  }
	 
	 
	
	

	@PostMapping("/lb1016s")
	public @Valid Lb1016 createLb1016(@Valid @RequestBody Lb1016 Lb1016) {
		
		return repository.save(Lb1016);
		 
	}

	@DeleteMapping("/lb1016s/{id}")
	public Map<String, Boolean> deleteLb1016(@PathVariable(value = "id") Long Lb1016Id)
			throws ResourceNotFoundException {
		Lb1016 Lb1016 = repository.findById(Lb1016Id)
				.orElseThrow(() -> new ResourceNotFoundException("Lb1016 not found  id :: " + Lb1016Id));
		repository.delete(Lb1016);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/lb1016s/delete")
	  public ResponseEntity<String> deleteAllLb1016s() {
	    System.out.println("Delete All Lb1016s...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Lb1016s have been deleted!", HttpStatus.OK);
	  }
	 
	
}
