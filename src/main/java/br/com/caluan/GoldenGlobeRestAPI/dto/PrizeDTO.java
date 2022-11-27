package br.com.caluan.GoldenGlobeRestAPI.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PrizeDTO {

    List<ProducersDTO> min;
    List<ProducersDTO> max;


    public PrizeDTO() {
        this.min = new ArrayList<ProducersDTO>();
        this.max = new ArrayList<ProducersDTO>();
    }
}
