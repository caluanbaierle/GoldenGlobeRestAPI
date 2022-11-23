package br.com.caluan.GoldenGlobeRestAPI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping()
@RestController
public class MovieController {


    @PostMapping
    public ResponseEntity<Object> isSimian(@Validated @RequestBody SimianRequest simianRequest) {
        SimianResponse response = simianService.isSimian(simianRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
