package myongari.backend.club.application.port;

public interface ClubImageStorage {

    String getPresignedUrl(String key);
    String putImage(String key, byte[] image);
}
