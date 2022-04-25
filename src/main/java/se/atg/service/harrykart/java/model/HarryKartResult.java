package se.atg.service.harrykart.java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class HarryKartResult {

    @JsonProperty("ranking")
    private final List<RankingElement> rankings;

    public HarryKartResult() {
        this.rankings = new ArrayList<>();
    }

    public List<RankingElement> getRankings() {
        return rankings;
    }

    public void addRanking(int position, String horseName) {
        this.rankings.add(new RankingElement(position, horseName));
    }
}
