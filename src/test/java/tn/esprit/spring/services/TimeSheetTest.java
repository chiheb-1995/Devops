package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimeSheetRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TimeSheetTest {


    @Autowired
    ITimeSheetService timesheetService;
    @Autowired
    IEmployeService employeService;
    @Autowired
    TimeSheetRepository timesheetRepository;
    @Autowired
    MissionRepository missionRepository;


    @Test
    public void testTimeSheetAffecteAvecEmployeTechnicien() throws ParseException {
        Employe technicien = new Employe("nom de TECHNICIEN", "prenom de TECHNICIENT", "technicien@test1.com", true, Role.TECHNICIEN);
        //employé qui va etre affecter à une mission
        Employe employe = new Employe("nom employer a misssion", "prenom employer a mission", "employemission@test1.com", true, Role.INGENIEUR);
        Mission mission = new Mission("Mission de test", "description mission pour lancer ce test unitaire");
        TimeSheet timesheet = new TimeSheet();
        timesheet.setEmploye(technicien);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateDebut = formatter.parse("01-01-2022");
        Date dateFin = formatter.parse("01-06-2022");
        employeService.addEmploye(employe);
        employeService.addEmploye(technicien);
        timesheetService.ajouterMission(mission);
        timesheetService.ajouterTimeSheet(mission.getId(), employe.getId(), dateDebut, dateFin);
        timesheetService.validerTimeSheet(mission.getId(), technicien.getId(), dateDebut, dateFin, technicien.getId());
      //  Collection<TimeSheet> timesheets = employeService.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);

      //  for (TimeSheet timesheet1 : timesheets) {
       //     assertFalse(timesheet1.isValide());
      //  }
    }

    @Test
    public void testTimeSheetAvecEmployeDifferentDepartementQueLaMission() throws ParseException {
        Employe technicien = new Employe("nom de TECHNICIEN", "prenom de TECHNICIENT", "technicien@test.com", true, Role.TECHNICIEN);
        Employe chefDepartmenet = new Employe("Nom CHEF DEPARTEMENT", "prenom Nom CHEF DEPARTEMENT", "chefdepartement@test.com", true, Role.CHEF_DEPARTEMENT);
        Mission mission = new Mission("Mission de test", "description mission pour lancer ce test unitaire");

        Departement departement1 = new Departement("Departement test 1");
        mission.setDepartement(departement1);
        chefDepartmenet.setDepartements(Arrays.asList(departement1));
        TimeSheet timesheet = new TimeSheet();
        timesheet.setEmploye(technicien);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateDebut = formatter.parse("01-01-2022");
        Date dateFin = formatter.parse("01-06-2022");
        employeService.addEmploye(chefDepartmenet);
        employeService.addEmploye(technicien);
        timesheetService.ajouterMission(mission);
        timesheetService.ajouterTimeSheet(mission.getId(), technicien.getId(), dateDebut, dateFin);


        timesheetService.validerTimeSheet(mission.getId(), technicien.getId(), dateDebut, dateFin, chefDepartmenet.getId());


/*        Collection<TimeSheet> timesheets = employeService.getTimesheetsByMissionAndDate(technicien, mission, dateDebut, dateFin); 

        for (TimeSheet timesheet1 : timesheets) {
            assertFalse(timesheet1.isValide());
        } **/
    }
}
