package br.com.caluan.GoldenGlobeRestAPI.controller;

import br.com.caluan.GoldenGlobeRestAPI.dto.MovieRequestDTO;
import br.com.caluan.GoldenGlobeRestAPI.dto.PrizeDTO;
import br.com.caluan.GoldenGlobeRestAPI.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/movie")
@RestController
public class MovieController {

    @Autowired
    private MovieService service;


    @PostMapping
    public ResponseEntity<MovieRequestDTO> create(@Validated @RequestBody MovieRequestDTO movieRequest) {
        MovieRequestDTO response = service.create(movieRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<MovieRequestDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieRequestDTO> findById(@PathVariable("id") long movieID) {
        MovieRequestDTO movieRequestDTO = service.findById(movieID);
        return new ResponseEntity<>(movieRequestDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @Valid @RequestBody final MovieRequestDTO movieRequest) {
        return service.update(id, movieRequest);
    }

    @GetMapping("/product")
    public PrizeDTO getProduct() {
        return service.getWinners();
    }


}
