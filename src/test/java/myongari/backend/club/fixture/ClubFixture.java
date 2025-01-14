package myongari.backend.club.fixture;

import java.util.List;
import java.util.UUID;
import myongari.backend.club.domain.Apply;
import myongari.backend.club.domain.Category;
import myongari.backend.club.domain.Club;
import myongari.backend.club.domain.Image;
import myongari.backend.club.domain.President;
import myongari.backend.club.domain.State;
import myongari.backend.club.stub.FixedDateHolder;

@SuppressWarnings("NonAsciiCharacters")
public class ClubFixture {

    public static List<Club> 모든_동아리_정보_생성(Club... club) {
        return List.of(club);
    }

    public static Club 동아리_1_정보_생성() {
        Image 동아리_1_로고_이미지 = Image.builder()
                .uuid(UUID.randomUUID())
                .imageLink(null)
                .build();
        President 회장_1_정보 = President.builder()
                .name("홍길동")
                .contact("010-1111-1111")
                .email("example1@example.com")
                .build();
        Apply 동아리_1_지원_정보 = Apply.builder()
                .recruitmentStatus(State.Recruited)
                .recruitStartDate(new FixedDateHolder(8, 1).getDate())
                .recruitEndDate(new FixedDateHolder(8, 30).getDate())
                .applyLink("https://form.example.com/1")
                .qualifications("지원 조건 예시1")
                .build();
        return Club.builder()
                .name("동아리1")
                .location("학관1111")
                .snsLink("https://sns.example.com/1")
                .introduce("소개글 예시1")
                .activity("활동 예시1")
                .image(동아리_1_로고_이미지)
                .apply(동아리_1_지원_정보)
                .president(회장_1_정보)
                .category(카테고리1_생성())
                .build();
    }

    public static Club 동아리_2_정보_생성_이미지_없음() {
        President 회장_2_정보 = President.builder()
                .name("김철수")
                .contact("010-2222-2222")
                .email("example2@example.com")
                .build();
        Apply 동아리_2_지원_정보 = Apply.builder()
                .recruitmentStatus(State.Recruiting)
                .recruitStartDate(new FixedDateHolder(8, 1).getDate())
                .recruitEndDate(new FixedDateHolder(8, 10).getDate())
                .applyLink("https://form.example.com/2")
                .qualifications("지원 조건 예시2")
                .build();
        return Club.builder()
                .name("동아리2")
                .location("학관2222")
                .snsLink("https://sns.example.com/2")
                .introduce("소개글 예시2")
                .activity("활동 예시2")
                .image(null)
                .apply(동아리_2_지원_정보)
                .president(회장_2_정보)
                .category(카테고리1_생성())
                .build();
    }

    public static Club 동아리_3_정보_생성() {
        Image 동아리_3_로고_이미지 = Image.builder()
                .uuid(UUID.randomUUID())
                .build();
        President 회장_3_정보 = President.builder()
                .name("박지성")
                .contact("010-3333-3333")
                .email("example3@example.com")
                .build();
        Apply 동아리_3_지원_정보 = Apply.builder()
                .recruitmentStatus(State.Recruiting)
                .recruitStartDate(new FixedDateHolder(8, 20).getDate())
                .recruitEndDate(new FixedDateHolder(12, 31).getDate())
                .applyLink("https://form.example.com/3")
                .qualifications("지원 조건 예시3")
                .build();
        return Club.builder()
                .name("동아리3")
                .location("학관3333")
                .snsLink("https://sns.example.com/3")
                .introduce("소개글 예시3")
                .activity("활동 예시3")
                .image(동아리_3_로고_이미지)
                .apply(동아리_3_지원_정보)
                .president(회장_3_정보)
                .category(카테고리1_생성())
                .build();
    }

    public static Category 카테고리1_생성() {
        return Category.builder()
                .name("카테고리1")
                .build();
    }
}
