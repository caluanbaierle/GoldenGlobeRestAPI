package br.com.caluan.GoldenGlobeRestAPI.config;

import br.com.caluan.GoldenGlobeRestAPI.entity.Movie;
import br.com.caluan.GoldenGlobeRestAPI.repository.MovieRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
@Slf4j
public class CSVInitializerReader {

    MovieRepository repository;

    @Autowired
    public CSVInitializerReader(MovieRepository repository) {
        this.repository = repository;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(System.getProperty("user.dir") +
                    File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "movielist.csv"));

            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).withSkipLines(1).build();

            List<String[]> movies = csvReader.readAll();
            for (String[] movie : movies) {
                repository.save(getMovie(movie));
            }
        } catch (Exception ex) {
            log.error("Error metodo CSVInitializerReader " + ex);
        }
    }

    private Movie getMovie(String[] movie) {
        if (movie == null) {

            return null;
        } else {
            Movie toReturn = new Movie();
            toReturn.setYear(movie[0]);
            toReturn.setTitle(movie[1]);
            toReturn.setStudios(movie[2]);
            toReturn.setProducers(movie[3].replaceAll(", and ", ",").replaceAll(" and ", ","));
            toReturn.setWinner(movie[4].equals("yes") ? "T" : "F");
            return toReturn;
        }
    }
}
