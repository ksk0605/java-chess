package controller.state;

import domain.Square;

public class End implements GameState {
    @Override
    public GameState start() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public GameState play(final Square source, final Square target) {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public GameState status() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }

    @Override
    public boolean isNotEnd() {
        throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
    }
}
