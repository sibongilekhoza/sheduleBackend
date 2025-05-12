package com.sheduleBackend.BusinessServices.impl;

import com.sheduleBackend.Enums.Role;
import com.sheduleBackend.Exceptions.UserException;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import com.sheduleBackend.Models.Staff;
import com.sheduleBackend.Models.User;
import com.sheduleBackend.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Authentication {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public StaffResponseDto logIn(String email, String password) {
        System.out.println(password);
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String userPassword = user.getPassword();
            if (email.equals(user.getEmail()) && password.equals(userPassword)) {
                if (user instanceof Staff staff) {
                    Role role = staff.getUserRole();
                    StaffResponseDto staffResponseDto = modelMapper.map(user, StaffResponseDto.class);
                    staffResponseDto.setRole(role);
                    return staffResponseDto;
                }
                throw new UserException("Students are not allowed to log in");
            }
            throw new UserException("Wrong password or email");
        }
        throw new UserException("User does not exist");
    }
}
