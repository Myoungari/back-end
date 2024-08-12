package myongari.backend.club.infra;

import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static myongari.backend.club.fixture.ClubFixture.모든_동아리_정보_생성;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ClubRepositoryImpl.class)
@ActiveProfiles("test")
@SuppressWarnings("NonAsciiCharacters")
public class ClubRepositoryTest {

    @Autowired
    private ClubJpaRepository clubJpaRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Test
    void 모든_동아리_정보들을_페이징_조회한다() {
        // given
        List<Club> 모든_동아리_정보 = 모든_동아리_정보_생성();
        clubJpaRepository.saveAll(모든_동아리_정보);

        int page = 0;
        int size = 2;
        PageRequest pageRequest = PageRequest.of(page, size);

        // when
        Page<ClubSimple> clubSimpleAll = clubRepository.getClubSimpleAll(pageRequest);

        // then
        assertThat(clubSimpleAll.getNumber()).isEqualTo(page);
    }

    @Test
    void 카테고리별_동아리_이름_내역을_가져온다() {
        // given
        List<Club> 모든_동아리_정보 = 모든_동아리_정보_생성();
        clubJpaRepository.saveAll(모든_동아리_정보);

        String categoryName = "카테고리1";

        // when
        List<ClubName> clubNames = clubRepository.getClubNamesByCategory(categoryName);

        // then
        assertThat(clubNames.get(0).getClubName()).isEqualTo("동아리1");
    }
}
