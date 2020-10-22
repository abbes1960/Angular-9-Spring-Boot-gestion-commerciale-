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
import com.projet.stock.model.Grade;
import com.projet.stock.repository.GradeRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class GradeController {
	@Autowired
	GradeRepository repository;
	
	 @GetMapping("/grades")
	  public List<Grade> getAllGrades() {
	    System.out.println("Get all Grades...");
	 
	    List<Grade> Grades = new ArrayList<>();
	    repository.findAll().forEach(Grades::add);
	 
	    return Grades;
	  }
	
	@GetMapping("/grades/{code}")
	public ResponseEntity<Grade> getGradeById(@PathVariable(value = "code") String code)
			throws ResourceNotFoundException {
		Grade Grade = repository.findByCode(code)
				.orElseThrow(() -> new ResourceNotFoundException("Grade not found for this id :: " + code));
		return ResponseEntity.ok().body(Grade);
	}

	@PostMapping("/grades")
	public Grade createGrade(@Valid @RequestBody Grade Grade) {
		return repository.save(Grade);
	}
	

	@DeleteMapping("/grades/{id}")
	public Map<String, Boolean> deleteGrade(@PathVariable(value = "id") Long GradeId)
			throws ResourceNotFoundException {
		Grade Grade = repository.findById(GradeId)
				.orElseThrow(() -> new ResourceNotFoundException("Grade not found  id :: " + GradeId));

		repository.delete(Grade);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/grades/delete")
	  public ResponseEntity<String> deleteAllGrades() {
	    System.out.println("Delete All Grades...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Grades have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/grades/{id}")
	  public ResponseEntity<Grade> updateGrade(@PathVariable("id") long id, @RequestBody Grade Grade) {
	    System.out.println("Update Grade with ID = " + id + "...");
	 
	    Optional<Grade> GradeInfo = repository.findById(id);
	 
	    if (GradeInfo.isPresent()) {
	    	Grade grade = GradeInfo.get();
	          grade.setLibelle(Grade.getLibelle());
	           
	      return new ResponseEntity<>(repository.save(Grade), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
