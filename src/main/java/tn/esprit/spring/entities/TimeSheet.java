package tn.esprit.spring.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TimeSheet implements Serializable{

	private static final long serialVersionUID = 3876346912862238239L;

	@EmbeddedId
	private TimeSheetPK timesheetPK;

	@ManyToOne
    @JoinColumn(name = "idMission", referencedColumnName = "id", insertable=false, updatable=false)
	private Mission mission;
	
	@ManyToOne
    @JoinColumn(name = "idEmploye", referencedColumnName = "id", insertable=false, updatable=false)
	private Employe employe;
	
	private boolean isValide;
	
	public boolean isValide() {
		return isValide;
	}

	public void setValide(boolean isValide) {
		this.isValide = isValide;
	}

	public TimeSheetPK getTimeSheetPK() {
		return timesheetPK;
	}

	public void setTimesheetPK(TimeSheetPK timesheetPK) {
		this.timesheetPK = timesheetPK;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}


	@Override
	public String toString() {
		return "Timesheet{" +
				"timesheetPK=" + timesheetPK +
				", mission=" + mission +
				", employe=" + employe +
				", isValide=" + isValide +
				'}';
	}
}
