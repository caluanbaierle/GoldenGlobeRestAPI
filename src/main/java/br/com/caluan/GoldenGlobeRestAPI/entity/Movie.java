package br.com.caluan.GoldenGlobeRestAPI.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "YEAR", nullable = false)
    private String year;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "STUDIOS", nullable = false)
    private String studios;
    @Column(name = "PRODUCERS", nullable = false)
    private String producers;
    @Column(name = "ISWINNER")
    private String winner;


}

