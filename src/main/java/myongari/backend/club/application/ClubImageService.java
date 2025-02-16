package myongari.backend.club.application;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.application.port.ClubImageStorage;
import myongari.backend.club.application.port.ClubRepository;
import myongari.backend.club.domain.Image;
import myongari.backend.club.domain.ImageType;
import myongari.backend.club.dto.ClubDetail;
import myongari.backend.club.dto.ClubSummary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClubImageService {

    private final ClubImageStorage clubImageStorage;
    private final ClubRepository clubRepository;

    public ClubDetail setThumbnailPresignedUrl(
            final ClubDetail clubDetail
    ) {
        List<Image> images = clubRepository.findImage(clubDetail.getId(), ImageType.THUMBNAIL);
        if (!images.isEmpty()) {
            String presignedUrl = getPresignedUrl(images.get(0));
            clubDetail.setThumbnailUrl(presignedUrl);
        }
        return clubDetail;
    }

    public ClubSummary setThumbnailPresignedUrl(
            final ClubSummary clubSummary
    ) {
        List<Image> images = clubRepository.findImage(clubSummary.getId(), ImageType.THUMBNAIL);
        if (!images.isEmpty()) {
            String presignedUrl = getPresignedUrl(images.get(0));
            clubSummary.setThumbnailUrl(presignedUrl);
        }
        return clubSummary;
    }

    public ClubDetail setImagePresignedUrls(
            final ClubDetail clubDetail
    ) {
        List<Image> images = clubRepository.findImage(clubDetail.getId(), ImageType.NORMAL);
        List<String> presignedUrls = images.stream()
                .map(this::getPresignedUrl)
                .toList();
        clubDetail.setClubImageUrls(presignedUrls);
        return clubDetail;
    }

    private String getPresignedUrl(final Image image) {
        if (image == null) {
            return null;
        }
        UUID uuid = image.getUuid();
        String extension = image.getExtension();
        String key = "image/" + uuid + extension; // extension에 '.' 포함

        return clubImageStorage.getPresignedUrl(key);
    }

    public void saveImage(
            final Long clubId,
            final ImageType imageType,
            final MultipartFile image
    ) {
        if (image == null) {
            return;
        }
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

        Image savedImage = Image.builder()
                .uuid(uuid)
                .extension(extension)
                .imageType(imageType)
                .clubId(clubId)
                .build();
        clubRepository.saveImage(savedImage);
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
