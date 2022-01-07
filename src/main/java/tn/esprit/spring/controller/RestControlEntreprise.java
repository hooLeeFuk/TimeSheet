package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

import java.util.List;

@RestController
public class RestControlEntreprise {


    @Autowired
    IEmployeService iemployeservice;
    @Autowired
    IEntrepriseService ientrepriseservice;
    @Autowired
    ITimesheetService itimesheetservice;

    // Ajouter Entreprise : http://localhost:8081/SpringMVC/servlet/ajouterEntreprise
    //{"id":1,"name":"SSII Consulting","raisonSocial":"Cite El Ghazela"}

    @PostMapping("/ajouterEntreprise")
    @ResponseBody
    public int ajouterEntreprise(@RequestBody Entreprise ssiiConsulting) {
        ientrepriseservice.ajouterEntreprise(ssiiConsulting);
        return ssiiConsulting.getId();
    }

    // http://localhost:8081/SpringMVC/servlet/affecterDepartementAEntreprise/1/1
    @PutMapping(value = "/affecterDepartementAEntreprise/{iddept}/{identreprise}")
    public void affecterDepartementAEntreprise(@PathVariable("iddept") int depId, @PathVariable("identreprise") int entrepriseId) {
        ientrepriseservice.affecterDepartementAEntreprise(depId, entrepriseId);
    }

    // http://localhost:8081/SpringMVC/servlet/deleteEntrepriseById/1
    @DeleteMapping("/deleteEntrepriseById/{identreprise}")
    @ResponseBody
    public void deleteEntrepriseById(@PathVariable("identreprise") int entrepriseId) {
        ientrepriseservice.deleteEntrepriseById(entrepriseId);
    }

    // http://localhost:8081/SpringMVC/servlet/getEntrepriseById/1
    @GetMapping(value = "getEntrepriseById/{identreprise}")
    @ResponseBody
    public Entreprise getEntrepriseById(@PathVariable("identreprise") int entrepriseId) {

        return ientrepriseservice.getEntrepriseById(entrepriseId);
    }

    // http://localhost:8081/SpringMVC/servlet/ajouterDepartement
    //{"id":1,"name":"Telecom"}

    @PostMapping("/ajouterDepartement")
    @ResponseBody
    public int ajouterDepartement(@RequestBody Departement dep) {
        return ientrepriseservice.ajouterDepartement(dep);
    }

    // http://localhost:8081/SpringMVC/servlet/getAllDepartementsNamesByEntreprise/1
    @GetMapping(value = "getAllDepartementsNamesByEntreprise/{identreprise}")
    @ResponseBody
    public List<String> getAllDepartementsNamesByEntreprise(@PathVariable("identreprise") int entrepriseId) {
        return ientrepriseservice.getAllDepartementsNamesByEntreprise(entrepriseId);
    }

    // URL : http://localhost:8081/SpringMVC/servlet/deleteDepartementById/3
    @DeleteMapping("/deleteDepartementById/{iddept}")
    @ResponseBody
    public void deleteDepartementById(@PathVariable("iddept") int depId) {
        ientrepriseservice.deleteDepartementById(depId);

    }
}
