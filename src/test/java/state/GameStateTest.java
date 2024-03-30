package state;

import controller.state.End;
import controller.state.GameState;
import controller.state.Ready;
import controller.state.Running;
import domain.ChessBoardInitializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class GameStateTest {
    @DisplayName("게임 준비 -> 게임 중")
    @Test
    void startWhenReady() throws SQLException {
        // given
        final GameState state = new Ready();
        // when
        final GameState actual = state.start();
        //then
        assertThat(actual).isInstanceOf(Running.class);
    }

    @DisplayName("게임 준비 중 게임 시도 X")
    @Test
    void playWhenReady() {
        // given
        final GameState state = new Ready();
        //then
        assertThatCode(() -> state.play())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임을 시작해주세요.");
    }

    @DisplayName("게임 준비 -> 게임 종료")
    @Test
    void endWhenReady() throws SQLException {
        // given
        final GameState state = new Ready();
        // when
        final GameState actual = state.end();
        //then
        assertThat(actual).isInstanceOf(End.class);
    }

    @DisplayName("게임 중 -> 게임 중")
    @Test
        // TODO: 구조변경으로 인한 테스트 깨짐 해결
    void playWhenRunning() throws SQLException {
        // given
        final GameState state = new Running(ChessBoardInitializer.initialize());
        // when
        final GameState actual = state.play();
        //then
        assertThat(actual).isInstanceOf(Running.class);
    }

    @DisplayName("게임 중 시작 X")
    @Test
    void startWhenRunning() {
        // given
        final GameState state = new Running(ChessBoardInitializer.initialize());
        //then
        assertThatCode(state::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임 중에는 시작할 수 없습니다.");
    }

    @DisplayName("게임 중 -> 게임 종료")
    @Test
    void endWhenRunning() throws SQLException {
        // given
        final GameState state = new Running(ChessBoardInitializer.initialize());
        // when
        final GameState actual = state.end();
        //then
        assertThat(actual).isInstanceOf(End.class);
    }
}
