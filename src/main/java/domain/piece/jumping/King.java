package domain.piece.jumping;

import domain.Direction;
import domain.Team;

import java.util.List;

public class King extends JumpingPiece {
    private final int SCORE = 0;

    public King(final Team team) {
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

    @Override
    public boolean isKing() {
        return true;
    }
}
