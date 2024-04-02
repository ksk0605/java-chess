package controller.state;

import dao.ChessBoardDao;
import dao.MySqlConnectionPool;
import dao.TurnDao;
import domain.ChessBoard;
import domain.Square;
import domain.Team;
import domain.piece.Piece;
import dto.ChessBoardDto;
import view.OutputView;

import java.sql.SQLException;
import java.util.Map;

public class Ready implements GameState {
    OutputView outputView = new OutputView();

    @Override
    public GameState start() throws SQLException {
        final ChessBoardDao chessBoardDAO = new ChessBoardDao(new MySqlConnectionPool());
        final Map<Square, Piece> pieces = chessBoardDAO.findAll();
        final TurnDao turnDAO = new TurnDao(new MySqlConnectionPool());
        final Team team = turnDAO.find();

        final ChessBoard chessBoard = new ChessBoard(pieces, team);

        outputView.printChessBoard(ChessBoardDto.from(chessBoard.getPieces()));
        return new Running(chessBoard);
    }

    @Override
    public GameState play() {
        throw new UnsupportedOperationException("게임을 시작해주세요.");
    }

    @Override
    public GameState status() {
        throw new UnsupportedOperationException("게임을 시작해주세요.");
    }

    @Override
    public GameState end() {
        return new End();
    }
}
