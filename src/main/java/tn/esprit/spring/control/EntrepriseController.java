package tn.esprit.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimeSheetService;

@RestController
public class EntrepriseController {
    @Autowired
    IEmployeService iemployeservice;
    @Autowired
    IEntrepriseService ientrepriseservice;
    @Autowired
    ITimeSheetService itimesheetservice;

    // Ajouter Entreprise : http://localhost:8081/SpringMVC/servlet/ajouterEntreprise


    @PostMapping("/ajouterEntreprise")
    @ResponseBody
    public long ajouterEntreprise(@RequestBody Entreprise e) {
        Entreprise entreprise = new Entreprise();
        entreprise.setName(e.getName());
        entreprise.setRaisonSocial(e.getRaisonSocial());
        entreprise.setDepartements(e.getDepartements());
        ientrepriseservice.ajouterEntreprise(entreprise);
        return entreprise.getId();
    }

    // http://localhost:8081/SpringMVC/servlet/affecterDepartementAEntreprise/1/1
    @PutMapping(value = "/affecterDepartementAEntreprise/{iddept}/{identreprise}")
    public void affecterDepartementAEntreprise(@PathVariable("iddept")int depId, @PathVariable("identreprise")int entrepriseId) {
        ientrepriseservice.affecterDepartementAEntreprise(depId, entrepriseId);
    }

    // http://localhost:8081/SpringMVC/servlet/deleteEntrepriseById/1
    @DeleteMapping("/deleteEntrepriseById/{identreprise}")
    @ResponseBody
    public void deleteEntrepriseById(@PathVariable("identreprise")int entrepriseId)
    {
        ientrepriseservice.deleteEntrepriseById(entrepriseId);
    }

    // http://localhost:8081/SpringMVC/servlet/getEntrepriseById/1
    @GetMapping(value = "getEntrepriseById/{identreprise}")
    @ResponseBody
    public Entreprise getEntrepriseById(@PathVariable("identreprise") int entrepriseId) {

        return ientrepriseservice.getEntrepriseById(entrepriseId);
    }

    // http://localhost:8081/SpringMVC/servlet/ajouterDepartement


    @PostMapping("/ajouterDepartement")
    @ResponseBody
    public long ajouterDepartement(@RequestBody Departement departementRequest) {
        Departement departement = new Departement();
        departement.setName(departementRequest.getName());
        departement.setEmployes(departementRequest.getEmployes());
        departement.setMissions(departementRequest.getMissions());
        return ientrepriseservice.ajouterDepartement(departement);
    }

    // http://localhost:8081/SpringMVC/servlet/getAllDepartementsNamesByEntreprise/1
    @GetMapping(value = "getAllDepartementsNamesByEntreprise/{identreprise}")
    @ResponseBody
    public List<String> getAllDepartementsNamesByEntreprise(@PathVariable("identreprise") int entrepriseId) {
        return ientrepriseservice.getAllDepartementsNamesByEntreprise(entrepriseId);
    }

    // URL : http://localhost:8081/SpringMVC/servlet/deleteDepartementById/3
    @DeleteMapping("/deleteDepartementById/{iddept}")
    @ResponseBody
    public void deleteDepartementById(@PathVariable("iddept") int depId) {
        ientrepriseservice.deleteDepartementById(depId);

    }
}
