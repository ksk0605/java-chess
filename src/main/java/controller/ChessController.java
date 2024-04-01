package controller;

import controller.state.GameState;
import controller.state.Ready;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private GameState state;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.state = new Ready();
    }

    public void run() {
        outputView.printHeader();
        while (state.isContinue()) {
            try {
                final Menu menu = inputView.readMenu();
                state = menu.execute(state);
            } catch (final Exception e) {
                outputView.printError(e.getMessage());
            }
        }
        outputView.printEndMessage();
    }
}
