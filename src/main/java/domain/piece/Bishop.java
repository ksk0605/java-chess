package domain.piece;

import domain.Direction;
import domain.Team;

import java.util.List;

public class Bishop extends SlidingPiece {
    public Bishop(final Team team) {
        super(team);
    }

    @Override
    protected List<Direction> movableDirections() {
        return List.of(
                Direction.NORTH_EAST,
                Direction.NORTH_WEST,
                Direction.SOUTH_EAST,
                Direction.SOUTH_WEST);
    }
}
