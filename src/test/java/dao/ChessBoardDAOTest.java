package dao;

import domain.*;
import domain.piece.Piece;
import domain.piece.jumping.King;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardDAOTest {
    private ChessBoardDAO chessBoardDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = ConnectionGenerator.getConnection();
        connection.setAutoCommit(false);

        chessBoardDAO = new ChessBoardDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        // 테스트 이후에는 모든 변경사항을 롤백
        connection.rollback();
        connection.close();
    }


    @DisplayName("체스보드 현황을 업데이트 한다.")
    @Test
    void update() throws SQLException {
        // given
        final Map<Square, Piece> pieces = Map.of(
                new Square(File.D, Rank.SEVEN), new King(Team.BLACK),
                new Square(File.D, Rank.SIX), new King(Team.WHITE));
        final ChessBoard chessBoard = new ChessBoard(pieces);
        // when
        chessBoardDAO.update(chessBoard);
        //then
        assertThat(chessBoardDAO.findAll()).isEqualTo(pieces);
    }
}
