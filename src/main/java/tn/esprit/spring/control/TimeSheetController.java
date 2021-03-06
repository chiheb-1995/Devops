package tn.esprit.spring.control;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimeSheetService;


@RestController
public class TimeSheetController {

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimeSheetService itimesheetservice;
	
	// http://localhost:8088/SpringMVC/servlet/ajouterMission
	// {"id":0,"name":"mamission", "description":"c ma mission"}
	@PostMapping("/ajouterMission")
	@ResponseBody
	public int ajouterMission(@RequestBody Mission mission) {
		itimesheetservice.ajouterMission(mission);
		return mission.getId();
	}

	// http://localhost:8088/SpringMVC/servlet/affecterMissionADepartement/4/4
	@PutMapping(value = "/affecterMissionADepartement/{idmission}/{iddept}") 
	public void affecterMissionADepartement(@PathVariable("idmission") int missionId, @PathVariable("iddept") int depId) {
		itimesheetservice.affecterMissionADepartement(missionId, depId);

	}
	
	// http://localhost:8088/SpringMVC/servlet/ajouterTimesheet
    //{"missionId":1,"employeId":2,"dateDebut":"2020-03-01","dateFin":"2021-03-01"}
	
	@PostMapping("/ajouterTimesheet/idmission/idemp/dated/datef")
	@ResponseBody
	public void ajouterTimesheet(@PathVariable("idmission") int missionId, @PathVariable("idemp") int employeId, @PathVariable("dated") Date dateDebut,@PathVariable("datef") Date dateFin) {
		itimesheetservice.ajouterTimeSheet(missionId, employeId, dateDebut, dateFin);

	}

	// http://localhost:8088/SpringMVC/servlet/affecterMissionADepartement/4/4
	@PutMapping(value = "/validerTimesheet/{idmission}/{iddept}") 
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		itimesheetservice.validerTimeSheet(missionId, employeId, dateDebut, dateFin, validateurId);

	}
	
	// http://localhost:8088/SpringMVC/servlet/findAllMissionByEmployeJPQL/1
    @GetMapping(value = "findAllMissionByEmployeJPQL/{idemp}")
    @ResponseBody
	public List<Mission> findAllMissionByEmployeJPQL(@PathVariable("idemp") int employeId) {

		return itimesheetservice.findAllMissionByEmployeJPQL(employeId);
	}

    // http://localhost:8088/SpringMVC/servlet/getAllEmployeByMission/1
    @GetMapping(value = "getAllEmployeByMission/{idmission}")
    @ResponseBody
	public List<Employe> getAllEmployeByMission(@PathVariable("idmission") int missionId) {

		return itimesheetservice.getAllEmployeByMission(missionId);
	}
}
