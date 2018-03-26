package org.softuni.residentevil.services;

import org.softuni.residentevil.entities.Capital;
import org.softuni.residentevil.repositories.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapitalServiceImpl implements CapitalService {
    private CapitalRepository repository;

    @Autowired
    public CapitalServiceImpl(CapitalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Capital> getAllCapitals() {
        return repository.findAll();
    }

    @Override
    public Capital getCapitalByName(String capitalName) {
        return this.repository.getCapitalByName(capitalName);
    }
}