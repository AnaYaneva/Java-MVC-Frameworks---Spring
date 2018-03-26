package org.softuni.residentevil.services;

import org.softuni.residentevil.entities.User;
import org.softuni.residentevil.models.EditUserBindingModel;
import org.softuni.residentevil.models.RegisterUserBindingModel;

import java.util.List;

public interface UserService {
    void register(RegisterUserBindingModel bindingModel);

    boolean userWithUsernameExists(String username);

    boolean userWithEmailExists(String email);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    User getUserById(long id);

    void edit(long id, EditUserBindingModel bindingModel);
}
