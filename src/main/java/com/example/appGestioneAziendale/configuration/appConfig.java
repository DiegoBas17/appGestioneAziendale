package com.example.appGestioneAziendale.configuration;

import com.example.appGestioneAziendale.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class appConfig {
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> dipendenteRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("Utente con questa mail non trovato"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider autProvider = new DaoAuthenticationProvider();
        autProvider.setUserDetailsService(userDetailsService());
        autProvider.setPasswordEncoder(passwordEncoder());
        return autProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
