package myongari.backend.club.service;

import static myongari.backend.club.fixture.ClubFixture.동아리_1_정보_생성;
import static myongari.backend.club.fixture.ClubFixture.동아리_2_정보_생성_이미지_없음;
import static myongari.backend.club.fixture.ClubFixture.카테고리1_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.NoSuchElementException;
import myongari.backend.club.application.ClubService;
import myongari.backend.club.application.port.CategoryRepository;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.fake.CategoryFakeRepository;
import myongari.backend.club.fake.ClubFakeRepository;
import myongari.backend.club.stub.ClubImageStubStorage;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class ClubServiceTest {

    private final CategoryRepository categoryRepository;

    private final ClubRepository clubRepository;

    private final ClubService clubService;

    public ClubServiceTest() {
        categoryRepository = new CategoryFakeRepository();
        clubRepository = new ClubFakeRepository();
        clubService = new ClubService(
                clubRepository,
                categoryRepository,
                new ClubImageStubStorage());
    }

    @Test
    void 카테고리가_저장소에_없다면_예외를_발생시킨다() {
        // given
        categoryRepository.save(카테고리1_생성());
        String categoryName = "카테고리2";

        // when & then
        assertThatThrownBy(() -> clubService.findClubNamesAndClubDetailByCategoryName(categoryName))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("카테고리를 찾지 못했습니다.");
    }

    @Test
    void 카테고리_내_동아리가_없다면_예외를_발생시킨다() {
        // given
        categoryRepository.save(카테고리1_생성());
        String categoryName = "카테고리1";
        long clubId = 1L;

        // when & then
        assertSoftly(softly -> {
            assertThatThrownBy(() -> clubService.findClubNamesAndClubDetailByCategoryName(categoryName))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage("카테고리 내 동아리가 존재하지 않습니다.");
            assertThatThrownBy(() -> clubService.findClubNamesAndClubDetailByCategoryName(categoryName, clubId))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage("카테고리 내 동아리가 존재하지 않습니다.");
        });
    }

    @Test
    void 동아리를_조회할_때_이미지_데이터를_같이_반환한다() {
        // given
        categoryRepository.save(카테고리1_생성());
        clubRepository.save(동아리_1_정보_생성());
        long id = 1L;

        // when
        Club club = clubService.findClubById(id);

        // then
        assertThat(club.getImage()).isNotNull();
    }

    @Test
    void 동아리를_조회할_때_이미지가_없다면_이미지_링크를_받지_않는다() {
        // given
        categoryRepository.save(카테고리1_생성());
        clubRepository.save(동아리_2_정보_생성_이미지_없음());
        long id = 1L;

        // when
        Club club = clubService.findClubById(id);

        // then
        assertThat(club.getImage()).isNull();
    }
}
