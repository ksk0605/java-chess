package domain.piece.sliding;

import domain.Direction;
import domain.Team;

import java.util.List;

public class Queen extends SlidingPiece {
    public static final int SCORE = 9;

    public Queen(final Team team) {
        super(team);
    }

    @Override
    protected List<Direction> movableDirections() {
        return List.of(
                Direction.NORTH,
                Direction.SOUTH,
                Direction.EAST,
                Direction.WEST,
                Direction.NORTH_EAST,
                Direction.NORTH_WEST,
                Direction.SOUTH_EAST,
                Direction.SOUTH_WEST);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
