package tn.esprit.spring.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;

@Service
public class EmployeServiceImpl implements IEmployeService {

	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
    
    @Autowired
    EmployeRepository employeRepository;
    
    public List<Employe> getAllEmployes() {
    	List<Employe> employes = null;
    	try {
    		l.info("In Method getAllEmployes :");
    		employes = (List<Employe>) employeRepository.findAll();
    		l.debug("connexion à la DB Ok :");
    		for (Employe employe : employes) {
				l.debug("employe :" + employe.getNom());
    	}
    	l.info("Out of Method getAllEmployes with Success" + employes.size());
    	} catch (Exception e) {
    		l.error("Out of Method retrieveAllContrats with Errors : " + e);
		}
    	return (List<Employe>) employeRepository.findAll();
        
    }
    
    @Override
	public int addEmploye(Employe employe) {
		
		Employe employeSaved = null; 
		
		try {
			l.info("In Method addEmploye :"); 
			employeSaved = employeRepository.save(employe); 
			l.info("Out of Method addEmploye with Success");
		} catch (Exception e) {
			l.error("Out of Method addEmploye with Errors : " + e);
		}
		
		return employeSaved.getId(); 
	}

	@Override
	public Employe updateEmploye(Employe employe) {
		Employe employeUpdated = null; 
		
		try {
			l.info("In Method updateEmploye :"); 
			employeUpdated = employeRepository.save(employe); 
			l.info("Out of method updateEmploye With Success");
			
		} catch (Exception e) {
			l.error("Out of Method updateEmploye with Errors : " + e);
		}
		
		return updateEmploye(employeUpdated); 
	}

	@Override
	public void deleteEmploye(int id) {
		try {
			l.info("In Method deleteEmploye");
			employeRepository.deleteById(id);
			l.info("Out of Method deleteEmploye with Success");
			
		} catch (Exception e) {
			l.error("Out of Method deleteEmploye with Errors : " + e);
		}
		
	}

	@Override
	public Employe retrieveEmploye(int id) {
		Employe employe = null; 
		try {
			l.info("In Method retrieveEmploye");
			employe =  employeRepository.findById(id).get(); ; 
			l.info("Out of Method retrieveEmploye with Success");
			
		} catch (Exception e) {
			l.error("Error in retrieveEmploye : " + e);
		}

		return employe;
	}
	
}