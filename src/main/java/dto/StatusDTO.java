package dto;

import domain.Team;

import java.util.Map;

public record StatusDTO(double whiteScore, double blackScore) {
    public static StatusDTO from(final Map<Team, Double> status) {
        final double whiteScore = status.get(Team.WHITE);
        final double blackScore = status.get(Team.BLACK);

        return new StatusDTO(whiteScore, blackScore);
    }
}
