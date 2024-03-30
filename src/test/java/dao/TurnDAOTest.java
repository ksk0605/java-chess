package dao;

import domain.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class TurnDAOTest {
    Connection connection;
    TurnDAO turnDAO;

    @BeforeEach
    void setUp() throws SQLException {
        connection = ConnectionGenerator.getConnection();
        connection.setAutoCommit(false);

        turnDAO = new TurnDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }

    @DisplayName("턴 진행상황을 업데이트 한다.")
    @Test
    void updateTurn() throws SQLException {
        // when
        turnDAO.update(Team.BLACK);
        final Team team = turnDAO.find();
        //then
        Assertions.assertThat(team).isEqualTo(Team.BLACK);
    }
}
