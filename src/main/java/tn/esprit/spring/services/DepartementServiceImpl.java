package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;

import java.util.List;

@Service
public class DepartementServiceImpl implements IDepartementService {


    @Autowired
    DepartementRepository deptRepoistory;


    public List<Departement> getAllDepartements() {
        return (List<Departement>) deptRepoistory.findAll();
    }

}
