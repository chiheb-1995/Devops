package tn.esprit.spring.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
    private static final Logger l = LogManager.getLogger(EntrepriseServiceImpl.class);

    @Autowired
    EntrepriseRepository entrepriseRepoistory;
    @Autowired
    DepartementRepository deptRepository;

    public long ajouterEntreprise(Entreprise entreprise) {
        l.debug("Add Enterprise");
        try {
            entrepriseRepoistory.save(entreprise);
        }
        catch (Exception e) {
            l.error("create enterprise error.", e.getMessage());
        }
        l.info("create enterprise :"+entreprise.getName()+"added SUCCESSFULLY");
        return entreprise.getId();
    }

    public long ajouterDepartement(Departement dep) {
        l.debug("Add Department");
        try {
            deptRepository.save(dep);
        }
        catch (Exception e) {
            l.error("create department error.", e.getMessage());
        }
        l.info("create department :"+dep.getName()+"added SUCCESSFULLY");
        return dep.getId();
    }

    public void affecterDepartementAEntreprise(long depId, long entrepriseId) {
        try {
            Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
            Departement depManagedEntity = deptRepository.findById(depId).orElse(null);
            if(depManagedEntity!=null) {
                if(depManagedEntity.getEntreprise()==null) {
                    depManagedEntity.setEntreprise(entrepriseManagedEntity);
                }else {
                    depManagedEntity.getEntreprise().addDepartement(depManagedEntity);
                }
            }
            deptRepository.save(depManagedEntity);
        }catch(Exception e){
            l.error("Departement cannot be affected to enterprise");
        }

    }

    public List<String> getAllDepartementsNamesByEntreprise(long entrepriseId) {
        Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
        List<String> depNames = new ArrayList<>();
        if(entrepriseManagedEntity!=null) {
            for(Departement dep : entrepriseManagedEntity.getDepartements()){
                depNames.add(dep.getName());
            }
        }
        return depNames;
    }

    @Transactional
    public void deleteEntrepriseById(long entrepriseId) {
        l.debug("Delete Enterprise");
        entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).orElse(null));
    }

    @Transactional
    public void deleteDepartementById(long depId) {
        l.debug("Delete Department ");
        deptRepository.delete(deptRepository.findById(depId).orElse(null));
    }

    @Transactional
    public Entreprise getEntrepriseById(long entrepriseId) {
        return entrepriseRepoistory.findById(entrepriseId).orElse(null);
    }

    public List<Entreprise> getAllEntreprises(){
        Iterable<Entreprise> iterable = entrepriseRepoistory.findAll();
        List<Entreprise> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }
}
