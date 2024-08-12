package myongari.backend.club.fixture;

import myongari.backend.club.domain.Apply;
import myongari.backend.club.domain.Category;
import myongari.backend.club.domain.Club;
import myongari.backend.club.domain.President;
import myongari.backend.club.domain.State;

import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class ClubFixture {

    public static List<Club> 모든_동아리_정보_생성() {
        President 회장_1_정보 = new President("홍길동", "010-1111-1111", "example1@example.com");
        President 회장_2_정보 = new President("김철수", "010-2222-2222", "example2@example.com");

        Apply 동아리_1_지원_정보 = new Apply(State.Recruited, "https://form.example.com/1", "지원 조건 예시1");
        Apply 동아리_2_지원_정보 = new Apply(State.Recruiting, "https://form.example.com/2", "지원 조건 예시2");

        Category 카테고리1 = new Category("카테고리1");
        Category 카테고리2 = new Category("카테고리2");

        return List.of(new Club("동아리1", "학관1111", "https://sns.example.com/1", "https://image.example.com/1", "소개글 예시1", "활동 예시1",
                        동아리_1_지원_정보, 회장_1_정보, 카테고리1),
                new Club("동아리2", "학관2222", "https://sns.example.com/2", "https://image.example.com/2", "소개글 예시2", "활동 예시2",
                        동아리_2_지원_정보, 회장_2_정보, 카테고리2));
    }
}
