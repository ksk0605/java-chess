package domain.piece.sliding;

import domain.Direction;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SlidingPiece extends Piece {

    protected SlidingPiece(final Team team) {
        super(team);
    }

    protected abstract List<Direction> movableDirections();

    @Override
    public boolean canMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        final List<Square> path = calculatePath(source, pieces);
        return path.contains(target);
    }

    private List<Square> calculatePath(final Square source, final Map<Square, Piece> pieces) {
        return movableDirections().stream()
                .flatMap(direction -> calculatePathInDirection(source, direction, pieces).stream())
                .toList();
    }

    private List<Square> calculatePathInDirection(final Square source, final Direction direction, final Map<Square, Piece> pieces) {
        final List<Square> movableSquares = new ArrayList<>();
        Square movableSource = source;

        while (movableSource.canMove(direction)) {
            movableSource = movableSource.next(direction);
            // TODO: Pieces 를 넘겨주어 가독성 개선 & 순환참조 인터페이스로 해결
            if (pieces.containsKey(movableSource) && pieces.get(movableSource).isSameTeam(pieces.get(source))) {
                break;
            }
            movableSquares.add(movableSource);
        }

        return movableSquares;
    }
}
