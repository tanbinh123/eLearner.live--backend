package live.elearners.services;

import live.elearners.config.AuthUtil;
import live.elearners.domain.model.Course;
import live.elearners.domain.model.RegisteredLearner;
import live.elearners.domain.repository.CourseRepository;
import live.elearners.domain.repository.PreRegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AdminService {
    private final AuthUtil authUtil;
    private final CourseRepository courseRepository;
    private final PreRegistrationRepository preRegistrationRepository;

    public ResponseEntity<Void> enrollmentVerify(String courseId, String leanerId) {
        if (authUtil.getRole().equals("ADMIN")) {
            Optional<Course> optionalCourse = courseRepository.findById(courseId);
            if (!optionalCourse.isPresent()) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

            RegisteredLearner registeredLearner = new RegisteredLearner();
            registeredLearner.setPaymentDateAndTime(authUtil.getCurrentDateAndTime());
            registeredLearner.setPaymentVerified(false);


            Course course = optionalCourse.get();
            if (!course.getRegisteredLearners().isEmpty()) {
                for (RegisteredLearner registeredLearner1 : course.getRegisteredLearners()) {
                    if (registeredLearner1.getLearnerId().equals(leanerId)) {
                        registeredLearner1.setPaymentVerified(true);
                        registeredLearner1.setPaymentVerifyDateAndTime(authUtil.getCurrentDateAndTime());
                        courseRepository.save(course);
                        return new ResponseEntity(HttpStatus.OK);
                    }
                }
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
