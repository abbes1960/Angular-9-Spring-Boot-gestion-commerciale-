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
import com.projet.stock.model.UserPoste;
import com.projet.stock.repository.UserPosteRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserPosteController {
	@Autowired
	UserPosteRepository repository;
	 @GetMapping("/usersp")
	  public List<UserPoste> getAllUserPoste() {
	    System.out.println("Get all UserPoste...");
	    List<UserPoste> UserPoste = new ArrayList<>();
	    repository.findAll().forEach(UserPoste::add);
	    return UserPoste;
	  }
	@GetMapping("/usersp/{id}")
	public ResponseEntity<UserPoste> getUserPosteById(@PathVariable(value = "id") Long UserPosteId)
			throws ResourceNotFoundException {
		UserPoste UserPoste = repository.findById(UserPosteId)
				.orElseThrow(() -> new ResourceNotFoundException("UserPoste not found for this id :: " + UserPosteId));
		return ResponseEntity.ok().body(UserPoste);
	}

	 
	 
	
	@PostMapping("/usersp")
	public UserPoste createUserPoste(@Valid @RequestBody UserPoste UserPoste) {
		return repository.save(UserPoste);
	}
	
	@GetMapping("/usersp/5/{mat}")
	  public   ResponseEntity<UserPoste> getUtilisateurByMat(@PathVariable int mat) 
		  throws ResourceNotFoundException {
		 System.out.println(mat);
		UserPoste UserPoste = repository.findByMat(mat)
				  .orElseThrow(() -> new ResourceNotFoundException("Usernot found for this Mat  : " + mat));
		   return ResponseEntity.ok().body(UserPoste);
	  } 
	
	@DeleteMapping("/usersp/{id}")
	public Map<String, Boolean> deleteUserPoste(@PathVariable(value = "id") Long UserPosteId)
			throws ResourceNotFoundException {
		UserPoste UserPoste = repository.findById(UserPosteId)
				.orElseThrow(() -> new ResourceNotFoundException("UserPoste not found  id :: " + UserPosteId));

		repository.delete(UserPoste);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/usersp/delete")
	  public ResponseEntity<String> deleteAllUserPoste() {
	    System.out.println("Delete All UserPoste...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All UserPostes have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/usersp/{id}")
	  public ResponseEntity<UserPoste> updateUserPoste(@PathVariable("id") long id, @RequestBody UserPoste UserPoste) {
	    System.out.println("Update UserPoste with ID = " + id + "...");
	 
	    Optional<UserPoste> UserPosteInfo = repository.findById(id);
	 
	    if (UserPosteInfo.isPresent()) {
	    	UserPoste userPoste = UserPosteInfo.get();
	    	userPoste.setRole(UserPoste.getRole());
	    	userPoste.setNom(UserPoste.getNom());
	    	userPoste.setMat(UserPoste.getMat()); 
	    	userPoste.setPwd(UserPoste.getPwd());
	      return new ResponseEntity<>(repository.save(UserPoste), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }	

}
