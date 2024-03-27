package state;

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

        return new Running(this.chessBoard);
    }

    @Override
    public GameState status() {
        outputView.printStatus(StatusDTO.from(chessBoard.status()));
        return new Running(chessBoard);
    }

    @Override
    public GameState end() {
        return new End();
    }
}
