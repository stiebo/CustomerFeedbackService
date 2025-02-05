package feedbackservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import feedbackservice.domain.Feedback;

import java.util.List;

public record PaginatedResponseDto(
        @JsonProperty("total_documents") Long totalDocuments,
        @JsonProperty("is_first_page") Boolean isFirstPage,
        @JsonProperty("is_last_page") Boolean isLastPage,
        List<Feedback> documents
) {
}
