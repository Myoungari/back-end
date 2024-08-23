package myongari.backend.club.infra;

import static myongari.backend.club.fixture.ClubFixture.동아리_1_정보_생성;
import static myongari.backend.club.fixture.ClubFixture.동아리_2_정보_생성;
import static myongari.backend.club.fixture.ClubFixture.동아리_3_정보_생성;
import static myongari.backend.club.fixture.ClubFixture.모든_동아리_정보_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;
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
        Club 동아리_1 = 동아리_1_정보_생성();
        Club 동아리_2 = 동아리_2_정보_생성();
        Club 동아리_3 = 동아리_3_정보_생성();
        List<Club> 모든_동아리_정보 = 모든_동아리_정보_생성(동아리_1, 동아리_2, 동아리_3);
        clubJpaRepository.saveAll(모든_동아리_정보);

        int page = 0;
        int size = 2;
        PageRequest pageRequest = PageRequest.of(page, size);

        // when
        Page<ClubSimple> clubSimpleAll = clubRepository.findClubSimpleAll(pageRequest);

        // then
        assertThat(clubSimpleAll.getNumber()).isEqualTo(page);
    }

    @Test
    void 카테고리별_동아리_이름_내역을_가져온다() {
        // given
        Club 동아리_1 = 동아리_1_정보_생성();
        Club 동아리_2 = 동아리_2_정보_생성();
        Club 동아리_3 = 동아리_3_정보_생성();
        List<Club> 모든_동아리_정보 = 모든_동아리_정보_생성(동아리_1, 동아리_2, 동아리_3);
        clubJpaRepository.saveAll(모든_동아리_정보);

        String categoryName = "카테고리1";

        // when
        List<ClubName> clubNames = clubRepository.findClubNamesByCategoryName(categoryName);

        // then
        assertSoftly(softly -> {
            softly.assertThat(clubNames).hasSize(3);
            softly.assertThat(clubNames.get(0).getClubName()).isEqualTo("동아리2");
            softly.assertThat(clubNames.get(1).getClubName()).isEqualTo("동아리3");
            softly.assertThat(clubNames.get(2).getClubName()).isEqualTo("동아리1");
        });
    }
}
