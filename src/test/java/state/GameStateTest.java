package state;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStateTest {
    @DisplayName("게임 준비 -> 게임 중")
    @Test
    void readyToRunning() {
        // given
        final GameState state = new Ready();
        // when
        final GameState actual = state.start();
        //then
        Assertions.assertThat(actual).isInstanceOf(Running.class);
    }

    @DisplayName("게임 준비 -> 게임 종료")
    @Test
    void readyToEnd() {
        // given
        final GameState state = new Ready();
        // when
        final GameState actual = state.end();
        //then
        Assertions.assertThat(actual).isInstanceOf(End.class);
    }

    @DisplayName("게임 중 -> 게임 중")
    @Test
    void runningToRunning() {
        // given
        final GameState state = new Running();
        // when
        final GameState actual = state.play();
        //then
        Assertions.assertThat(actual).isInstanceOf(Running.class);
    }

    @DisplayName("게임 중 -> 게임 시작 X")
    @Test
    void runningToStart() {
        // given
        final GameState state = new Running();
        //then
        Assertions.assertThatCode(state::start).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임 중에는 시작할 수 없습니다.");
    }

    @DisplayName("게임 중 -> 게임 종료")
    @Test
    void runningToEnd() {
        // given
        final GameState state = new Running();
        // when
        final GameState actual = state.end();
        //then
        Assertions.assertThat(actual).isInstanceOf(End.class);
    }
}
