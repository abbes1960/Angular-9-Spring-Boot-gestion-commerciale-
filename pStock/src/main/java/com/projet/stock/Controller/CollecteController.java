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
import com.projet.stock.model.Bon;
import com.projet.stock.model.Collecte;
import com.projet.stock.model.Cposte;
import com.projet.stock.model.Lbon;
import com.projet.stock.model.Lcollecte;
import com.projet.stock.repository.CollecteRepository;
import com.projet.stock.repository.CposteRepository;
import com.projet.stock.repository.LcollecteRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CollecteController {
	@Autowired 	CollecteRepository repository;
	@Autowired  CposteRepository cposterepo;
	@Autowired  LcollecteRepository repo;
	@Autowired  ServletContext context;
	
	@GetMapping("/collectes")
	public List<Collecte> getAllCollecte() {
	    System.out.println("Get all Collectes...");
	    List<Collecte> Collectes = new ArrayList<>();
	    repository.findAll().forEach(Collectes::add);
	    return Collectes;
	  }
	@GetMapping("/collectes/{id}")
	public ResponseEntity<Collecte> getCollecteById(@PathVariable(value = "id") Long Id)
	
			throws ResourceNotFoundException {
		Collecte collecte = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Collecte not found for this id :: " + Id));
		List<Lcollecte> lcollectes = new ArrayList<>();
    	repo.findAllByNumero(collecte.getNumero()).forEach(lcollectes::add);
    	collecte.setLcollectes(lcollectes);
		return ResponseEntity.ok().body(collecte);
	}

	@DeleteMapping("/collectes/{id}")
	public Map<String, Boolean> deleteCollecte(@PathVariable(value = "id") Long CollecteId)
			throws ResourceNotFoundException {
		Collecte Collecte = repository.findById(CollecteId)
				.orElseThrow(() -> new ResourceNotFoundException("Collecte not found  id :: " + CollecteId));
		repository.delete(Collecte);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/collectes/delete")
	  public ResponseEntity<String> deleteAllCollectes() {
	    System.out.println("Delete All Collectes...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Collectes have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/collectes/{id}")
	  public ResponseEntity<Collecte> updateCollecte(@PathVariable("id") long id, @RequestBody Collecte Collecte) {
	    System.out.println("Update Collecte with ID = " + id + "...");
	    Optional<Collecte> CollecteInfo = repository.findById(id);
	    if (CollecteInfo.isPresent()) {
	    	Collecte collecte = CollecteInfo.get();
	    	collecte.setLibelle(Collecte.getLibelle());
	      return new ResponseEntity<>(repository.save(Collecte), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	  @PostMapping("/collectes")
		public ResponseEntity<Collecte> createCollecte(@Valid @RequestBody Collecte Collecte)  throws JsonParseException , JsonMappingException , Exception{
		  System.out.println(" save data");
		  repository.save(Collecte);
		  List<Lcollecte> lcollectes = Collecte.getLcollectes();
		    for (Lcollecte lc : lcollectes) {
	          	lc.setNumero(Collecte.getNumero());
	          	 System.out.println(" save data ligne");
	          	repo.save(lc);
		       }	 
		    Optional<Cposte> CposteInfo = cposterepo.findByAnnee(Collecte.getAnnee());
	     	if (CposteInfo.isPresent()) {
		    	Cposte Cposte = CposteInfo.get();
		        Cposte.setNumcollecte(Cposte.getNumcollecte()+1);
		        Cposte =   cposterepo.save(Cposte);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
			 
		}

}
