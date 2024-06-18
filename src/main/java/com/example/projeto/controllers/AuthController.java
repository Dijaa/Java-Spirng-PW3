package com.example.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.AuthenticationDTO;
import com.example.projeto.dtos.LoginRespotaDTO;
import com.example.projeto.dtos.RegisterDTO;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.AuthorizationService;
import com.example.projeto.service.EmailService;
import com.example.projeto.service.TokenService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@NoArgsConstructor
public class AuthController {
   
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AuthorizationService authorizationService;


    @Autowired
    private TokenService tokenService;

    @Autowired EmailService emailService;

    @GetMapping("/public/testeemail")
    public void getMethodName() {
       //emailService.send("edersonbastiani@gmail.com", "oi", "oi");
       //emailService.sendHTML1("edersonbastiani@gmail.com", "teste");
       emailService.sendHtmlTemplate("edersonbastiani@gmail.com", "teste");

    }
    

    @PostMapping("/public/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO authetinticationDto){
        authenticationManager = context.getBean(AuthenticationManager.class);
        
        var usernamePassword = new UsernamePasswordAuthenticationToken(authetinticationDto.email(), authetinticationDto.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        UserModel user = (UserModel) auth.getPrincipal();
        
        System.out.println("aaaaaaaa");
        System.out.println(user.getId());
        System.out.println("aaaaaaaa");


        return ResponseEntity.ok(new LoginRespotaDTO(token, user.getId()));
    }

    @PostMapping("/public/register")
    public ResponseEntity<Object> register (@RequestBody RegisterDTO registerDto){
            return authorizationService.register(registerDto);
        
    }
    
}