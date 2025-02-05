package feedbackservice.repository;

import feedbackservice.domain.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FeedbackRepository extends MongoRepository<Feedback, String> {
}
