package com.vm.vacationmanager.services.impl;

import com.vm.vacationmanager.errors.UsernamesNotFoundException;
import com.vm.vacationmanager.models.binding.UserRolesBindingModel;
import com.vm.vacationmanager.models.entities.Roles;
import com.vm.vacationmanager.models.entities.User;
import com.vm.vacationmanager.models.entities.UserRole;
import com.vm.vacationmanager.models.entities.Vacation;
import com.vm.vacationmanager.models.service.UserServiceModel;
import com.vm.vacationmanager.models.views.UserViewModel;
import com.vm.vacationmanager.repesitories.UserRepository;
import com.vm.vacationmanager.repesitories.UserRoleRepository;
import com.vm.vacationmanager.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
        private final UserRepository userRepository;
        private final UserRoleRepository userRoleRepository;
        private final ModelMapper modelMapper;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;
        private final UserServiceDB userServiceDB;

        @Autowired
        public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper,
                               BCryptPasswordEncoder bCryptPasswordEncoder, UserServiceDB userServiceDB) {
            this.userRepository = userRepository;
            this.userRoleRepository = userRoleRepository;
            this.modelMapper = modelMapper;
            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
            this.userServiceDB = userServiceDB;
        }

        @Override
        public void registerUserAndLogin(UserServiceModel userServiceModel) {
            User user = this.modelMapper.map(userServiceModel, User.class);
            user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
            if (userRepository.count() == 0) {
                UserRole userRole = userRoleRepository.
                        findByRole(Roles.ADMIN).orElseThrow(() ->
                                new IllegalStateException("ADMIN role not found. Please seed the roles."));

                user.getRoles().add(userRole);
            }else{
                UserRole userRole =userRoleRepository.
                        findByRole(Roles.EMPLOYEE).orElseThrow(() ->
                                new IllegalStateException("EMPLOYEE role not found. Please seed the roles."));
                user.getRoles().add(userRole);
            }
                this.userRepository.save(user);
            UserDetails principal = userServiceDB.loadUserByUsername(user.getUsername());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principal,
                    user.getPassword(),
                    principal.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        @Override
        public boolean findUserByUserName(String username) {
            return this.userRepository.findByUsername(username).isEmpty();
        }

        @Override
        public User getUserByUserName(String username) {
            return this.userRepository.findByUsername(username).orElseThrow(() ->
                 new UsernamesNotFoundException(String.format("%s not exist.", username)));
        }

        @Override
        public List<UserViewModel> getUsers() {
            List<User> users = userRepository.findAll();
            return users.stream().map(u -> modelMapper.map(u, UserViewModel.class)).collect(Collectors.toList());
        }

        @Override
        public void setRoles(UserRolesBindingModel userRolesBindingModel) {
            User user = userRepository.findByUsername(userRolesBindingModel.getUsername()).orElseThrow();
            user.getRoles().clear();
            userRolesBindingModel.getRoles().forEach(r -> {
                UserRole userRole = userRoleRepository.
                        findByRole(Roles.valueOf(r)).orElseThrow(
                                () -> new IllegalStateException("User Role not found. Please seed the roles."));
                user.getRoles().add(userRole);
            });
            userRepository.save(user);
        }

        @Override
        public boolean isMoreOneAdmin() {
            int admins = userRepository.findAdminUsers();
            return admins >= 1;
        }

    @Override
    public List<Vacation> findMyVacations(String username) {
        return userRepository.findMyVacations(username);
    }
}

