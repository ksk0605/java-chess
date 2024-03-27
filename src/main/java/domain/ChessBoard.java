package domain;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.jumping.King;
import domain.piece.jumping.Knight;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private static final Map<File, Piece> BLACK_PIECE_TYPE_ORDERS = Map.of(
            File.A, new Rook(Team.BLACK), File.B, new Knight(Team.BLACK),
            File.C, new Bishop(Team.BLACK), File.D, new Queen(Team.BLACK),
            File.E, new King(Team.BLACK), File.F, new Bishop(Team.BLACK),
            File.G, new Knight(Team.BLACK), File.H, new Rook(Team.BLACK)
    );
    private static final Map<File, Piece> WHITE_PIECE_TYPE_ORDERS = Map.of(
            File.A, new Rook(Team.WHITE), File.B, new Knight(Team.WHITE),
            File.C, new Bishop(Team.WHITE), File.D, new Queen(Team.WHITE),
            File.E, new King(Team.WHITE), File.F, new Bishop(Team.WHITE),
            File.G, new Knight(Team.WHITE), File.H, new Rook(Team.WHITE)
    );

    private final Pieces pieces;
    private Team team;

    public ChessBoard() {
        this.pieces = new Pieces(new HashMap<>());
    }

    public ChessBoard(final Map<Square, Piece> pieces) {
        this.pieces = new Pieces(pieces);
        this.team = Team.WHITE;
    }

    public static ChessBoard create() {
        final Map<Square, Piece> chessTable = new HashMap<>();

        for (final File file : File.values()) {
            chessTable.put(new Square(file, Rank.SEVEN), new BlackPawn());
            chessTable.put(new Square(file, Rank.TWO), new WhitePawn());
            chessTable.put(new Square(file, Rank.EIGHT), BLACK_PIECE_TYPE_ORDERS.get(file));
            chessTable.put(new Square(file, Rank.ONE), WHITE_PIECE_TYPE_ORDERS.get(file));
        }

        return new ChessBoard(chessTable);
    }

    public void move(final Square source, final Square target) {
        validateEmptySource(source);
        validateSameSquare(source, target);
        validateTeam(source);
        validateMove(source, target);

        pieces.move(source, target);
        team = team.turn();
    }

    private void validateSameSquare(final Square source, final Square target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("제자리 이동은 불가능합니다.");
        }
    }

    private void validateEmptySource(final Square source) {
        if (pieces.hasPiece(source)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
    }

    private void validateTeam(final Square source) {
        if (pieces.isOppositeTeam(source, team)) {
            throw new IllegalArgumentException("자기 말이 아닙니다.");
        }
    }

    private void validateMove(final Square source, final Square target) {
        if (pieces.cannotMove(source, target)) {
            throw new IllegalArgumentException("갈 수 없는 경로입니다.");
        }
    }

    public Map<Team, Double> status() {
        final double whiteScore = pieces.score(Team.BLACK);
        final double blackScore = pieces.score(Team.WHITE);
        return Map.of(Team.WHITE, whiteScore, Team.BLACK, blackScore);
    }

    public Map<Square, Piece> getPieces() {
        return pieces.getPieces();
    }
}
