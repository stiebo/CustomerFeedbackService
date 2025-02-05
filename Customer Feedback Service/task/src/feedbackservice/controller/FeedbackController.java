package feedbackservice.controller;

import feedbackservice.domain.Feedback;
import feedbackservice.dto.PaginatedResponseDto;
import feedbackservice.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<Void> createFeedback(@Valid @RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackService.saveFeedback(feedback);

        // Construct the Location URI (e.g., /feedback/65af3c4b123456789)
        URI location = URI.create(String.format("/feedback/%s", savedFeedback.getId()));

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public Feedback getFeedback(@PathVariable String id) {
        return feedbackService.getFeedback(id);
    }

    @GetMapping
    public PaginatedResponseDto getAllFeedbacks(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int perPage,
                                                @RequestParam(required = false) Integer rating,
                                                @RequestParam(required = false) String customer,
                                                @RequestParam(required = false) String product,
                                                @RequestParam(required = false) String vendor) {
        return feedbackService.getFilteredFeedbacks(rating, customer, product, vendor, page, perPage);
    }
}
