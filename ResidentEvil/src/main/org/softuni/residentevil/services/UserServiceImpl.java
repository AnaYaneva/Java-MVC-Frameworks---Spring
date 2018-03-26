package org.softuni.residentevil.services;

import org.softuni.residentevil.entities.Role;
import org.softuni.residentevil.entities.User;
import org.softuni.residentevil.models.EditUserBindingModel;
import org.softuni.residentevil.models.RegisterUserBindingModel;
import org.softuni.residentevil.repositories.RoleRepository;
import org.softuni.residentevil.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = repository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void register(RegisterUserBindingModel bindingModel) {
        User user = new User();

        user.setUsername(bindingModel.getUsername());
        user.setPassword(this.encoder.encode(bindingModel.getPassword()));
        user.setEmail(bindingModel.getEmail());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        Role userRole = this.roleRepository.findRoleByAuthority("ROLE_USER");

        if (userRole == null) {
            userRole = new Role();
            userRole.setAuthority("ROLE_USER");
            userRole.setUsers(new HashSet<>());

            this.roleRepository.saveAndFlush(userRole);
        }

        user.setAuthorities(new HashSet<>(Collections.singletonList(userRole)));
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public boolean userWithUsernameExists(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByUsername(username) != null;
    }

    @Override
    public boolean userWithEmailExists(String email) {
        return this.userRepository.findUserByEmail(email) != null;
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return this.userRepository.getOne(id);
    }

    @Override
    public void edit(long id, EditUserBindingModel bindingModel) {
        User user = this.getUserById(id);

        if (user == null) {
            return;
        }

        user.setUsername(bindingModel.getUsername());
        user.setEmail(bindingModel.getEmail());

        if (bindingModel.getPassword().length() != 0) {
            user.setPassword(this.encoder.encode(bindingModel.getPassword()));
        }

        Set<Role> rolesToAdd = new HashSet<>();

        for (String roleName : bindingModel.getRoles()) {
            Role role = this.roleRepository.findRoleByAuthority(roleName);

            if (role == null) {
                continue;
            }

            rolesToAdd.add(role);
        }

        user.setAuthorities(rolesToAdd);
        this.userRepository.saveAndFlush(user);
    }
}