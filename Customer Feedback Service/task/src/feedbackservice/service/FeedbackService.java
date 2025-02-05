package feedbackservice.service;

import feedbackservice.domain.Feedback;
import feedbackservice.dto.PaginatedResponseDto;
import feedbackservice.exception.FeedbackNotFoundException;
import feedbackservice.repository.FeedbackRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback getFeedback(String id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found with id: " + id));
    }

    public PaginatedResponseDto getFilteredFeedbacks(Integer rating, String customer,
                                               String product, String vendor,
                                               int page, int perPage) {

        Feedback probe = new Feedback(null, rating, null, customer, product, vendor);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        Example<Feedback> example = Example.of(probe, matcher);

        Pageable pageable = PageRequest.of(Math.max((page - 1), 0), (perPage < 5 || perPage > 20) ? 10 : perPage,
                Sort.by("id").descending());

        Page<Feedback> listPageFeedback = feedbackRepository.findAll(example, pageable);
        return new PaginatedResponseDto(
                listPageFeedback.getTotalElements(),
                listPageFeedback.isFirst(),
                listPageFeedback.isLast(),
                listPageFeedback.getContent());
    }
}
