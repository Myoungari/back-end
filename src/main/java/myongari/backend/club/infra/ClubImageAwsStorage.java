package myongari.backend.club.infra;

import io.awspring.cloud.s3.S3Operations;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myongari.backend.club.application.port.ClubImageStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClubImageAwsStorage implements ClubImageStorage {

    private final S3Operations s3Operations;

    @Value("${spring.cloud.aws.s3.bucket:null}")
    private String bucketName;

    @Override
    public String getPresignedUrl(String key) {
        return s3Operations.createSignedGetURL(bucketName, key, Duration.ofMinutes(5)).toString();
    }
}

