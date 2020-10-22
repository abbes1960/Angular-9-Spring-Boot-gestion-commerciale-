package com.projet.stock.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.stock.domaine.Message;
import com.projet.stock.exception.ResourceNotFoundException;
import com.projet.stock.model.Article;
import com.projet.stock.model.Comm;
import com.projet.stock.model.Departement;
import com.projet.stock.model.Lcomm;
import com.projet.stock.model.Patrimoine;
import com.projet.stock.model.TypePatrimoine;
import com.projet.stock.repository.DepartementRepository;
import com.projet.stock.repository.PatrimoineRepository;
import com.projet.stock.repository.TypePatrimoineRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PatrimoineController {
	@Autowired 	PatrimoineRepository  repository;
	@Autowired 	TypePatrimoineRepository  repo;
	@Autowired 	DepartementRepository  deprepo;
	@Autowired  ServletContext context;
	
	@GetMapping("/patrimoines")
	public List<Patrimoine> getAllPatrimoines() {
	System.out.println("Get all Patrimoines...");
	List<Patrimoine> Patrimoines = new ArrayList<>();
	repository.findAll().forEach(Patrimoines::add);		 
	return Patrimoines;
	}
	
		
	@PostMapping("/patrimoines")
	public ResponseEntity<Message> createPatrimoine (@RequestParam("file") MultipartFile file,
	@RequestParam("patrimoine") String patrimoine) throws JsonParseException , JsonMappingException , Exception
	{
		System.out.println("Ok .............");
	    Patrimoine patri = new ObjectMapper().readValue(patrimoine, Patrimoine.class);
	    boolean isExit = new File(context.getRealPath("/ImgPatrimoines/")).exists();
	    if (!isExit)
	    {
	    new File (context.getRealPath("/ImgPatrimoines/")).mkdir();
	    System.out.println("mk dir.............");
	    }
	    String filename = file.getOriginalFilename();
	    String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
	    File serverFile = new File (context.getRealPath("/ImgPatrimoines/"+File.separator+newFileName));
	    try
	    {
	    System.out.println("Image");
	    FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
	        	 
	    }catch(Exception e) {
	        	e.printStackTrace();
	    }

	     patri.setImage(newFileName);
	     Patrimoine pat = repository.save(patri);
	     if (pat != null)
	     {
	    	 List<Departement> ldepartements = pat.getLdepartements();
	 	    for (Departement dep : ldepartements){
	 	        dep.setCode(pat.getCode());
	 	        deprepo.save(dep);
	 	       } 


	    	 Optional<TypePatrimoine> TypeInfo = repo.findByCode(pat.getTypep());
          	 
	    	    if (TypeInfo.isPresent()) {
	    	    	TypePatrimoine typePatrimoine = TypeInfo.get();
	    	    	typePatrimoine.setRang(typePatrimoine.getRang()+1);
	    	    	typePatrimoine = repo.save(typePatrimoine);
	    	    } 
	      return new ResponseEntity<Message>(new Message (""),HttpStatus.OK);
	     }
	        else
	     {
	      return new ResponseEntity<Message>(new Message ("Patrimoine not saved"),HttpStatus.BAD_REQUEST);	
	     }
		 }
	
	
	
		 
		 @GetMapping("/patrimoines/{id}")
			public ResponseEntity<Patrimoine> getPatrimoineById(@PathVariable(value = "id") Long Id)
					throws ResourceNotFoundException {
				Patrimoine Patrimoine = repository.findById(Id)
						.orElseThrow(() -> new ResourceNotFoundException("Patrimoine not found for this id :: " + Id));
				return ResponseEntity.ok().body(Patrimoine);
			}
		 
		 @GetMapping(path="/imgPatrimoines/{id}")
		 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
			 Patrimoine Patrimoine   = repository.findById(id).get();
			 return Files.readAllBytes(Paths.get(context.getRealPath("/ImgPatrimoines/")+Patrimoine.getImage()));
		 }
		
		
					

		@DeleteMapping("/patrimoines/{id}")
		public Map<String, Boolean> deletePatrimoine(@PathVariable(value = "id") Long PatrimoineId)
				throws ResourceNotFoundException {
			Patrimoine Patrimoine = repository.findById(PatrimoineId)
					.orElseThrow(() -> new ResourceNotFoundException("Patrimoine not found  id :: " + PatrimoineId));
			String wcode = Patrimoine.getCode();
			repository.delete(Patrimoine);
			deprepo.deletedeprat(wcode);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}
		  	 
		@DeleteMapping("/patrimoines/delete")
		  public ResponseEntity<String> deleteAllPatrimoines() {
		    System.out.println("Delete All Patrimoines...");
		    repository.deleteAll();
		    return new ResponseEntity<>("All Patrimoines have been deleted!", HttpStatus.OK);
		}

		  @PutMapping("/patrimoines/{id}")
		  public ResponseEntity<Patrimoine> updatePatrimoine(@PathVariable("id") long id, @RequestBody Patrimoine Patrimoine) {
		    System.out.println("Update Patrimoine with ID = " + id + "...");
		    Optional<Patrimoine> PatrimoineInfo = repository.findById(id);
		    if (PatrimoineInfo.isPresent()) {
		    	Patrimoine patrimoine = PatrimoineInfo.get();
		           patrimoine.setLibelle(Patrimoine.getLibelle());
		           patrimoine.setAdresse(Patrimoine.getAdresse());
		           patrimoine.setAnnee(Patrimoine.getAnnee());
		           patrimoine.setCode_dir(Patrimoine.getCode_dir());
		           patrimoine.setContrat(Patrimoine.getContrat());
		           patrimoine.setCouvert(Patrimoine.getCouvert());
		           patrimoine.setSuperficie(Patrimoine.getSuperficie());
		           patrimoine.setNature(Patrimoine.getNature());
		           patrimoine.setLat(Patrimoine.getLat());
		           patrimoine.setLng(Patrimoine.getLng());
		           patrimoine.setNum(Patrimoine.getNum());
		           patrimoine.setProprietaire(Patrimoine.getProprietaire());
		           patrimoine.setPv(Patrimoine.getPv());
		           patrimoine.setMontant(Patrimoine.getMontant());
		           patrimoine.setTypep(Patrimoine.getTypep());
		           patrimoine.setVille(Patrimoine.getVille());
		           patrimoine.setLgf(Patrimoine.getLgf());
		           patrimoine.setChs(Patrimoine.getChs());
		      return new ResponseEntity<>(repository.save(Patrimoine), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }

}
