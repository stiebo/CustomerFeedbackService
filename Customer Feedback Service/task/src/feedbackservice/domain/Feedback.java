package feedbackservice.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedback")
public class Feedback {
    @Id
    private String id;
    @Min(1) @Max(5)
    private Integer rating;
    private String feedback;
    private String customer;
    @NotBlank
    private String product;
    @NotBlank
    private String vendor;

    public Feedback(String id, Integer rating, String feedback, String customer, String product, String vendor) {
        this.id = id;
        this.rating = rating;
        this.feedback = feedback;
        this.customer = customer;
        this.product = product;
        this.vendor = vendor;
    }

    public String getId() {
        return id;
    }

    public Feedback setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public Feedback setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public String getFeedback() {
        return feedback;
    }

    public Feedback setFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }

    public String getCustomer() {
        return customer;
    }

    public Feedback setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public String getProduct() {
        return product;
    }

    public Feedback setProduct(String product) {
        this.product = product;
        return this;
    }

    public String getVendor() {
        return vendor;
    }

    public Feedback setVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }
}
