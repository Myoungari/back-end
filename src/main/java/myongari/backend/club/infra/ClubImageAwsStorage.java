package myongari.backend.club.infra;

import io.awspring.cloud.s3.S3Operations;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.application.port.ClubImageStorage;
import myongari.backend.club.domain.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClubImageAwsStorage implements ClubImageStorage {

    @Value("${spring.cloud.aws.s3.bucket:null}")
    private String bucketName;

    private final S3Operations s3Operations;

    @Override
    public Image downloadImage(UUID uuid) {
        String key = "image/" + uuid + ".png";

        URL signedGetURL = s3Operations.createSignedGetURL(bucketName, key, Duration.ofMinutes(5));
        log.info("Presigned URL: [{}]", signedGetURL);

        return Image.builder()
                .uuid(uuid)
                .imageLink(signedGetURL.toString())
                .build();
    }
}

