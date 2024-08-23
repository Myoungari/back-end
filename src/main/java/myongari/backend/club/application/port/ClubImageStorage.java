package myongari.backend.club.application.port;

import java.io.IOException;
import myongari.backend.club.domain.ImageType;

public interface ClubImageStorage {

    byte[] downloadImage(String imageName, ImageType imageType) throws IOException;
}
