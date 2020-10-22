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
import com.projet.stock.model.Article;
import com.projet.stock.model.Livr;
import com.projet.stock.model.Llivr;
import com.projet.stock.repository.CompteurRepository;
import com.projet.stock.repository.ArticleRepository;
import com.projet.stock.repository.LivrRepository;
import com.projet.stock.repository.LlivrRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LivrController {
	@Autowired 	LivrRepository repository;
	@Autowired LlivrRepository repo;
	@Autowired CompteurRepository comptrepo;
	@Autowired ArticleRepository artRepository;
	@Autowired  ServletContext context;
	 @GetMapping("/livrs")
	  public List<Livr> getAllLivrs() {
	    System.out.println("Get all Livrs...");
	    List<Livr> Livrs = new ArrayList<>();
	    repository.findAll().forEach(Livrs::add);
	    return Livrs;
	  }
	
	
	@GetMapping("/livrs/{id}")
	public ResponseEntity<Livr> getBonById(@PathVariable(value = "id") Long LivrId)
	
			throws ResourceNotFoundException {
		Livr Livr = repository.findById(LivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Bon not found for this id :: " + LivrId));
		List<Llivr> llivrs = new ArrayList<>();
    	repo.findAllByNumero(Livr.getNumero()).forEach(llivrs::add);
    	Livr.setLlivrs(llivrs);
		return ResponseEntity.ok().body(Livr);
	}
	@PostMapping("/livrs")
	public ResponseEntity<Livr> createLivr(@Valid @RequestBody Livr Livr)  throws JsonParseException , JsonMappingException , Exception{
		  System.out.println("Save Livrs..."); 
		repository.save(Livr);
		  System.out.println("Save Livrs...");
		  List<Llivr> llivrs = Livr.getLlivrs();
		    for (Llivr lc : llivrs) {
	          	lc.setNumero(Livr.getNumero());
	          	Optional<Article> ArticleInfo = artRepository.findByCode(lc.getCode());
		     	if (ArticleInfo.isPresent()) {
			    	Article art = ArticleInfo.get();
			           art.setStock(art.getStock()-lc.getQte());
			           art =   artRepository.save(art);
		     	}
	          	repo.save(lc);
		       }	 
		    Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Livr.getAnnee());
	     	if (CompteurInfo.isPresent()) {
		    	Compteur compteur = CompteurInfo.get();
		           compteur.setNumbl(compteur.getNumbl()+1);
		         compteur =   comptrepo.save(compteur);
	     	}
			 return new ResponseEntity<>(HttpStatus.OK);
		} 
	
	
	
	@DeleteMapping("/livrs/{id}")
	public Map<String, Boolean> deleteLivr(@PathVariable(value = "id") Long LivrId)
			throws ResourceNotFoundException {
		Livr Livr = repository.findById(LivrId)
				.orElseThrow(() -> new ResourceNotFoundException("Livr not found  id :: " + LivrId));
		repository.delete(Livr);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	 
	  @DeleteMapping("/livrs/delete")
	  public ResponseEntity<String> deleteAllLivrs() {
	    System.out.println("Delete All Livrs...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Livrs have been deleted!", HttpStatus.OK);
	  }
	 
	  @PutMapping("/livrs/{id}")
	  public ResponseEntity<Livr> updateLivr(@PathVariable("id") long id, @RequestBody Livr Livr) {
	    System.out.println("Update Livr with ID = " + id + "...");
	    Optional<Livr> LivrInfo = repository.findById(id);
	    if (LivrInfo.isPresent()) {
	    	Livr livr = LivrInfo.get();
	    	livr.setLibcl(Livr.getLibcl());
	      return new ResponseEntity<>(repository.save(Livr), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }	
}
