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
import com.projet.stock.model.Residence;
import com.projet.stock.repository.ResidenceRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ResidenceController {
	@Autowired
	ResidenceRepository repository;
	
	 @GetMapping("/residences")
	  public List<Residence> getAllResidences() {
	    System.out.println("Get all Residences...");
	 
	    List<Residence> Residences = new ArrayList<>();
	    repository.findAll().forEach(Residences::add);
	 
	    return Residences;
	  }

	 @GetMapping("/residences/{id}")
		public ResponseEntity<Residence> getResidenceById(@PathVariable(value = "id") Long ResidenceId)
				throws ResourceNotFoundException {
			Residence Residence = repository.findById(ResidenceId)
					.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + ResidenceId));
			return ResponseEntity.ok().body(Residence);
		}

	 @GetMapping("/residencess/{code}")
		
	    public ResponseEntity<List<Residence>> listResidence(@PathVariable int code) {
		  System.out.println("Get  Residences...");
			List<Residence> residences = repository.findByCoddir(code);
	       
	        return new ResponseEntity<List<Residence>>(residences, HttpStatus.OK);
	    }
	
	@PostMapping("/residences")
	public Residence createResidence(@Valid @RequestBody Residence Residence) {
		return repository.save(Residence);
	}
	

	@DeleteMapping("/residences/{id}")
	public Map<String, Boolean> deleteResidence(@PathVariable(value = "id") Long ResidenceId)
			throws ResourceNotFoundException {
		Residence Residence = repository.findById(ResidenceId)
				.orElseThrow(() -> new ResourceNotFoundException("Categorie not found  id :: " + ResidenceId));

		repository.delete(Residence);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/residences/delete")
	  public ResponseEntity<String> deleteAllResidences() {
	    System.out.println("Delete All Residences...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Residences have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/residences/{id}")
	  public ResponseEntity<Residence> updateResidence(@PathVariable("id") long id, @RequestBody Residence Residence) {
	    System.out.println("Update Residence with ID = " + id + "...");
	 
	    Optional<Residence> ResidenceInfo = repository.findById(id);
	 
	    if (ResidenceInfo.isPresent()) {
	    	Residence residence = ResidenceInfo.get();
	           residence.setLibelle(Residence.getLibelle());
	           residence.setCode(Residence.getCode()); 
	      return new ResponseEntity<>(repository.save(Residence), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	

}
