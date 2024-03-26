package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Piece {
    protected final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    protected abstract List<Direction> movableDirections();

    protected List<Square> calculatePath(final Square source, final Map<Square, Piece> pieces) {
        final List<Square> movableSquares = new ArrayList<>();

        for (final Direction movableDirection : movableDirections()) {
            Square movableSource = source;

            while (movableSource.canMove(movableDirection)) {
                movableSource = movableSource.next(movableDirection);
                if (pieces.containsKey(movableSource)) {
                    break;
                }
                movableSquares.add(movableSource);
            }
        }

        return movableSquares;
    }

    public boolean canNotMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        return !canMove(source, target, pieces);
    }

    protected abstract boolean canMove(Square source, Square target, Map<Square, Piece> pieces);

    public boolean isSameTeam(final Piece other) {
        return this.team == other.team;
    }

    public boolean isOppositeTeam(final Team other) {
        return team != other;
    }

    public boolean isBlack() {
        return this.team == Team.BLACK;
    }
}
