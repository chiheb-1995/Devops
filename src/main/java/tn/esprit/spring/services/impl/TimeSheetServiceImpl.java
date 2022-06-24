package tn.esprit.spring.services.impl;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.TimeSheet;
import tn.esprit.spring.entities.TimeSheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimeSheetRepository;
import tn.esprit.spring.services.ITimeSheetService;

@Service
public class TimeSheetServiceImpl implements ITimeSheetService {
	
	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimeSheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	public int ajouterMission(Mission mission) {
		missionRepository.save(mission);
		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {
		Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new RuntimeException("NotFound"));
		Departement dep = deptRepoistory.findById(depId).orElseThrow(() -> new RuntimeException("NotFound"));;
		mission.setDepartement(dep);
		missionRepository.save(mission);
		
	}

	public void ajouterTimeSheet(int missionId, long employeId, Date dateDebut, Date dateFin) {
		TimeSheetPK timesheetPK = new TimeSheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		TimeSheet timesheet = new TimeSheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false);
		timesheetRepository.save(timesheet);
		
	}

	
	public void validerTimeSheet(int missionId, long employeId, Date dateDebut, Date dateFin, long validateurId) {
		System.out.println("In valider Timesheet");
		Employe validateur = employeRepository.findById((int) validateurId).get();
		Mission mission = missionRepository.findById(missionId).get();
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			System.out.println("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return;
		}
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		if(!chefDeLaMission){
			System.out.println("l'employe doit etre chef de departement de la mission en question");
			return;
		}
		
		TimeSheetPK timesheetPK = new TimeSheetPK(missionId, employeId, dateDebut, dateFin);
		TimeSheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(long employeId) {
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

}
