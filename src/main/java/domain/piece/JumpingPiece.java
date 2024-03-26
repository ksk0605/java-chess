package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.List;
import java.util.Map;

public abstract class JumpingPiece extends Piece {
    protected JumpingPiece(final Team team) {
        super(team);
    }

    protected abstract List<Direction> movableDirections();

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
