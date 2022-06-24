package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;

public interface ITimeSheetService {
	public int ajouterMission(Mission mission);
	public void affecterMissionADepartement(int missionId, int depId);
	public void ajouterTimeSheet(int missionId, long employeId, Date dateDebut, Date dateFin);
	public void validerTimeSheet(int missionId, long employeId, Date dateDebut, Date dateFin, long validateurId);
	public List<Mission> findAllMissionByEmployeJPQL(long employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
}
