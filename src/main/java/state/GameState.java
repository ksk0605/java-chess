package state;

import domain.Square;

public interface GameState {
    GameState start();

    GameState play(Square source, Square target);

    GameState end();

    default boolean isNotEnd() {
        return true;
    }
}
