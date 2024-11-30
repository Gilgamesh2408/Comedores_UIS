package com.uis.ComedoresUIS.auth.services;

import com.uis.ComedoresUIS.auth.dto.AuthLoginRequest;
import com.uis.ComedoresUIS.auth.dto.AuthResponse;
import com.uis.ComedoresUIS.persistence.models.admins.Administrator;
import com.uis.ComedoresUIS.persistence.models.students.Student;
import com.uis.ComedoresUIS.persistence.repositories.admins.AdministratorRepository;
import com.uis.ComedoresUIS.persistence.repositories.students.StudentRepository;
import com.uis.ComedoresUIS.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean isAdmin;

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        return isAdmin ? loadAdmin(code) : loadStudent(code);
    }

    public AuthResponse login(AuthLoginRequest authLoginRequest) {
        String code = authLoginRequest.username();
        String password = authLoginRequest.password();
        this.isAdmin = authLoginRequest.isAdmin();

        Authentication authentication = authenticate(code, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.createToken(authentication);

        return new AuthResponse(code, "User Authenticate", token, true);
    }

    public Authentication authenticate(String code, String password) {
        UserDetails user = loadUserByUsername(code);

        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(code,
                user.getPassword(), user.getAuthorities());
    }

    private UserDetails loadAdmin(String code) {
        Administrator admin = administratorRepository.findAdministratorByCodeAdmin(code)
                .orElseThrow(() -> new UsernameNotFoundException("Administrator not found"));

        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority("ROLE_"
                .concat(admin.getRole().name())));

        return new User(code, admin.getPassword(), roles);
    }

    private UserDetails loadStudent(String code) {
        Student student = studentRepository.findStudentByCodeStudent(code)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found"));

        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority("ROLE_"
                .concat(student.getRole().name())));

        return new User(code, student.getPassword(), roles);
    }
}
