package myongari.backend.club.infra;

import io.awspring.cloud.s3.S3Operations;
import java.net.URL;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.application.port.ClubImageStorage;
import myongari.backend.club.domain.Image;
import myongari.backend.club.domain.ImageType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Slf4j
@RequiredArgsConstructor
public class ClubImageAwsStorage implements ClubImageStorage {

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    private final S3Operations s3Operations;

    @Override
    public Image downloadImage(String imageName, ImageType imageType) {
        URL signedGetURL = s3Operations.createSignedGetURL(bucketName,
                "image/" + imageName + "." + imageType.getLowerCase(), Duration.ofMinutes(5));
        log.info("Presigned URL: [{}]", signedGetURL);

        return Image.builder()
                .imageLink(signedGetURL.toString())
                .type(imageType)
                .build();
    }
}

