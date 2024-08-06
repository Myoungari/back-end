package myongari.backend.club.infra;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.entity.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class ClubRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    private ClubRepository clubRepository;

    @Autowired
    public ClubRepositoryTest(ClubJpaRepository clubJpaRepository) {
        clubRepository = new ClubRepositoryImpl(clubJpaRepository);
    }

    @Test
    void ddl_auto_를_none으로_설정하면_test_초기화할_때_dummy_data_자동_주입() {
        // give & when
        Club club = em.find(Club.class, 1);

        // then
        assertThat(club.getId()).isEqualTo(1);
    }

    @Test
    void 동아리_정보들을_가져온다() {
        // when
        List<ClubSimple> clubSimpleAll = clubRepository.getClubSimpleAll();

        // then
        assertThat(clubSimpleAll.get(0).getName()).isEqualTo("표현의 자유");
    }

    @Test
    void 카테고리별_동아리_이름_내역을_가져온다() {
        // given
        String categoryName = "평면예술분과";

        // when
        List<ClubName> clubNames = clubRepository.getClubNamesByCategory(categoryName);

        // then
        assertThat(clubNames.get(0).getClubName()).isEqualTo("표현의 자유");
    }
}
