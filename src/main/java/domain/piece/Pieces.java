package domain.piece;

import domain.File;
import domain.Square;
import domain.Team;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Pieces {
    final Map<Square, Piece> pieces;

    public Pieces(final Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Square source, final Square target) {
        pieces.put(target, get(source));
        pieces.remove(source);
    }

    public double score(final Team team) {
        return getScoreWithoutPawn(team) + getTotalPawnScore(team);
    }

    private double getScoreWithoutPawn(final Team team) {
        return pieces.values().stream()
                .filter(piece -> piece.team() == team)
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::score)
                .sum();
    }

    private double getTotalPawnScore(final Team team) {
        return countPawnsByFile(pieces, team).values().stream()
                .mapToDouble(aLong -> aLong >= 2 ? aLong * 0.5 : aLong)
                .sum();
    }

    private Map<File, Long> countPawnsByFile(final Map<Square, Piece> pieces, final Team team) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().team() == team)
                .filter(entry -> entry.getValue().isPawn())
                .collect(Collectors.groupingBy(entry -> entry.getKey().file(), Collectors.counting()));
    }

    public boolean hasPiece(final Square source) {
        return !pieces.containsKey(source);
    }

    public boolean cannotMove(final Square source, final Square target) {
        return get(source).cannotMove(source, target, pieces);
    }

    public boolean isOppositeTeam(final Square source, final Team team) {
        return get(source).isOppositeTeam(team);
    }

    public boolean isKingCountUnder(final int count) {
        return getKingCount() < count;
    }

    private int getKingCount() {
        return (int) pieces.values().stream()
                .filter(Piece::isKing)
                .count();
    }

    public Map<Square, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    private Piece get(final Square source) {
        return pieces.get(source);
    }
}
