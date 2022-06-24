package tn.esprit.spring.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.IContratService;

@Service
public class ContratServiceImpl implements IContratService {

	@Autowired
	ContratRepository contratRepository;

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	public List<Contrat> getAllContrats() {

		List<Contrat> contrats = null;
		try {

			logger.info("In Method retrieveAllContrats :");
			contrats = (List<Contrat>) contratRepository.findAll();
			logger.debug("connexion à la DB Ok :");
			for (Contrat contrat : contrats) {
				logger.debug("contrat :" + contrat.getTypeContrat());
			}
			logger.info("Out of Method retrieveAllContrats with Success" + contrats.size());
		} catch (Exception e) {
			logger.error("Out of Method retrieveAllContrats with Errors : " + e);
		}

		return contrats;
	}

	@Override
	public Contrat addContrat(Contrat contrat) {
		Contrat contratSaved = null;

		try {
			logger.info("In method addContrat");
			contratSaved = contratRepository.save(contrat);
			logger.info("Out of method addContrat");

		} catch (Exception e) {
			logger.error("Out of Method addContrat with Errors : " + e);
		}

		return contratSaved;
	}

	@Override
	public Contrat updateContrat(Contrat contrat) {
		Contrat contratUpdated = null;

		try {
			logger.info("In method updateContrat");
			contratUpdated = contratRepository.save(contrat);
			logger.info("Out of method updateContrat");

		} catch (Exception e) {
			logger.error("Out of Method updateContrat with Errors : " + e);
		}

		return contratUpdated;
	}

	@Override
	public void deleteContratById(Long idContrat) {

		try {
			logger.info("In method deleteContratById");
			contratRepository.deleteById(idContrat); 
			logger.info("Out of method deleteContratById");
			
		} catch (Exception e) {
			logger.error("Out of Method deleteContratById with Errors : " + e); 
		}
	}

	@Override
	public void deleteContrat(Contrat contrat) {
		try {
			logger.info("In method deleteContrat");
			contratRepository.delete(contrat); 
			logger.info("Out of method deleteContrat");
			
		} catch (Exception e) {
			logger.error("Out of Method deleteContrat with Errors : " + e); 
		}

	}

	@Override
	public Contrat getContratById(Long idContrat) {
		Contrat contrat = contratRepository.findById(idContrat).orElse(null);
        if (contrat == null) {
        	logger.error("No mission Found with ID : " +idContrat);
        }
        return contrat;
	}

}
