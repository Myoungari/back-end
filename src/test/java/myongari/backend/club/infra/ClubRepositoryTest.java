package myongari.backend.club.infra;

import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.entity.Club;
import myongari.backend.club.presentation.dto.ClubCount;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ClubRepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(ClubRepositoryTest.class);
    private ClubRepository clubRepository;

    @Autowired
    public ClubRepositoryTest(ClubJpaRepository clubJpaRepository) {
        clubRepository = new ClubRepositoryImpl(clubJpaRepository);
    }

    @Test
    void 모든_동아리_정보들을_페이징_조회한다() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 2);

        // when
        Page<ClubSimple> clubSimpleAll = clubRepository.getClubSimpleAll(pageRequest);

        // then
        assertThat(clubSimpleAll.getContent().get(0).getName()).isEqualTo("표현의 자유");
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

    @Test
    void 등록된_모든_동아리_갯수를_가져온다() {
        // when
        ClubCount clubCount = clubRepository.getClubCount();

        // then
        assertThat(clubCount.getCount()).isNotNull();
    }

    @Test
    void 동아리_id별_상세_정보를_가져온다() {
        // give
        Long id = 1L;

        // when
        Club club = clubRepository.getClubById(id);

        // then
        assertThat(club).isNotNull();
    }
}
