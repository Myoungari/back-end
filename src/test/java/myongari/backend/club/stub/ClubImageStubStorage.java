package myongari.backend.club.stub;

import java.util.UUID;
import myongari.backend.club.application.port.ClubImageStorage;
import myongari.backend.club.domain.Image;

public class ClubImageStubStorage implements ClubImageStorage {

    private static final String rootPath = "src/test/resources/";

    @Override
    public Image downloadImage(UUID uuid) {
        String key = uuid + ".png";
        return Image.builder()
                .uuid(uuid)
                .imageLink(rootPath + key)
                .build();
    }
}
