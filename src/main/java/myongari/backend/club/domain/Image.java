package myongari.backend.club.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Image {

    @Id
    @JsonIgnore
    @Column(name = "image_uuid", columnDefinition = "BINARY(16)")
    private UUID uuid;
    @JsonIgnore
    @Column(name = "image_extension")
    private String extension;
    @Enumerated(EnumType.STRING)
    private ImageType imageType;
    private Long clubId;
}
