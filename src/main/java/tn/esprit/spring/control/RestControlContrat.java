package tn.esprit.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IContratService;

@RestController
public class RestControlContrat {

	
	//couplage faible
	@Autowired
	IContratService iContratservice;
	
	
	// Ajouter Contrat : http://localhost:8081/SpringMVC/servlet/ajouterContrat
	

	@PostMapping("/ajouterContrat")
	@ResponseBody
	public Long ajouterContrat(@RequestBody tn.esprit.spring.entities.Contrat contratRequest) {
		Contrat contrat = new Contrat();
		contrat.setDateDebut(contratRequest.getDateDebut());
		contrat.setReference(contratRequest.getReference());
		contrat.setSalaire(contratRequest.getSalaire());
		contrat.setTypeContrat(contratRequest.getTypeContrat());
		contrat.setEmploye(contratRequest.getEmploye());

		iContratservice.addContrat(contrat);
		return contrat.getReference();
	}
	
	
    // http://localhost:8081/SpringMVC/servlet/deleteContratById/1
    @DeleteMapping("/deleteContratById/{idContrat}") 
	@ResponseBody 
	public void deleteContratById(@PathVariable("idContrat")Long contratId)
	{
		iContratservice.deleteContratById(contratId);
	}
    
 // http://localhost:8081/SpringMVC/servlet/deleteContrat
    @DeleteMapping("/deleteContrat") 
	@ResponseBody 
	public void deleteContrat(@RequestBody tn.esprit.spring.entities.Contrat contratRequest)
	{
		iContratservice.deleteContrat(contratRequest);
	}
    
    // http://localhost:8081/SpringMVC/servlet/getContratById/1
    @GetMapping(value = "getContratById/{idContrat}")
    @ResponseBody
	public Contrat getContratById(@PathVariable("idContrat") Long contratId) {

		return iContratservice.getContratById(contratId);
	}
     	

	
 	 // http://localhost:8081/SpringMVC/servlet/getAllContrat
    @GetMapping(value = "/getAllContrat")
    @ResponseBody
	public List<Contrat> getAllContrat() {
		return iContratservice.getAllContrats();
	}
    
 // http://localhost:8081/SpringMVC/servlet/updateContrat
    @PostMapping("/updateContrat") 
	@ResponseBody 
	public void updateContrat(@RequestBody tn.esprit.spring.entities.Contrat contratRequest)
	{
		iContratservice.updateContrat(contratRequest);
	}

    
}
