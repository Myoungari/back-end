package myongari.backend.club.fixture;

import myongari.backend.club.domain.Apply;
import myongari.backend.club.domain.State;
import myongari.backend.club.stub.FixedDateHolder;

@SuppressWarnings("NonAsciiCharacters")
public class ApplyFixture {

    public static Apply 지원_정보_생성_시작_08_01_끝_08_30() {
        return Apply.builder()
                .recruitmentStatus(State.Recruiting)
                .recruitStartDate(new FixedDateHolder(8, 1).getDate())
                .recruitEndDate(new FixedDateHolder(8, 30).getDate())
                .build();
    }

    public static Apply 지원_정보_생성_시작_08_16_끝_08_30() {
        return Apply.builder()
                .recruitmentStatus(State.Recruiting)
                .recruitStartDate(new FixedDateHolder(8, 16).getDate())
                .recruitEndDate(new FixedDateHolder(8, 30).getDate())
                .build();
    }

    public static Apply 지원_정보_생성_시작_08_01_끝_08_10() {
        return Apply.builder()
                .recruitmentStatus(State.Recruiting)
                .recruitStartDate(new FixedDateHolder(8, 1).getDate())
                .recruitEndDate(new FixedDateHolder(8, 10).getDate())
                .build();
    }

    public static Apply 지원_정보_생성_시작_null_끝_08_30() {
        return Apply.builder()
                .recruitmentStatus(State.Recruiting)
                .recruitStartDate(null)
                .recruitEndDate(new FixedDateHolder(8, 30).getDate())
                .build();
    }

    public static Apply 지원_정보_생성_시작_08_01_끝_null() {
        return Apply.builder()
                .recruitmentStatus(State.Recruiting)
                .recruitStartDate(new FixedDateHolder(8, 1).getDate())
                .recruitEndDate(null)
                .build();
    }
}
