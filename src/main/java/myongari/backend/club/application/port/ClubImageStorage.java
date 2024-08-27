package myongari.backend.club.application.port;

import java.util.UUID;
import myongari.backend.club.domain.Image;

public interface ClubImageStorage {

    Image downloadImage(UUID uuid);
}
