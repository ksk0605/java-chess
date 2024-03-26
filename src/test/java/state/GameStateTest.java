package state;

import domain.ChessBoard;
import domain.File;
import domain.Rank;
import domain.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStateTest {
    Square source = new Square(File.A, Rank.TWO);
    Square target = new Square(File.A, Rank.THREE);

    @DisplayName("게임 준비 -> 게임 중")
    @Test
    void startWhenReady() {
        // given
        final GameState state = new Ready();
        // when
        final GameState actual = state.start();
        //then
        Assertions.assertThat(actual).isInstanceOf(Running.class);
    }

    @DisplayName("게임 준비 중 게임 시도 X")
    @Test
    void playWhenReady() {
        // given
        final GameState state = new Ready();
        //then
        Assertions.assertThatCode(() -> state.play(source, target))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임을 시작해주세요.");
    }

    @DisplayName("게임 준비 -> 게임 종료")
    @Test
    void endWhenReady() {
        // given
        final GameState state = new Ready();
        // when
        final GameState actual = state.end();
        //then
        Assertions.assertThat(actual).isInstanceOf(End.class);
    }

    @DisplayName("게임 중 -> 게임 중")
    @Test
    void playWhenRunning() {
        // given
        final GameState state = new Running(ChessBoard.create());
        // when
        final GameState actual = state.play(source, target);
        //then
        Assertions.assertThat(actual).isInstanceOf(Running.class);
    }

    @DisplayName("게임 중 시작 X")
    @Test
    void startWhenRunning() {
        // given
        final GameState state = new Running(ChessBoard.create());
        //then
        Assertions.assertThatCode(state::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임 중에는 시작할 수 없습니다.");
    }

    @DisplayName("게임 중 -> 게임 종료")
    @Test
    void endWhenRunning() {
        // given
        final GameState state = new Running(ChessBoard.create());
        // when
        final GameState actual = state.end();
        //then
        Assertions.assertThat(actual).isInstanceOf(End.class);
    }
}
