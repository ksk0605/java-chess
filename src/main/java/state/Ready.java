package state;

public class Ready implements GameState {

    @Override
    public GameState start() {
        return new Running();
    }

    @Override
    public GameState play() {

        return null;
    }

    @Override
    public GameState end() {

        return new End();
    }
}
