package myongari.backend.club.fake;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import myongari.backend.club.application.port.ClubImageStorage;
import myongari.backend.club.domain.Image;
import myongari.backend.club.domain.ImageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClubImageFakeStorage implements ClubImageStorage {

    private static final Logger log = LoggerFactory.getLogger(ClubImageFakeStorage.class);

    private static final String rootPath = "src/test/resources";

    @Override
    public Image downloadImage(String imageName, ImageType imageType) {
        Path path = Paths.get(rootPath + '/' + imageName + "." + imageType.getLowerCase());

        try {
            if (!Files.exists(path)) {
                throw new IOException("이미지를 찾을 수 없습니다.");
            }

            String contentType = Files.probeContentType(path);
            if (contentType == null || !isValidImageType(contentType)) {
                throw new IOException("호환되는 타입이 아닙니다.");
            }

            try (InputStream inputStream = Files.newInputStream(path)) {
                return Image.builder()
                        .type(imageType)
                        .image(inputStream.readAllBytes())
                        .build();
            }
        }
        catch (IOException e) {
            log.error("IO 예외 발생 : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private boolean isValidImageType(String contentType) {
        return "image/png".equals(contentType) || "image/jpeg".equals(contentType) || "image/jpg".equals(contentType);
    }
}
