package org.softuni.residentevil.services;

import org.softuni.residentevil.entities.Capital;

import java.util.List;

public interface CapitalService {
    List<Capital> getAllCapitals();

    Capital getCapitalByName(String capitalName);
}
