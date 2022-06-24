package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.TimeSheet;
import tn.esprit.spring.entities.TimeSheetPK;

@Repository
public interface TimeSheetRepository extends CrudRepository<TimeSheet, Integer> {

	@Query("select DISTINCT m from Mission m join m.timeSheets t join t.employe e where e.id=:employeId")
	public List<Mission> findAllMissionByEmployeJPQL(@Param("employeId")long employeId);
	
	@Query("select DISTINCT e from Employe e "
				+ "join e.timeSheets t "
				+ "join t.mission m "
				+ "where m.id=:misId")
	public List<Employe> getAllEmployeByMission(@Param("misId")int missionId);
	
	
	@Query("Select t from TimeSheet t "
				+ "where t.mission=:mis and "
				+ "t.employe=:emp and "
				+ "t.timeSheetPK.dateDebut>=:dateD and "
				+ "t.timeSheetPK.dateFin<=:dateF")
	public List<TimeSheet> getTimesheetsByMissionAndDate(@Param("emp")Employe employe, @Param("mis")Mission mission, @Param("dateD")Date dateDebut,@Param("dateF")Date dateFin);

	  public TimeSheet findBytimesheetPK(TimeSheetPK timesheetPK);
}
