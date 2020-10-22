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
import com.projet.stock.model.Avoir;
import com.projet.stock.model.Compteur;
import com.projet.stock.model.Favoir;
import com.projet.stock.model.Lavoir;
import com.projet.stock.model.Lfavoir;
import com.projet.stock.model.Residence;
import com.projet.stock.repository.CompteurRepository;
import com.projet.stock.repository.FavoirRepository;
import com.projet.stock.repository.LfavoirRepository;
import com.projet.stock.repository.ResidenceRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FavoirController {
	@Autowired 	FavoirRepository repository;
	@Autowired LfavoirRepository repo;
	@Autowired CompteurRepository comptrepo;
	@Autowired  ServletContext context;
	 @GetMapping("/favoirs")
	  public List<Favoir> getAllFavoirs() {
	    System.out.println("Get all Favoirs...");
	    List<Favoir> Favoirs = new ArrayList<>();
	    repository.findAll().forEach(Favoirs::add);
	    return Favoirs;
	  }
	
	@GetMapping("/favoirs/{id}")
	public ResponseEntity<Favoir> getFavoirById(@PathVariable(value = "id") Long FavoirId)
			throws ResourceNotFoundException {
		Favoir Favoir = repository.findById(FavoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Favoir not found for this id :: " + FavoirId));
		return ResponseEntity.ok().body(Favoir);
	}

	@PostMapping("/favoirs")
	public ResponseEntity<Favoir> createAvoir(@Valid @RequestBody Favoir Favoir)  throws JsonParseException , JsonMappingException , Exception{
		repository.save(Favoir);
		List<Lfavoir> lfavoirs = Favoir.getLfavoirs();
	    for (Lfavoir lc : lfavoirs) {
	        lc.setNumero(Favoir.getNumero());
       		repo.save(lc);
	       }	 
     Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Favoir.getAnnee());
     	if (CompteurInfo.isPresent()) {
	    	Compteur compteur = CompteurInfo.get();
	           compteur.setNumav(compteur.getNumavf()+1);
	         compteur =   comptrepo.save(compteur);
     	}
		 return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/favoirs/{id}")
	public Map<String, Boolean> deleteFavoir(@PathVariable(value = "id") Long FavoirId)
			throws ResourceNotFoundException {
		Favoir Favoir = repository.findById(FavoirId)
				.orElseThrow(() -> new ResourceNotFoundException("Favoir not found  id :: " + FavoirId));
		repository.delete(Favoir);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/favoirs/delete")
	  public ResponseEntity<String> deleteAllFavoirs() {
	    System.out.println("Delete All Favoirs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Favoirs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/favoirs/{id}")
	  public ResponseEntity<Favoir> updateFavoir(@PathVariable("id") long id, @RequestBody Favoir Favoir) {
	    System.out.println("Update Favoir with ID = " + id + "...");
	    Optional<Favoir> FavoirInfo = repository.findById(id);
	    if (FavoirInfo.isPresent()) {
	    	Favoir favoir = FavoirInfo.get();
	    	favoir.setLibelle(Favoir.getLibelle());
	      return new ResponseEntity<>(repository.save(Favoir), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
