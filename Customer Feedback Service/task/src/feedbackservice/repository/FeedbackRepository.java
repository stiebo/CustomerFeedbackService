package feedbackservice.repository;

import feedbackservice.domain.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    Page<Feedback> findAllByOrderByIdDesc(Pageable pageable);
}
