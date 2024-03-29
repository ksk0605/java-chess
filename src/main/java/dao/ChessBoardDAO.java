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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class ChessBoardDAO {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Map<Square, Piece> findAll() {
        final var query = "SELECT * FROM chessboard";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            final Map<Square, Piece> pieces = new HashMap<>();

            while (resultSet.next()) {
                final File file = File.from(resultSet.getString("file"));
                final Rank rank = Rank.from(Integer.parseInt(resultSet.getString("rank")));
                final Square square = new Square(file, rank);

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
                pieces.put(square, piece);
            }

            return pieces;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(final ChessBoard chessBoard) {
        final String deleteQuery = "DELETE FROM chessboard";
        final String insertQuery = "INSERT INTO chessboard (`piece`, `team`, `rank`, `file`) VALUES (?, ?, ?, ?)";

        try (final Connection connection = getConnection();
             final var deleteStatement = connection.prepareStatement(deleteQuery);
             final var insertStatement = connection.prepareStatement(insertQuery)) {

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

        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
