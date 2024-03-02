package com.danielferreira.crud.modules.users.controllers;

import com.danielferreira.crud.modules.infra.security.TokenService;
import com.danielferreira.crud.modules.users.dto.AuthenticationDTO;
import com.danielferreira.crud.modules.users.dto.LoginResponseDTO;
import com.danielferreira.crud.modules.users.dto.RegisterDTO;
import com.danielferreira.crud.modules.users.entities.UserEntity;
import com.danielferreira.crud.modules.users.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    };

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if(this.repository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();
        var encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        var user = new UserEntity(registerDTO.login(), encryptedPassword, registerDTO.role());
        this.repository.save(user);
        return ResponseEntity.ok().build();
    }
}
