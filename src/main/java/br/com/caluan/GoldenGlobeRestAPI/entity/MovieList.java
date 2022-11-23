package br.com.caluan.GoldenGlobeRestAPI.entity;

import lombok.Data;
import javax.persistence.*;

    @Entity
    @Data
    @Table(name = "movie_list", schema = "public", catalog = "dba")
    public class MovieList {
        private int id;
        private int year;
        private String title;
        private String studios;
        private String producers;
        private boolean isWinner;

        @Id
        @SequenceGenerator(name="movie_list_sequence", sequenceName="movie_list_sequence_id_seq", allocationSize = 1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="movie_list_sequence")
        @Column(name = "id")
        public int getId() {
            return id;
        }
    }

