package com.example.appGestioneAziendale.controllers;

import com.example.appGestioneAziendale.domain.dto.requests.LikeRequest;
import com.example.appGestioneAziendale.domain.dto.response.LikeResponse;
import com.example.appGestioneAziendale.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/v1/like")
@RequiredArgsConstructor
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/create")
    public ResponseEntity<LikeResponse> createLike(@RequestBody LikeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(likeService.createLike(request));
    }
}
