package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

import java.util.List;

@Service
public class ContratServiceImpl implements IContratService {


    @Autowired
    ContratRepository contratRepository;


    public List<Contrat> getAllContrats() {
        return (List<Contrat>) contratRepository.findAll();
    }

}
