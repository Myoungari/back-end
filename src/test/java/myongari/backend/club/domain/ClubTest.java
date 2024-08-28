package myongari.backend.club.domain;

import static myongari.backend.club.fixture.ApplyFixture.지원_정보_생성_시작_08_01_끝_08_10;
import static myongari.backend.club.fixture.ApplyFixture.지원_정보_생성_시작_08_01_끝_08_30;
import static myongari.backend.club.fixture.ApplyFixture.지원_정보_생성_시작_08_16_끝_08_30;
import static org.assertj.core.api.Assertions.assertThat;

import myongari.backend.club.application.port.DateHolder;
import myongari.backend.club.stub.FixedDateHolder;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class ClubTest {

    private final DateHolder dateHolder;

    public ClubTest() {
        this.dateHolder = new FixedDateHolder(8, 15);
    }

    @Test
    void 동아리_모집_상태가_미정or모집_조기_마감or모집_취소_상태일_경우_모집_기간을_통해_모집_상태를_변경하지_않는다() {

    }

    @Test
    void 현재_기간이_동아리_모집_기간_안에_속한다면_모집중_상태를_반환한다() {
        // given
        Apply apply = 지원_정보_생성_시작_08_01_끝_08_30();

        // when
        apply.setRecruitmentStatus(dateHolder);

        // then
        assertThat(apply.getRecruitmentStatus()).isEqualTo(State.Recruiting);
    }

    @Test
    void 현재_기간이_동아리_모집_시작_기간_보다_빠르다면_모집예정_상태를_반환한다() {
        // given
        Apply apply = 지원_정보_생성_시작_08_16_끝_08_30();

        // when
        apply.setRecruitmentStatus(dateHolder);

        // then
        assertThat(apply.getRecruitmentStatus()).isEqualTo(State.Pending);
    }

    @Test
    void 현재_기간이_동아리_모집_끝_기간을_지났다면_모집마감_상태를_반환한다() {
        // given
        Apply apply = 지원_정보_생성_시작_08_01_끝_08_10();

        // when
        apply.setRecruitmentStatus(dateHolder);

        // then
        assertThat(apply.getRecruitmentStatus()).isEqualTo(State.Recruited);
    }
}
