package state;

public interface GameState {
    GameState start();

    GameState play();

    GameState end();
}
