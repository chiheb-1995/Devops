package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {

    public long ajouterEntreprise(Entreprise entreprise);
    public long ajouterDepartement(Departement dep);
    void affecterDepartementAEntreprise(long depId, long entrepriseId);
    List<String> getAllDepartementsNamesByEntreprise(long entrepriseId);
    public void deleteEntrepriseById(long entrepriseId);
    public void deleteDepartementById(long depId);
    public Entreprise getEntrepriseById(long entrepriseId);
    public List<Entreprise> getAllEntreprises();
}
