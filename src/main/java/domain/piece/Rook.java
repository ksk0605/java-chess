package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.List;
import java.util.Objects;

public class Rook extends Piece {
    private static final List<Direction> movableDirections = List.of(
            Direction.NORTH,
            Direction.SOUTH,
            Direction.EAST,
            Direction.WEST);

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        return checkMovable(source, target, movableDirections);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Rook piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, Rook.class);
    }
}
