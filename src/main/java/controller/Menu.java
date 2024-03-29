package controller;

import controller.state.GameState;

public enum Menu {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String command;

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
            return state.play();
        }
        if (isStatus()) {
            return state.status();
        }
        return state.end();
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    private boolean isStatus() {
        return this == STATUS;
    }
}
