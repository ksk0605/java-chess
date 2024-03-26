package controller;

import state.GameState;
import state.Ready;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private GameState state;

    public ChessController() {
        this.state = new Ready();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printHeader();
        while (state.isNotEnd()) {
            try {
                final Menu menu = inputView.readMenu();
                state = menu.execute(state);
            } catch (final Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
