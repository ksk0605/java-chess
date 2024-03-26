package domain.piece.pawn;

import domain.Direction;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.util.List;
import java.util.Map;

public abstract class Pawn extends Piece {
    Pawn(final Team team) {
        super(team);
    }

    @Override
    protected List<Direction> movableDirections() {
        return List.of();
    }

    public abstract boolean canMove(final Square source, final Square target, final Map<Square, Piece> pieces);

    protected void validateHasSameTeamPieceOnTarget(final Square source, final Square target, final Map<Square, Piece> pieces) {
        final Piece targetPiece = pieces.get(target);
        final Piece sourcePiece = pieces.get(source);

        if (targetPiece.isSameTeam(sourcePiece)) {
            throw new IllegalArgumentException("같은 팀은 공격할 수 없습니다.");
        }
    }
}
