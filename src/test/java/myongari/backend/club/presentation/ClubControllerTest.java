package myongari.backend.club.presentation;

import myongari.backend.club.application.ClubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static myongari.backend.club.fixture.ClubFixture.모든_동아리_정보_1_페이지;
import static myongari.backend.club.fixture.ClubFixture.특정_카테고리에_속한_동아리_이름_생성;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ActiveProfiles("test")
@SuppressWarnings("NonAsciiCharacters")
public class ClubControllerTest {

    @MockBean
    private ClubService clubService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void 모든_동아리_정보를_페이지_별로_출력한다() throws Exception {
        int page = 1;
        int size = 3;

        when(clubService.getClubSimpleAll(page, size)).thenReturn(모든_동아리_정보_1_페이지());

        mockMvc.perform(get("/clubs")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.code").value(200),
                        jsonPath("$.data.totalElements").value(3),
                        jsonPath("$.data.currentPage").value(1)
                )
                .andDo(print());
    }

    @Test
    void 특정_카테고리에_속해_있는_동아리_이름을_출력한다() throws Exception {
        when(clubService.getClubNamesByCategory(anyString())).thenReturn(특정_카테고리에_속한_동아리_이름_생성());

        mockMvc.perform(get("/clubs/평면예술분과"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.data[0].clubName").value("표현의 자유")
                )
                .andDo(print());
    }
}
