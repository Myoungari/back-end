package myongari.backend.club.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import myongari.backend.club.dto.ClubNamesAndDetail;
import myongari.backend.club.dto.ClubSummary;
import myongari.backend.common.response.Success;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "동아리 조회 API")
public interface ClubSwagger {

    @Operation(summary = "동아리 페이지 조회 API", description = "")
    ResponseEntity<Success<List<ClubSummary>>> getClubSummaryAll();

    @Operation(
            summary = "카테고리 이름 별 동아리 이름 및 동아리 세부 정보 조회 API",
            description = "pathVariable로 카테고리 이름을 받아 해당 카테고리에 속한 동아리 이름 및 동아리 세부 정보를 조회합니다."
    )
    ResponseEntity<Success<ClubNamesAndDetail>> getClubNamesByCategoryName(
            @PathVariable(name = "category_name") String categoryName
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
}
