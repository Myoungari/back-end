package myongari.backend.club.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClubSimplePage {

    private long totalPages;
    private long totalElements;
    private long currentPage;
    private List<ClubSimple> content;

    public static ClubSimplePage from(Page<ClubSimple> clubSimpleAll) {
        return new ClubSimplePage(clubSimpleAll.getTotalPages(),
                clubSimpleAll.getTotalElements(),
                clubSimpleAll.getNumber(),
                clubSimpleAll.getContent());
    }

}
