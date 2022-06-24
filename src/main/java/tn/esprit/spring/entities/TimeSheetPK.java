package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class TimeSheetPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idMission;
	private long idEmploye;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	

	public TimeSheetPK() {
		super();
	}
	
	public TimeSheetPK(int idMission, long idEmploye, Date dateDebut, Date dateFin) {
		super();
		this.idMission = idMission;
		this.idEmploye = idEmploye;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	

	public void setIdMission(int idMission) {
		this.idMission = idMission;
	}

	public long getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(long idEmploye) {
		this.idEmploye = idEmploye;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getIdMission() {
		return idMission;
	}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
			result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
			result = (int) (prime * result + idEmploye);
			result = prime * result + idMission;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TimeSheetPK other = (TimeSheetPK) obj;
			if (dateDebut == null) {
				if (other.dateDebut != null)
					return false;
			} else if (!dateDebut.equals(other.dateDebut))
				return false;
			if (dateFin == null) {
				if (other.dateFin != null)
					return false;
			} else if (!dateFin.equals(other.dateFin))
				return false;
			if (idEmploye != other.idEmploye)
				return false;
			if (idMission != other.idMission)
				return false;
			return true;
		}

	@Override
	public String toString() {
		return "TimesheetPK{" +
				"idMission=" + idMission +
				", idEmploye=" + idEmploye +
				", dateDebut=" + dateDebut +
				", dateFin=" + dateFin +
				'}';
	}

}
