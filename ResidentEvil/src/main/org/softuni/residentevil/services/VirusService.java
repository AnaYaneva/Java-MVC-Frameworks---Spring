package org.softuni.residentevil.services;

import org.softuni.residentevil.entities.Virus;
import org.softuni.residentevil.models.AddVirusBindingModel;
import org.softuni.residentevil.models.EditVirusBindingModel;

import java.util.List;

public interface VirusService {
    void save(AddVirusBindingModel bindingModel);

    List<Virus> findAllViruses();

    void deleteById(String id);

    Virus findVirusById(String id);

    void edit(String id, EditVirusBindingModel bindingModel);

    String getMap();
}
