package controller.state;

import dao.ChessBoardDAO;
import dao.TurnDAO;
import domain.ChessBoard;
import domain.Square;
import domain.Team;
import domain.piece.Piece;
import dto.ChessBoardDTO;
import view.OutputView;

import java.util.Map;

public class Ready implements GameState {
    OutputView outputView = new OutputView();

    @Override
    public GameState start() {
        final ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
        final Map<Square, Piece> pieces = chessBoardDAO.findAll();
        final TurnDAO turnDAO = new TurnDAO();
        final Team team = turnDAO.find();

        final ChessBoard chessBoard = new ChessBoard(pieces, team);

        outputView.printChessBoard(ChessBoardDTO.from(chessBoard.getPieces()));
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
