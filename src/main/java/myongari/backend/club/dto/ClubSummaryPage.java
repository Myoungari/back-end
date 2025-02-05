package myongari.backend.club.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClubSummaryPage {

    private long totalPages;
    private long totalElements;
    private long currentPage;
    private List<ClubSummary> content;

    public static ClubSummaryPage from(Page<ClubSummary> clubSimpleAll) {
        return new ClubSummaryPage(clubSimpleAll.getTotalPages(),
                clubSimpleAll.getTotalElements(),
                clubSimpleAll.getNumber(),
                clubSimpleAll.getContent());
    }

}
