package com.example.appGestioneAziendale.security;

import com.example.appGestioneAziendale.domain.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Setter
    private List<String> publicEndpoints;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        String email = null;
        final String requestURI = request.getRequestURI();

        if (isPublic(requestURI)) {
            filterChain.doFilter(request,response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendAuthErrorResponse(response, "MalformedTokenException", "Token JWT mancante o malformato");
            return;
        }
        //per il logout
       /* jwt = authHeader.substring(7);
        System.out.println("il mio cazzo di token è: " + jwt);
        if (tokenBlackListService.isPresentToken(jwt)) {
            sendAuthErrorResponse(response, "TokenExpiredException",
                    "Token nella blacklist, non è più valido!");
        }
        email = jwtService.extractUsername(jwt);
        // se il login è corretto inserisco il token nel contesto di sicurezza dell'applicazione
        // quindi in sostanza da quel momento il token sarà valido per l'autenticazione
        try {
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (MalformedJwtException e) {
            sendAuthErrorResponse(response, "MalformedTokenException", "Token JWT mancante o malformato");
        } catch (UsernameNotFoundException e) {
            sendAuthErrorResponse(response, "UsernameNotFoundException", "Username non trovato");
        }
        filterChain.doFilter(request,response);
        }*/
    }

    private void sendAuthErrorResponse(HttpServletResponse response,
                                       String error,
                                       String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .exception(error)
                .message(message)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }

    private boolean isPublic(String requestURI){
        return publicEndpoints.stream()
                .map(endPoint -> endPoint.replace("**",".*"))
                .anyMatch(requestURI::matches);
    }
}
