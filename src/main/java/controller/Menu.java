package controller;

import domain.File;
import domain.Rank;
import domain.Square;
import state.GameState;
import view.InputView;

public enum Menu {
    START("start"),
    MOVE("move"),
    END("end"),
    DEFAULT("default");

    private final String command;
    private static final InputView inputView = new InputView();

    Menu(final String command) {
        this.command = command;
    }

    public static Menu fromInput(final String input) {
        for (final var value : Menu.values()) {
            if (value.command.equals(input)) {
                return value;
            }
        }
        throw new IllegalArgumentException("허용하지 않은 커멘드 입니다 start | end | move {} {} 로 입력해주세요.");
    }

    public GameState execute(final GameState state) {
        if (isStart()) {
            return state.start();
        }
        if (isMove()) {
            final Square source = readSquare();
            final Square target = readSquare();

            return state.play(source, target);
        }
        return state.end();
    }

    private Square readSquare() {
        final MoveCommand moveCommand = MoveCommand.fromInput(inputView.readMoveCommand());
        return new Square(File.from(moveCommand.file()), Rank.from(moveCommand.rank()));
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }
}
