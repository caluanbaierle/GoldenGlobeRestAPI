package br.com.caluan.GoldenGlobeRestAPI.repository;

import br.com.caluan.GoldenGlobeRestAPI.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT "
            + " u "
            + " FROM Movie u where "
            + " u.winner = 'T' ")
    List<Movie> getWinners();


}
