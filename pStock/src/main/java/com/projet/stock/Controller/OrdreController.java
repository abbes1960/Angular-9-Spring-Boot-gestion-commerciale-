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
import com.projet.stock.model.Ordre;
import com.projet.stock.model.Bon;
import com.projet.stock.model.Cposte;
import com.projet.stock.model.Lbon;
import com.projet.stock.model.Lordre;
import com.projet.stock.repository.OrdreRepository;
import com.projet.stock.repository.CposteRepository;
import com.projet.stock.repository.LordreRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class OrdreController {
	@Autowired 	OrdreRepository repository;
	@Autowired  CposteRepository cposterepo;
	@Autowired  LordreRepository repo;
	@Autowired  ServletContext context;
	@GetMapping("/Ordres")
	  public List<Ordre> getAlLordre() {
	    System.out.println("Get all Ordres...");
	    List<Ordre> Ordres = new ArrayList<>();
	    repository.findAll().forEach(Ordres::add);
	    return Ordres;
	  }
	
	@GetMapping("/Ordres/{id}")
	public ResponseEntity<Ordre> getOrdreById(@PathVariable(value = "id") Long OrdreId)
			throws ResourceNotFoundException {
		Ordre Ordre = repository.findById(OrdreId)
				.orElseThrow(() -> new ResourceNotFoundException("Ordre not found for this id :: " + OrdreId));
		List<Lordre> Lordres = new ArrayList<>();
    	repo.findAllByNumero(Ordre.getNumero()).forEach(Lordres::add);
    	Ordre.setLordres(Lordres);
		return ResponseEntity.ok().body(Ordre);
	}
	@DeleteMapping("/Ordres/{id}")
	public Map<String, Boolean> deleteOrdre(@PathVariable(value = "id") Long OrdreId)
			throws ResourceNotFoundException {
		Ordre Ordre = repository.findById(OrdreId)
				.orElseThrow(() -> new ResourceNotFoundException("Ordre not found  id :: " + OrdreId));
		repository.delete(Ordre);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/Ordres/delete")
	  public ResponseEntity<String> deleteAlLordres() {
	    System.out.println("Delete All Ordres...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Ordres have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/Ordres/{id}")
	  public ResponseEntity<Ordre> updateOrdre(@PathVariable("id") long id, @RequestBody Ordre Ordre) {
	    System.out.println("Update Ordre with ID = " + id + "...");
	    Optional<Ordre> OrdreInfo = repository.findById(id);
	    if (OrdreInfo.isPresent()) {
	    	Ordre ordre = OrdreInfo.get();
	    	ordre.setLibelle(Ordre.getLibelle());
	      return new ResponseEntity<>(repository.save(Ordre), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	
	  @PostMapping("/Ordres")
		public ResponseEntity<Ordre> createOrdre(@Valid @RequestBody Ordre Ordre)  throws JsonParseException , JsonMappingException , Exception{
		  System.out.println("ffffgfgfggf");
		  repository.save(Ordre);
		  List<Lordre> Lordres = Ordre.getLordres();
		    for (Lordre lc : Lordres) {
	          	lc.setNumero(Ordre.getNumero());
	          	repo.save(lc);
		       }	 
		    Optional<Cposte> CposteInfo = cposterepo.findByAnnee(Ordre.getAnnee());
	     	if (CposteInfo.isPresent()) {
		    	Cposte Cposte = CposteInfo.get();
		           Cposte.setNumpro(Cposte.getNumpro()+1);
		         Cposte =   cposterepo.save(Cposte);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		}
}






