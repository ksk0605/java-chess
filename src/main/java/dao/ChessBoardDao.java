package dao;

import domain.*;
import domain.piece.Piece;
import domain.piece.jumping.King;
import domain.piece.jumping.Knight;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class ChessBoardDao {
    private final Connection connection;

    public ChessBoardDao(final Connection connection) {
        this.connection = connection;
    }

    public ChessBoardDao() {
        this.connection = ConnectionGenerator.getConnection();
    }

    public Map<Square, Piece> findAll() {
        final var query = "SELECT * FROM chessboard";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            final Map<Square, Piece> pieces = new HashMap<>();
            while (resultSet.next()) {
                final Square square = createSquare(resultSet);
                final Piece piece = createPiece(resultSet);
                pieces.put(square, piece);
            }
            return pieces;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Square createSquare(final ResultSet resultSet) throws SQLException {
        final File file = File.from(resultSet.getString("file"));
        final Rank rank = Rank.from(Integer.parseInt(resultSet.getString("rank")));
        return new Square(file, rank);
    }

    private static Piece createPiece(final ResultSet resultSet) throws SQLException {
        final String pieceName = resultSet.getString("piece");
        final Piece piece;
        final Team team = Team.from(resultSet.getString("team"));
        switch (pieceName) {
            case "king" -> piece = new King(team);
            case "queen" -> piece = new Queen(team);
            case "rook" -> piece = new Rook(team);
            case "bishop" -> piece = new Bishop(team);
            case "knight" -> piece = new Knight(team);
            default -> {
                if (team == Team.BLACK) {
                    piece = new BlackPawn();
                } else {
                    piece = new WhitePawn();
                }
            }
        }
        return piece;
    }

    public void update(final ChessBoard chessBoard) throws SQLException {
        final String deleteQuery = "DELETE FROM chessboard";
        final String insertQuery = "INSERT INTO chessboard (`piece`, `team`, `rank`, `file`) VALUES (?, ?, ?, ?)";

        try (final var deleteStatement = connection.prepareStatement(deleteQuery);
             final var insertStatement = connection.prepareStatement(insertQuery)) {
            connection.setAutoCommit(false);
            deleteStatement.executeUpdate();

            for (final Map.Entry<Square, Piece> entry : chessBoard.getPieces().entrySet()) {
                final Square square = entry.getKey();
                final Piece piece = entry.getValue();
                if (piece instanceof BlackPawn || piece instanceof WhitePawn) {
                    insertStatement.setString(1, "pawn");
                } else {
                    insertStatement.setString(1, piece.getClass().getSimpleName().toLowerCase());
                }
                insertStatement.setString(2, piece.team().name().toLowerCase());
                insertStatement.setString(3, String.valueOf(square.rank().index()));
                insertStatement.setString(4, square.file().name().toLowerCase());
                insertStatement.executeUpdate();
            }
            connection.commit();
        } catch (final SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }
}
