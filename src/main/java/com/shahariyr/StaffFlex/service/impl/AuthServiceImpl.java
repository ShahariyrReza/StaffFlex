package com.shahariyr.StaffFlex.service.impl;

import com.shahariyr.StaffFlex.dto.LoginDto;
import com.shahariyr.StaffFlex.dto.RegisterDto;
import com.shahariyr.StaffFlex.entity.Employee;
import com.shahariyr.StaffFlex.entity.Role;
import com.shahariyr.StaffFlex.entity.User;
import com.shahariyr.StaffFlex.enums.EmployeeStatus;
import com.shahariyr.StaffFlex.enums.RoleType;
import com.shahariyr.StaffFlex.repository.EmployeeRepository;
import com.shahariyr.StaffFlex.repository.RoleRepository;
import com.shahariyr.StaffFlex.repository.UserRepository;
import com.shahariyr.StaffFlex.security.JwtTokenProvider;
import com.shahariyr.StaffFlex.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           EmployeeRepository employeeRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new RuntimeException("Username is already exists!.");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new RuntimeException("Email is already exists!.");
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleType.ROLE_EMPLOYEE).get();
        roles.add(userRole);
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        // Also create a basic Employee profile
        Employee employee = new Employee();
        employee.setFirstName(registerDto.getFirstName());
        employee.setLastName(registerDto.getLastName());
        employee.setEmail(registerDto.getEmail());
        employee.setUser(savedUser);
        employee.setJoiningDate(LocalDate.now());
        employee.setStatus(EmployeeStatus.ACTIVE);
        employeeRepository.save(employee);

        return "User Registered Successfully!.";
    }
}
