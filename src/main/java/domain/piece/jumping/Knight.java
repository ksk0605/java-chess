package domain.piece.jumping;

import domain.Direction;
import domain.Team;

import java.util.List;

public class Knight extends JumpingPiece {
    public Knight(final Team team) {
        super(team);
    }

    @Override
    protected List<Direction> movableDirections() {
        return List.of(
                Direction.NORTH_NORTH_EAST,
                Direction.NORTH_NORTH_WEST,
                Direction.SOUTH_SOUTH_EAST,
                Direction.SOUTH_SOUTH_WEST,
                Direction.EAST_EAST_NORTH,
                Direction.EAST_EAST_SOUTH,
                Direction.WEST_WEST_NORTH,
                Direction.WEST_WEST_SOUTH
        );
    }

    @Override
    public double score() {
        return 2.5;
    }
}
