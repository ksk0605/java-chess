package dao;

import domain.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDAO {
    private final Connection connection;

    public TurnDAO() {
        connection = ConnectionGenerator.getConnection();
    }

    public TurnDAO(final Connection connection) {
        this.connection = connection;
    }

    public Team find() {
        final var query = "SELECT team FROM turn";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String team = resultSet.getString("team");
                return Team.from(team);
            }
            return null;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(final Team team) {
        final var query = "update turn set team = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, team.name().toLowerCase());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
