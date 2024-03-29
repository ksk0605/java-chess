package dao;

import domain.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnDAOTest {
    @DisplayName("DB에 저장된 턴을 가져온다.")
    @Test
    void turn() {
        // given
        final TurnDAO turnDAO = new TurnDAO();
        // when
        final Team team = turnDAO.find();
        //then
        Assertions.assertThat(team).isEqualTo(Team.WHITE);
    }

    @DisplayName("턴 진행상황을 업데이트 한다.")
    @Test
    void updateTurn() {
        // given
        final TurnDAO turnDAO = new TurnDAO();
        // when
        turnDAO.update(Team.WHITE);
        final Team team = turnDAO.find();
        //then
        Assertions.assertThat(team).isEqualTo(Team.WHITE);
    }
}
