package domain.piece;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    // TODO: 테스트 분리
    @DisplayName("퀸은 상하좌우 대각선으로 여러칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square source, final Square target, final boolean expected) {
        final Queen queen = new Queen(Team.BLACK);

        // when
        final boolean canMove = queen.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isEqualTo(expected);
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.A, Rank.FOUR), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.H, Rank.FOUR), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.D, Rank.ONE), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.D, Rank.EIGHT), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.H, Rank.EIGHT), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.A, Rank.ONE), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.A, Rank.SEVEN), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.G, Rank.ONE), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.G, Rank.TWO), false),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.G, Rank.THREE), false),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.G, Rank.FIVE), false));
    }
}
