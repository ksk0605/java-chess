package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.List;
import java.util.Map;

public class Knight extends Piece {
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
    public boolean canMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        for (final Direction movableDirection : movableDirections()) {
            if (source.next(movableDirection).equals(target)) {
                return true;
            }
        }
        return false;
    }
}
