package br.com.caluan.GoldenGlobeRestAPI.dto;

import lombok.Data;

@Data
public class ProducersDTO {

    String producer;
    int interval;
    int previousWin;
    int followingWin;


    public void setPreviousWin(int previousWin) {
        this.previousWin = previousWin;
        updateInterval();
    }

    public void setFollowingWin(int followingWin) {
        this.followingWin = followingWin;
        updateInterval();
    }

    private void updateInterval() {
        if (previousWin > 0 && followingWin > 0)
            interval = followingWin - previousWin;
    }
}
