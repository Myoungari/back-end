package myongari.backend.club.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubRegisterRequest;
import myongari.backend.club.dto.ClubSummary;
import myongari.backend.common.response.Success;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "동아리 조회 API")
public interface ClubSwagger {

    @Operation(summary = "동아리 페이지 조회 API", description = "")
    ResponseEntity<Success<List<ClubSummary>>> getClubSummaryAll();

    @Operation(
            summary = "카테고리 이름 별 동아리 이름 및 동아리 세부 정보 조회 API",
            description = "pathVariable로 카테고리 이름을 받아 해당 카테고리에 속한 동아리 이름 및 동아리 세부 정보를 조회합니다."
    )
    ResponseEntity<Success<ClubNamesAndDetail>> getClubNamesByCategoryName(
            String categoryName
    );

    @Operation(
            summary = "카테고리 이름 별 동아리 이름 및 동아리 세부 정보 조회 API",
            description = "pathVariable로 카테고리 이름을 받아 해당 카테고리에 속한 동아리 이름 및 동아리 세부 정보를 조회합니다.\n"
                    + "pathVariable로 clubId를 받아 해당 동아리의 세부 정보를 조회합니다."
    )
    ResponseEntity<Success<ClubNamesAndDetail>> getClubNamesByCategoryNameAndClubId(
            String categoryName,
            Long clubId
    );

    @Operation(summary = "동아리 정보 저장 API", description = "")
    @RequestBody(
            content = @Content(
                encoding = @Encoding(
                        name = "clubRegisterRequest", contentType = MediaType.APPLICATION_JSON_VALUE)))
    ResponseEntity<Void> saveClub(
            ClubRegisterRequest clubRegisterRequest,
            MultipartFile image
    );
}
