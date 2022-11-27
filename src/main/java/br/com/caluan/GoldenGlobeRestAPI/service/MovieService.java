package br.com.caluan.GoldenGlobeRestAPI.service;

import br.com.caluan.GoldenGlobeRestAPI.dto.MovieRequestDTO;
import br.com.caluan.GoldenGlobeRestAPI.dto.PrizeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    MovieRequestDTO create(MovieRequestDTO movieRequestDTO);

    List<MovieRequestDTO> getAll();

    MovieRequestDTO findById(long id);

    ResponseEntity delete(long id);

    ResponseEntity update(long id, MovieRequestDTO movieRequestDTO);

    PrizeDTO getWinners();

}
