package com.adrian.forohub.controller;

import com.adrian.forohub.infra.security.DatosLogin;
import com.adrian.forohub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<String>login(@RequestBody @Valid DatosLogin datosLogin){
        try{
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                    datosLogin.username(), datosLogin.password()
            );
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // Aquí podrías generar un token JWT y devolverlo
            String jwtToken = tokenService.generarToken(datosLogin.username());
            return ResponseEntity.ok(jwtToken);


        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Usuario o contraseña inválidos");
        }
    }
}
