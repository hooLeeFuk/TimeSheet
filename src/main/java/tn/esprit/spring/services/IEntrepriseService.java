package tn.esprit.spring.services;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

import java.util.List;

public interface IEntrepriseService {

    public int ajouterEntreprise(Entreprise entreprise);

    public int ajouterDepartement(Departement dep);

    void affecterDepartementAEntreprise(int depId, int entrepriseId);

    List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);

    public void deleteEntrepriseById(int entrepriseId);

    public void deleteDepartementById(int depId);

    public Entreprise getEntrepriseById(int entrepriseId);
}
