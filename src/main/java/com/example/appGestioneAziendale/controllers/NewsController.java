package com.example.appGestioneAziendale.controllers;

import com.example.appGestioneAziendale.domain.dto.requests.CreateNewsRequest;
import com.example.appGestioneAziendale.domain.dto.response.EntityIdResponse;
import com.example.appGestioneAziendale.domain.dto.response.NewsResponse;
import com.example.appGestioneAziendale.domain.entities.News;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.repository.NewsRepository;
import com.example.appGestioneAziendale.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/get/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) throws MyEntityNotFoundException {
        return new ResponseEntity<>(newsService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<News>> getAll(){
        return new ResponseEntity<>(newsService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> createNews(@RequestBody CreateNewsRequest request){
        return new ResponseEntity<>(newsService.createNews(request), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public EntityIdResponse updateNews(@PathVariable Long id, @RequestBody CreateNewsRequest request) throws  MyEntityNotFoundException{
        News news = getNewsById(id).getBody();
        if (request.titolo() != null) news.setTitolo(request.titolo());
        if (request.testo() != null) news.setTesto(request.testo());
        if (request.image_url() != null) news.setImage_url(request.image_url());
        if (request.allegato() != null) news.setAllegato_url(request.allegato());
        return new EntityIdResponse(newsRepository.save(news).getId());
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) throws MyEntityNotFoundException{
        newsService.deleteById(id);
    }
}
