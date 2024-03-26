package domain.piece;

import domain.Direction;
import domain.Team;

import java.util.List;

public class Rook extends SlidingPiece {
    public Rook(final Team team) {
        super(team);
    }

    @Override
    protected List<Direction> movableDirections() {
        return List.of(
                Direction.NORTH,
                Direction.SOUTH,
                Direction.EAST,
                Direction.WEST);
    }
}
