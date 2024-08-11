package myongari.backend.club.fixture;

import java.util.List;
import myongari.backend.club.entity.State;
import myongari.backend.club.presentation.dto.ClubName;
import myongari.backend.club.presentation.dto.ClubSimple;
import myongari.backend.club.presentation.dto.ClubSimplePage;

public class ClubFixture {

    public static List<ClubSimple> 모든_동아리_정보_생성() {
        return List.of(new ClubSimple("표현의 자유", "http://example.link1", State.Recruited, "소개글 예시1"),
                new ClubSimple("A&P", "http://example.link2", State.Recruiting, "소개글 예시2"),
                new ClubSimple("TIME", "http://example.link3", State.Recruiting, "소개글 예시3"));
    }

    public static ClubSimplePage 모든_동아리_정보_1_페이지() {
        return new ClubSimplePage(1, 3, 1, 모든_동아리_정보_생성());
    }

    public static List<ClubName> 특정_카테고리에_속한_동아리_이름_생성() {
        return List.of(new ClubName("표현의 자유"),
                new ClubName("A&P"));
    }
}
