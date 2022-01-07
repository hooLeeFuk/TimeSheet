package tn.esprit.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.IEmployeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(EmployeTests.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeTests {
    private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
    @Mock
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    @Mock
    IEmployeService empployeService;

    @Autowired
    @InjectMocks
    private EmployeServiceImpl employeService;

    private Employe employe1;

    private static String mail = "wael.halila@gmail.com";


    @Before
    public void setUp() {

        employe1 = new Employe("halila", "wael", "wael.halila@gmail.com", true, Role.INGENIEUR);

        employeRepository.save(employe1);


    }

    @After
    public void tearDown() {

        employeRepository.deleteAll();
    }

    @Test
    public void TestajouterEmploye() {
        Employe e = new Employe();
        e.setActif(true);
        e.setEmail("wael.halila@mail.com");
        e.setNom("halila");
        e.setPrenom("wael");
        when(employeRepository.save(e)).thenReturn(e);
        assertEquals(e.getId(), employeService.addOrUpdateEmploye(e));

    }

    @Test
    public void TestgetNombreEmployeJPQL() {
        int nbr = employeService.getNombreEmployeJPQL();
        assertThat(nbr).isEqualTo(0);
    }

    @Test
    public void TestgetAllEmployeNamesJPQL() {
        List<String> list = employeService.getAllEmployeNamesJPQL();
        List<String> list1 = employeService.getAllEmployes().stream().map(Employe::getNom).collect(Collectors.toList());
        assertEquals(list, list1);
    }


    @Test
    public void TestgetAllEmployes() {
        assertTrue(employeService.getAllEmployes().isEmpty());
    }

    @Test
    public void TestgetSalaireByEmployeIdJPQL() {
        int employeId = 1;
        double salaire = employeService.getSalaireByEmployeIdJPQL(employeId);
        assertFalse(employeRepository.findById(employeId).isPresent() &&
                employeRepository.findById(employeId).get().getContrat().getSalaire() == salaire);
    }

    @Test
    public void TestmettreAjourEmailByEmployeId() {
        employeService.mettreAjourEmailByEmployeId(mail, employe1.getId());
        Optional<Employe> e = employeRepository.findById(employe1.getId());
        if (e.isPresent()) {
            assertThat(e.get().getEmail()).isEqualTo(mail);
        }
    }

    @Test
    public void TestgetEmployePrenomById() {
        String prenom = employeService.getEmployePrenomById(employe1.getId());
        assertThat(prenom).isEqualTo(null);
    }

    @Test
    public void TestmettreAjourEmailByEmployeIdJPQL() {
        employeService.mettreAjourEmailByEmployeIdJPQL("wael.halila@gmail.com", employe1.getId());
        Optional<Employe> e = employeRepository.findById(employe1.getId());
        if (e.isPresent()) {
            assertThat(e.get().getEmail()).isEqualTo("wael.halila@gmail.com");
        }
    }

    @Test
    public void TestDeleteEmploye() {
        Employe emp = new Employe();
        assertNotNull(emp);
        employeRepository.deleteEmployeById(16);

    }
}
