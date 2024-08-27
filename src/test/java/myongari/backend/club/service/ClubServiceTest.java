package myongari.backend.club.service;

import static myongari.backend.club.fixture.ClubFixture.동아리_1_정보_생성;
import static myongari.backend.club.fixture.ClubFixture.동아리_2_정보_생성_이미지_없음;
import static myongari.backend.club.fixture.ClubFixture.카테고리1_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import myongari.backend.club.application.ClubService;
import myongari.backend.club.application.port.CategoryRepository;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Club;
import myongari.backend.club.fake.CategoryFakeRepository;
import myongari.backend.club.fake.ClubFakeRepository;
import myongari.backend.club.fake.ClubImageFakeStorage;
import org.junit.jupiter.api.BeforeEach;
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
                new ClubImageFakeStorage());
    }

    @BeforeEach
    public void setUp() {
        categoryRepository.save(카테고리1_생성());
        clubRepository.save(동아리_1_정보_생성());
        clubRepository.save(동아리_2_정보_생성_이미지_없음());
    }

    @Test
    void 카테고리_이름이_저장소에_없다면_예외를_발생시킨다() {
        // given
        String categoryName = "카테고리2";

        // when & then
        assertThatThrownBy(() -> clubService.findClubNamesByCategoryName(categoryName))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void 동아리를_조회할_때_이미지_데이터를_같이_반환한다() {
        // given
        long id = 1L;

        // when
        Club club = clubService.findClubById(id);

        // then
        assertThat(club.getImage()).isNotNull();
    }

    @Test
    void 동아리를_조회할_때_이미지가_없다면_이미지_링크를_받지_않는다() {
        // given
        long id = 2L;

        // when
        Club club = clubService.findClubById(id);

        // then
        assertThat(club.getImage()).isNull();
    }
}
