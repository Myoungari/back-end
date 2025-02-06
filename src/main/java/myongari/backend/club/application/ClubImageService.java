package myongari.backend.club.application;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.application.port.ClubImageStorage;
import myongari.backend.club.domain.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClubImageService {

    private final ClubImageStorage clubImageStorage;

    public Image getImage(Image image) {
        UUID uuid = image.getUuid();
        String extension = image.getExtension();
        String key = "image/" + uuid + extension; // extension에 '.' 포함

        String presignedUrl = clubImageStorage.getPresignedUrl(key);

        return Image.builder()
                .uuid(uuid)
                .imageLink(presignedUrl)
                .build();
    }

    public Image saveImage(final MultipartFile image) {
        String extension = parseExtension(image);

        UUID uuid = UUID.randomUUID();
        String key = "image/" + uuid + extension;

        byte[] imageBytes;
        try {
            imageBytes = image.getBytes();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get image bytes", e);
        }

        clubImageStorage.putImage(key, imageBytes);

        return Image.builder()
                .uuid(uuid)
                .extension(extension)
                .imageLink(key)
                .build();
    }

    private String parseExtension(MultipartFile image) {
        String contentType = image.getContentType();
        if (contentType == null) {
            throw new IllegalArgumentException("Content type is required");
        }
        return switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            case "image/gif" -> ".gif";
            default -> throw new IllegalArgumentException("Unsupported content type: " + contentType);
        };
    }
}
