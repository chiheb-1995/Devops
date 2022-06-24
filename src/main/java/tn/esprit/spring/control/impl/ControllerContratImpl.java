package tn.esprit.spring.control.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IContratService;

import java.util.List;


@Controller
public class ControllerContratImpl {

    @Autowired
    IContratService contratService;

    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }

    public Contrat getContratById(Long ref) {
        return contratService.getContratById(ref);
    }

    public void deleteContrat(Contrat contrat) {
    	contratService.deleteContrat(contrat);
    }

    public void deleteContratById(Long ref) {
    	contratService.deleteContratById(ref);
    }

    public Contrat addContrat(Contrat contrat) {
        return contratService.addContrat(contrat);
    }
    public Contrat updateContrat(Contrat contrat) {
        return contratService.updateContrat(contrat);

    }
}
