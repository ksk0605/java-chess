package domain.piece;

import domain.File;
import domain.Square;
import domain.Team;

import java.util.*;

public class Pieces {
    final Map<Square, Piece> pieces;

    public Pieces(final Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Square source, final Square target) {
        pieces.put(target, get(source));
        pieces.remove(source);
    }

    public Map<Team, Double> status(final Team team) {
        double score = 0;
        final Map<File, List<Piece>> ranks = new EnumMap<>(File.class);

        for (final File file : File.values()) {
            ranks.put(file, new ArrayList<>());
        }

        for (final var entry : pieces.entrySet()) {
            final Piece piece = entry.getValue();
            final Square square = entry.getKey();
            if (entry.getValue().team() == team) {
                if (piece.isPawn()) {
                    final List<Piece> rankPieces = ranks.get(square.file());
                    rankPieces.add(piece);
                    ranks.put(square.file(), rankPieces);
                } else {
                    score += piece.score();
                }
            }
        }

        for (final List<Piece> value : ranks.values()) {
            if (value.size() > 1) {
                score += value.size() * 0.5;
            } else {
                score += value.size();
            }
        }
        return Map.of(team, score);
    }

    public boolean hasPiece(final Square source) {
        return !pieces.containsKey(source);
    }

    public boolean cannotMove(final Square source, final Square target) {
        return get(source).canNotMove(source, target, pieces);
    }

    public boolean isOppositeTeam(final Square source, final Team team) {
        return get(source).isOppositeTeam(team);
    }

    public Map<Square, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    private Piece get(final Square source) {
        return pieces.get(source);
    }
}
