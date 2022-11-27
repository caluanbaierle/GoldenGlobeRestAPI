package br.com.caluan.GoldenGlobeRestAPI.dto;

import lombok.Data;

@Data
public class MovieRequestDTO {

    private int id;
    private String year;
    private String title;
    private String studios;
    private String producers;
    private String Winner;

}
