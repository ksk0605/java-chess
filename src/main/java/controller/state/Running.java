package controller.state;

import dao.ChessBoardDAO;
import dao.TurnDAO;
import domain.ChessBoard;
import domain.Square;
import dto.ChessBoardDTO;
import dto.StatusDTO;
import view.OutputView;

public class Running implements GameState {
    private final ChessBoard chessBoard;
    private final OutputView outputView = new OutputView();

    public Running(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("게임 중에는 시작할 수 없습니다.");
    }

    @Override
    public GameState play(final Square source, final Square target) {
        chessBoard.move(source, target);
        outputView.printChessBoard(ChessBoardDTO.from(chessBoard.getPieces()));

        if (chessBoard.isEnd()) {
            outputView.printStatus(StatusDTO.from(chessBoard.status()));
            final ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
            chessBoardDAO.update(ChessBoard.create());
            return new End();
        }

        return new Running(this.chessBoard);
    }

    @Override
    public GameState status() {
        outputView.printStatus(StatusDTO.from(chessBoard.status()));
        return new Running(chessBoard);
    }

    @Override
    public GameState end() {
        final ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
        chessBoardDAO.update(chessBoard);
        final TurnDAO turnDAO = new TurnDAO();
        turnDAO.update(chessBoard.currentTeam());
        return new End();
    }
}
