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

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public ChessBoard findAll() {
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
                if (pieceName.equals("king")) {
                    piece = new King(team);
                } else if (pieceName.equals("queen")) {
                    piece = new Queen(team);
                } else if (pieceName.equals("rook")) {
                    piece = new Rook(team);
                } else if (pieceName.equals("bishop")) {
                    piece = new Bishop(team);
                } else if (pieceName.equals("knight")) {
                    piece = new Knight(team);
                } else {
                    if (team == Team.BLACK) {
                        piece = new BlackPawn();
                    } else {
                        piece = new WhitePawn();
                    }
                }
                pieces.put(square, piece);
            }

            return new ChessBoard(pieces);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
