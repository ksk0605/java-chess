package controller.state;

import dao.ChessBoardDAO;
import domain.ChessBoard;
import domain.Square;
import dto.ChessBoardDTO;
import view.OutputView;

public class Ready implements GameState {
    OutputView outputView = new OutputView();

    @Override
    public GameState start() {
        final ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
        final ChessBoard chessBoard = chessBoardDAO.findAll();
        outputView.printChessBoard(ChessBoardDTO.from(chessBoard.getPieces()));
        return new Running(chessBoard);
    }

    @Override
    public GameState play(final Square source, final Square target) {
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