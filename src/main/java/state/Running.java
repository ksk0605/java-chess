package state;

public class Running implements GameState {

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("게임 중에는 시작할 수 없습니다.");
    }

    @Override
    public GameState play() {
        return new Running();
    }

    @Override
    public GameState end() {

        return new End();
    }
}
