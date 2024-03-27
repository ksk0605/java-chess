package state;

import domain.Square;

public class End implements GameState {
    @Override
    public GameState start() {
        return null;
    }

    @Override
    public GameState play(final Square source, final Square target) {
        return null;
    }

    @Override
    public GameState status() {
        return null;
    }

    @Override
    public GameState end() {
        return null;
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }
}
