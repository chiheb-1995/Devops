package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EntrepriseServiceImplTest {
    private static final Logger l = LogManager.getLogger(EntrepriseServiceImplTest.class);
    @Autowired
    IEntrepriseService iEntrepriseService;


    @Test
    @Order(1)
    void ajouterEntreprise() {
        l.info("ajouterEntreprise() Test");
        Entreprise entreprise = new Entreprise("Vermeg", "La bourse");
        Departement dep = new Departement("IT");
        entreprise.addDepartement(dep);
        long idEntreprise = iEntrepriseService.ajouterEntreprise(entreprise);
        Assertions.assertThat(idEntreprise).isPositive();
    }


    @Test
    @Order(2)
    void getEnterpriseById() {
        l.info("getEntrepriseByID() Test");
        Entreprise entreprise = iEntrepriseService.getEntrepriseById(1);
        assertNotNull(entreprise);
    }

    @Test
    @Order(3)
    void affectDepartementToEnterprise() {
        l.info("affecterDepartementAEntreprise() Test");
        Departement dep = new Departement("RH");
        long idDepartment = iEntrepriseService.ajouterDepartement(dep);
        iEntrepriseService.affecterDepartementAEntreprise(idDepartment, 1);
        Entreprise entreprise = iEntrepriseService.getEntrepriseById(1);
        Assertions.assertThat(entreprise.getDepartements().stream()
                        .map(Departement::getName)
                        .collect(Collectors.toList()))
                .contains(dep.getName());
    }
}
