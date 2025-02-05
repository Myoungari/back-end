package myongari.backend.club.application;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import myongari.backend.club.application.port.ClubImageStorage;
import myongari.backend.club.domain.Image;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubImageService {

    private final ClubImageStorage clubImageStorage;

    public Image getImage(UUID uuid) {
        String key = "image/" + uuid + ".png";

        String presignedUrl = clubImageStorage.getPresignedUrl(key);

        return Image.builder()
                .uuid(uuid)
                .imageLink(presignedUrl)
                .build();
    }
}
