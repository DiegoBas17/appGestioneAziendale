package com.example.appGestioneAziendale.security;

import com.example.appGestioneAziendale.domain.dto.requests.AuthRequest;
import com.example.appGestioneAziendale.domain.dto.requests.RegisterRequest;
import com.example.appGestioneAziendale.domain.dto.response.AuthenticationResponse;
import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.services.ComuneService;
import com.example.appGestioneAziendale.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request){
        Dipendente dipendente = Dipendente
                .builder()
                .nome(request.nome())
                .cognome(request.cognome())
                .email(request.email())
                .avatar(request.avatar())
                .dataDiNascita(request.dataDiNascita())
                .password(passwordEncoder.encode(request.password()))
                .telefono(request.numero())
                .comuneDiNascita(comuneService.getById(request.comune()))
                .build();
        String jwtToken = jwtService.generateToken(dipendente);
        dipendente.setRegistrationToken(jwtToken);
        dipendenteService.insertDipendente(dipendente);
        // TODO invio email di conferma
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.mail().toLowerCase(),
                request.password()
        ));
        Dipendente dipendente = dipendenteService.getByEmail(request.mail());
        String token = jwtService.generateToken(dipendente);
        dipendente.setLastLogin(LocalDateTime.now());
        dipendenteService.insertDipendente(dipendente);
        return AuthenticationResponse.builder().token(token).build();
    }

   /* public GenericResponse logout(Long idUtente, String token) {
        tokenBlackListService.insertToken(idUtente,token);
        return GenericResponse.builder().message("Logout effettuato con successo").build();
    }*/
}
