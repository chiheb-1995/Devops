package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Contrat;

public interface IContratService {

	public List<Contrat> getAllContrats();

	public Contrat addContrat(Contrat contrat);

	public void deleteContratById(Long idContrat);

	public void deleteContrat(Contrat contrat);

	public Contrat getContratById(Long idContrat);
	
	public Contrat updateContrat(Contrat contrat);

}
