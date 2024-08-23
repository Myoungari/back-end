package myongari.backend.club.infra;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import myongari.backend.club.domain.ImageType;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class ClubImageStorageTest {

    private static final String imagePath = "src/test/resources";

    private final ClubImageStorage clubImageStorage;

    public ClubImageStorageTest() {
        this.clubImageStorage = new ClubImageStorageImpl(imagePath);
    }

    @Test
    void 이미지_데이터를_가져온다() throws IOException {
        // given
        String imageName = "test_logo";

        // when
        byte[] imageData = clubImageStorage.downloadImage(imageName, ImageType.PNG);

        // then
        assertThat(imageData).isNotNull();
    }

}
