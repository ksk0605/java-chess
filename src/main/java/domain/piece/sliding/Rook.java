package domain.piece.sliding;

import domain.Direction;
import domain.Team;

import java.util.List;

public class Rook extends SlidingPiece {
    public static final int SCORE = 5;

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

    @Override
    public double getScore() {
        return SCORE;
    }
}
