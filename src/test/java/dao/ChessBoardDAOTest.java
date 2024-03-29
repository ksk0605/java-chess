package dao;

import domain.*;
import domain.piece.Piece;
import domain.piece.jumping.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardDAOTest {
    @DisplayName("체스보드 현황을 업데이트 한다.")
    @Test
    void update() {
        // given
        final Map<Square, Piece> pieces = Map.of(
                new Square(File.D, Rank.SEVEN), new King(Team.BLACK),
                new Square(File.D, Rank.SIX), new King(Team.BLACK));
        final ChessBoard chessBoard = new ChessBoard(pieces);
        // when
        final var chessBoardDAO = new ChessBoardDAO();
        chessBoardDAO.update(chessBoard);
        //then
        assertThat(chessBoardDAO.findAll().getPieces()).isEqualTo(pieces);
    }
}
