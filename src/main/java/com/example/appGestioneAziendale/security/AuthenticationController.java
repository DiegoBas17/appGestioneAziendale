package com.example.appGestioneAziendale.security;

import com.example.appGestioneAziendale.domain.dto.requests.AuthRequest;
import com.example.appGestioneAziendale.domain.dto.requests.RegisterRequest;
import com.example.appGestioneAziendale.domain.dto.response.AuthenticationResponse;
import com.example.appGestioneAziendale.domain.dto.response.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthRequest request) {
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.CREATED);
    }

   /* @PostMapping("/logout/{id_utente}")
    public ResponseEntity<GenericResponse> logout(@PathVariable Long id_utente, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        System.out.println("il mio cazzo di token estrapolato dalla chiamata è: " + token);
        return new ResponseEntity<>(authenticationService.logout(id_utente, token), HttpStatus.CREATED);
    }*/
}
