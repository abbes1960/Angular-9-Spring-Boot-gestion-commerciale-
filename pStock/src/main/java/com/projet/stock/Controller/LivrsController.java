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
import com.projet.stock.model.Compteur;

import com.projet.stock.model.Livrs;
import com.projet.stock.model.Llivrs;
import com.projet.stock.repository.CompteurRepository;
import com.projet.stock.repository.ArticleRepository;
import com.projet.stock.repository.LivrsRepository;
import com.projet.stock.repository.LlivrsRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class LivrsController {
	@Autowired 	LivrsRepository repository;
	@Autowired LlivrsRepository repo;
	@Autowired CompteurRepository comptrepo;
	@Autowired ArticleRepository artRepository;
	@Autowired  ServletContext context;
	 @GetMapping("/livrss")
	  public List<Livrs> getAlllivrss() {
	    System.out.println("Get all livrss...");
	    List<Livrs> livrs = new ArrayList<>();
	    repository.findAll().forEach(livrs::add);
	    return livrs;
	  }
	
	
	@GetMapping("/livrss/{id}")
	public ResponseEntity<Livrs> getBonById(@PathVariable(value = "id") Long Id)
	
			throws ResourceNotFoundException {
		System.out.println("Get all livrss..."+ Id);
		Livrs livrs = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Bon not found for this id :: " + Id));
		List<Llivrs> llivrs = new ArrayList<>();
    	repo.findAllByNumero(livrs.getNumero()).forEach(llivrs::add);
    	livrs.setLlivrs(llivrs);
		return ResponseEntity.ok().body(livrs);
	}
	@PostMapping("/livrss")
	public ResponseEntity<Livrs> createlivrs(@Valid @RequestBody Livrs livrs)  throws JsonParseException , JsonMappingException , Exception{
		  System.out.println("Save livrss..."); 
		repository.save(livrs);
		  System.out.println("Save livrss...");
		  List<Llivrs> llivrs = livrs.getLlivrs();
		    for (Llivrs lc : llivrs) {
	          	lc.setNumero(livrs.getNumero());
	          	
	          	repo.save(lc);
		       }	 
		    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(livrs.getAnnee());
	     	if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNumbl(compteur.getNumbl()+1);
		         compteur =   comptrepo.save(compteur);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		} 
	
	
	
	@DeleteMapping("/livrss/{id}")
	public Map<String, Boolean> deletelivrs(@PathVariable(value = "id") Long livrsId)
			throws ResourceNotFoundException {
		Livrs livrs = repository.findById(livrsId)
				.orElseThrow(() -> new ResourceNotFoundException("livrs not found  id :: " + livrsId));
		repository.delete(livrs);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/livrss/delete")
	  public ResponseEntity<String> deleteAlllivrss() {
	    System.out.println("Delete All livrss...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All livrss have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/livrss/{id}")
	  public ResponseEntity<Livrs> updatelivrs(@PathVariable("id") long id, @RequestBody Livrs livrs) {
	    System.out.println("Update livrs with ID = " + id + "...");
	    Optional<Livrs> livrsInfo = repository.findById(id);
	    if (livrsInfo.isPresent()) {
	    	Livrs livr = livrsInfo.get();
	    	livr.setLibcl(livrs.getLibcl());
	      return new ResponseEntity<>(repository.save(livrs), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }	
}
