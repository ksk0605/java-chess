package controller.state;

import controller.MoveCommand;
import dao.ChessBoardDAO;
import dao.TurnDAO;
import domain.ChessBoard;
import domain.File;
import domain.Rank;
import domain.Square;
import dto.ChessBoardDTO;
import dto.StatusDTO;
import view.InputView;
import view.OutputView;

public class Running implements GameState {
    private final ChessBoard chessBoard;
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    public Running(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("게임 중에는 시작할 수 없습니다.");
    }

    @Override
    public GameState play() {

        final Square source = readSquare();
        final Square target = readSquare();

        chessBoard.move(source, target);
        outputView.printChessBoard(ChessBoardDTO.from(chessBoard.getPieces()));

        if (chessBoard.isFinished()) {
            outputView.printStatus(StatusDTO.from(chessBoard.status()));
            final ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
            chessBoardDAO.update(ChessBoard.init());
            return new End();
        }

        return new Running(chessBoard);
    }

    private Square readSquare() {
        final MoveCommand moveCommand = MoveCommand.fromInput(inputView.readMoveCommand());
        return new Square(File.from(moveCommand.file()), Rank.from(moveCommand.rank()));
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
