package br.com.caluan.GoldenGlobeRestAPI.service.impl;

import br.com.caluan.GoldenGlobeRestAPI.dto.MovieRequestDTO;
import br.com.caluan.GoldenGlobeRestAPI.dto.PrizeDTO;
import br.com.caluan.GoldenGlobeRestAPI.dto.ProducersDTO;
import br.com.caluan.GoldenGlobeRestAPI.entity.Movie;
import br.com.caluan.GoldenGlobeRestAPI.exception.ResourceNotFoundException;
import br.com.caluan.GoldenGlobeRestAPI.repository.MovieRepository;
import br.com.caluan.GoldenGlobeRestAPI.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;
    private ModelMapper mapper;

    @Autowired
    public MovieServiceImpl(MovieRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public MovieRequestDTO create(MovieRequestDTO movieRequestDTO) {
        Movie movie = mapper.map(movieRequestDTO, Movie.class);
        movie = repository.save(movie);
        return mapper.map(movie, MovieRequestDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieRequestDTO> getAll() {
        List<Movie> movieList = repository.findAll();
        return movieList.stream().map(movie -> mapper.map(movie, MovieRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MovieRequestDTO findById(long id) {
        Movie bag = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Movie not found"));
        return mapper.map(bag, MovieRequestDTO.class);
    }

    @Override
    @Transactional
    public ResponseEntity delete(long id) {
        return repository.findById(id).map(data -> {
            repository.delete(data);

            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public ResponseEntity update(long id, MovieRequestDTO movieRequestDTO) {
        return repository.findById(id).map(data -> {
            repository.save(mapper.map(movieRequestDTO, Movie.class));
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @Transactional(readOnly = true)
    public PrizeDTO getWinners() {

        List<Movie> movieList = repository.getWinners();
        List<MovieRequestDTO> movieRequestDTOS = movieList.stream().map(movie -> mapper.map(movie, MovieRequestDTO.class))
                .collect(Collectors.toList());
        PrizeDTO prizeDTO = new PrizeDTO();
        List<ProducersDTO> producersData = new ArrayList<ProducersDTO>();

        for (MovieRequestDTO movie : movieRequestDTOS) {
            boolean control = true;
            String[] names = movie.getProducers().split(",");
            for (String name : names) {
                for (ProducersDTO produtor : producersData) {
                    if (produtor.getProducer().toLowerCase(Locale.ROOT).equals(name.trim().toLowerCase(Locale.ROOT))) {
                        produtor.setFollowingWin(Integer.parseInt(movie.getYear()));
                        control = false;
                    }
                }
                if (control) {
                    ProducersDTO producersDTO = new ProducersDTO();
                    producersDTO.setPreviousWin(Integer.parseInt(movie.getYear()));
                    producersDTO.setProducer(name.trim());
                    producersData.add(producersDTO);
                }
            }
        }
        List<ProducersDTO> collectProducersDTO = producersData.stream().filter(
                producersDTO -> producersDTO.getInterval() > 0).collect(Collectors.toList());

        for (ProducersDTO producersDTO : collectProducersDTO) {
            if (prizeDTO.getMax().size() > 0) {
                if (prizeDTO.getMax().get(0).getInterval() < producersDTO.getInterval()) {
                    prizeDTO.getMax().clear();
                    prizeDTO.getMax().add(producersDTO);
                }
            } else {
                prizeDTO.getMax().add(producersDTO);
            }
            if (prizeDTO.getMin().size() > 0) {
                if (prizeDTO.getMin().get(0).getInterval() > producersDTO.getInterval()) {
                    prizeDTO.getMin().clear();
                    prizeDTO.getMin().add(producersDTO);
                }
            } else {
                prizeDTO.getMin().add(producersDTO);
            }
        }

        //Caso exista mais de um Resultado.
        for (ProducersDTO producersDTO : collectProducersDTO) {
            if (prizeDTO.getMax().get(0).getInterval() == producersDTO.getInterval() &&
                    !(prizeDTO.getMax().get(0).getProducer().toLowerCase(Locale.ROOT).equals(
                            producersDTO.getProducer().toLowerCase(Locale.ROOT)))) {
                prizeDTO.getMax().add(producersDTO);
            }
            if (prizeDTO.getMin().get(0).getInterval() == producersDTO.getInterval() &&
                    !(prizeDTO.getMin().get(0).getProducer().toLowerCase(Locale.ROOT).equals(
                            producersDTO.getProducer().toLowerCase(Locale.ROOT)))) {
                prizeDTO.getMin().add(producersDTO);
            }
        }
        return prizeDTO;
    }

}
