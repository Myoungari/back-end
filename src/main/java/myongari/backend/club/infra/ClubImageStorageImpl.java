package myongari.backend.club.infra;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.application.port.ClubImageStorage;
import myongari.backend.club.domain.ImageType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClubImageStorageImpl implements ClubImageStorage {

    private final String rootPath;

    public ClubImageStorageImpl(@Value("${image.path}") String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public byte[] downloadImage(String imageName, ImageType imageType) throws IOException {
        Path path = Paths.get(rootPath + '/' + imageName + "." + imageType.name().toLowerCase());

        if (!Files.exists(path)) {
            throw new IOException("이미지를 찾을 수 없습니다.");
        }

        String contentType = Files.probeContentType(path);
        if (contentType == null || !isValidImageType(contentType)) {
            throw new IOException("호환되는 타입이 아닙니다.");
        }

        try (InputStream inputStream = Files.newInputStream(path)) {
            return inputStream.readAllBytes();
        }
    }

    private boolean isValidImageType(String contentType) {
        return "image/png".equals(contentType) || "image/jpeg".equals(contentType) || "image/jpg".equals(contentType);
    }
}
