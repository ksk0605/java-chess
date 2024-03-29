package controller.state;

public interface GameState {
    GameState start();

    GameState play();

    GameState status();

    GameState end();

    default boolean isNotEnd() {
        return true;
    }
}
