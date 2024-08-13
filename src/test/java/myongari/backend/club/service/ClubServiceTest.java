package myongari.backend.club.service;

import myongari.backend.club.application.ClubService;
import myongari.backend.club.application.port.CategoryRepository;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.fake.CategoryFakeRepository;
import myongari.backend.club.fake.ClubFakeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static myongari.backend.club.fixture.ClubFixture.카테고리1_생성;

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
                categoryRepository);
    }

    @Test
    void 카테고리_이름이_저장소에_없다면_예외를_발생시킨다() {
        // given
        categoryRepository.save(카테고리1_생성());
        String categoryName = "카테고리2";

        // when & then
        Assertions.assertThatThrownBy(() -> clubService.findClubNamesByCategoryName(categoryName))
                .isInstanceOf(NoSuchElementException.class);
    }

}
