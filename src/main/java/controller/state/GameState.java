package controller.state;

import java.sql.SQLException;

public interface GameState {
    GameState start() throws SQLException;

    GameState play() throws SQLException;

    GameState status();

    GameState end() throws SQLException;

    default boolean isNotEnd() {
        return true;
    }
}
