package myongari.backend.club.service;

import static myongari.backend.club.fixture.ClubFixture.동아리_1_정보_생성;
import static myongari.backend.club.fixture.ClubFixture.동아리_2_정보_생성_이미지_없음;
import static myongari.backend.club.fixture.ClubFixture.동아리_3_정보_생성;
import static myongari.backend.club.fixture.ClubFixture.모든_동아리_정보_생성;

import java.util.List;
import myongari.backend.club.application.RecruitStateScheduler;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.application.port.DateHolder;
import myongari.backend.club.domain.Club;
import myongari.backend.club.domain.State;
import myongari.backend.club.fake.ClubFakeRepository;
import myongari.backend.club.stub.FixedDateHolder;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class RecruitStateSchedulerTest {

    private final ClubRepository clubRepository;
    private final DateHolder dateHolder;
    private final RecruitStateScheduler recruitStateScheduler;

    public RecruitStateSchedulerTest() {
        this.clubRepository = new ClubFakeRepository();
        this.dateHolder = new FixedDateHolder(8, 15);
        this.recruitStateScheduler = new RecruitStateScheduler(this.clubRepository, this.dateHolder);
    }

    @Test
    void 동아리_리스트에서_모집_기간과_현재_시간을_비교하여_모집_상태를_갱신한_후_저장소에_반영한다() {
        // given
        List<Club> 모든_동아리_정보 = 모든_동아리_정보_생성(동아리_1_정보_생성(),
                동아리_2_정보_생성_이미지_없음(),
                동아리_3_정보_생성());
        clubRepository.saveAll(모든_동아리_정보);

        // when
        recruitStateScheduler.updateRecruitState();

        // then
        Club 동아리_1 = clubRepository.findClubById(1L).get();
        Club 동아리_2 = clubRepository.findClubById(2L).get();
        Club 동아리_3 = clubRepository.findClubById(3L).get();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(동아리_1.getApply().getRecruitmentStatus()).isEqualTo(State.RECRUITING);
            softly.assertThat(동아리_2.getApply().getRecruitmentStatus()).isEqualTo(State.RECRUITED);
            softly.assertThat(동아리_3.getApply().getRecruitmentStatus()).isEqualTo(State.RECRUITED);
        });
    }
}
