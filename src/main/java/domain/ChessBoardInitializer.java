package domain;

import domain.piece.Piece;
import domain.piece.jumping.King;
import domain.piece.jumping.Knight;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardInitializer {
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

    public static Map<Square, Piece> initialize() {
        final Map<Square, Piece> pieces = new HashMap<>();

        for (final File file : File.values()) {
            pieces.put(new Square(file, Rank.SEVEN), new BlackPawn());
            pieces.put(new Square(file, Rank.TWO), new WhitePawn());
            pieces.put(new Square(file, Rank.EIGHT), BLACK_PIECE_TYPE_ORDERS.get(file));
            pieces.put(new Square(file, Rank.ONE), WHITE_PIECE_TYPE_ORDERS.get(file));
        }

        return pieces;
    }
}
