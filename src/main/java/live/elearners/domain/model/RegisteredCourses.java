package live.elearners.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import live.elearners.config.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "registeredCourses")
public class RegisteredCourses extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    private String courseId;
    private String paymentDateAndTime;
    private String paymentVerifyDateAndTime;
    private boolean isPaymentVerified;
    private String paymentMethod;
    private String paymentTrxId;
    private String paid;
    private String due;
    private String commitmentDuePaidDate;


}
