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
import com.projet.stock.model.Agent;
import com.projet.stock.repository.AgentRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AgentController {
	@Autowired 	AgentRepository repository;
	
	 @GetMapping("/agents")
	  public List<Agent> getAllAgents() {
	    System.out.println("Get all Agents...");
	 
	    List<Agent> Agents = new ArrayList<>();
	    repository.findAll().forEach(Agents::add);
	 
	    return Agents;
	  }
	
	@GetMapping("/agents/{id}")
	public ResponseEntity<Agent> getAgentById(@PathVariable(value = "id") Long AgentId)
			throws ResourceNotFoundException {
		Agent Agent = repository.findById(AgentId)
				.orElseThrow(() -> new ResourceNotFoundException("Agent not found for this id :: " + AgentId));
		return ResponseEntity.ok().body(Agent);
	}

	@PostMapping("/agents")
	public Agent createAgent(@Valid @RequestBody Agent Agent) {
		return repository.save(Agent);
	}
	

	@DeleteMapping("/agents/{id}")
	public Map<String, Boolean> deleteAgent(@PathVariable(value = "id") Long AgentId)
			throws ResourceNotFoundException {
		Agent Agent = repository.findById(AgentId)
				.orElseThrow(() -> new ResourceNotFoundException("Agent not found  id :: " + AgentId));

		repository.delete(Agent);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/agents/delete")
	  public ResponseEntity<String> deleteAllAgents() {
	    System.out.println("Delete All Agents...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Agents have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/agents/{id}")
	  public ResponseEntity<Agent> updateAgent(@PathVariable("id") long id, @RequestBody Agent Agent) {
	    System.out.println("Update Agent with ID = " + id + "...");
	 
	    Optional<Agent> AgentInfo = repository.findById(id);
	 
	    if (AgentInfo.isPresent()) {
	    	Agent agent = AgentInfo.get();
	          agent.setNom(Agent.getNom());
	          agent.setCode(Agent.getCode());
	           
	      return new ResponseEntity<>(repository.save(Agent), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
