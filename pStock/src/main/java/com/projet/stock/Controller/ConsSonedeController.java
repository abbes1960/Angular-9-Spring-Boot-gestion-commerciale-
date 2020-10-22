package com.projet.stock.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.projet.stock.exception.ResourceNotFoundException;
import com.projet.stock.model.Cposte;
import com.projet.stock.model.ConsSonede;
import com.projet.stock.model.LconsSonede;
import com.projet.stock.repository.CposteRepository;
import com.projet.stock.repository.ConsSonedeRepository;
import com.projet.stock.repository.LconsSonedeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ConsSonedeController {
	@Autowired 	ConsSonedeRepository repository;
	@Autowired LconsSonedeRepository repo;
	@Autowired CposteRepository comptrepo;
	@Autowired  ServletContext context;
	 @GetMapping("/sonedes")
	  public List<ConsSonede> getAllConsSonedes() {
	    System.out.println("Get all ConsSonedes...");
	    List<ConsSonede> ConsSonedes = new ArrayList<>();
	    repository.findAll().forEach(ConsSonedes::add);
	    return ConsSonedes;
	  }
	
	@GetMapping("/sonedes/{id}")
	public ResponseEntity<ConsSonede> getConsSonedeById(@PathVariable(value = "id") Long ConsSonedeId)
			throws ResourceNotFoundException {
		ConsSonede ConsSonede = repository.findById(ConsSonedeId)
				.orElseThrow(() -> new ResourceNotFoundException("ConsSonede not found for this id :: " + ConsSonedeId));
		return ResponseEntity.ok().body(ConsSonede);
	}

 	

	@PostMapping("/sonedes")
	public ResponseEntity<ConsSonede> createConsSonede(@Valid @RequestBody ConsSonede ConsSonede) 
			throws JsonParseException , JsonMappingException , Exception{
		repository.save(ConsSonede);
		List<LconsSonede> lconsSonedes = ConsSonede.getLconsSonedes();
	    for (LconsSonede lc : lconsSonedes) {
	        lc.setNumero(ConsSonede.getNumero());
	        lc.setAnnee(ConsSonede.getAnnee());
	        lc.setMois(ConsSonede.getMois());
       		repo.save(lc);
       		System.out.println("valid LConsSonedes...");
	       }
	    Optional<Cposte> CposteInfo = comptrepo.findByAnnee(ConsSonede.getAnnee());
     	if (CposteInfo.isPresent()) {
	    	Cposte Cposte = CposteInfo.get();
	           Cposte.setNumcsonede(Cposte.getNumcsonede()+1);
	         Cposte =   comptrepo.save(Cposte);
     	}
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@DeleteMapping("/sonedes/{id}")
	public Map<String, Boolean> deleteConsSonede(@PathVariable(value = "id") Long ConsSonedeId)
			throws ResourceNotFoundException {
		ConsSonede ConsSonede = repository.findById(ConsSonedeId)
				.orElseThrow(() -> new ResourceNotFoundException("ConsSonede not found  id :: " + ConsSonedeId));
		repository.delete(ConsSonede);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/sonedes/delete")
	  public ResponseEntity<String> deleteAllConsSonedes() {
	    System.out.println("Delete All ConsSonedes...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All ConsSonedes have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/sonedes/{id}")
	  public ResponseEntity<ConsSonede> updateConsSonede(@PathVariable("id") long id, @RequestBody ConsSonede ConsSonede) {
	    System.out.println("Update ConsSonede with ID = " + id + "...");
	    Optional<ConsSonede> ConsSonedeInfo = repository.findById(id);
	    if (ConsSonedeInfo.isPresent()) {
	    	ConsSonede consSonede = ConsSonedeInfo.get();
	    	consSonede.setLibelle(ConsSonede.getLibelle());
	      return new ResponseEntity<>(repository.save(ConsSonede), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
