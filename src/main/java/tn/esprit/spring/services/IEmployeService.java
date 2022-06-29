package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Employe;


public interface IEmployeService {

	public List<Employe> getAllEmployes();
	public int addEmploye(Employe employe);
	public Employe updateEmploye(Employe employe);
	public void deleteEmploye(int id);
	public Employe retrieveEmploye(int id);
	
    
}