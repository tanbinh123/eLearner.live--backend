package live.elearners.controller;

import live.elearners.domain.model.Course;
import live.elearners.dto.request.CourseRequest;
import live.elearners.dto.request.CourseUpdateRequest;
import live.elearners.dto.response.CourseIdentityResponse;
import live.elearners.dto.response.CourseResponse;
import live.elearners.services.AuthService;
import live.elearners.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@AllArgsConstructor
@RequestMapping("courses")
public class CoursesController {
    private final AuthService authService;
    private final CourseService courseService;

    @PostMapping("")
    public ResponseEntity<CourseIdentityResponse> createCourse(HttpServletRequest httpServletRequest,
                                                               @RequestBody CourseRequest courseRequest) {
        authService.pink(httpServletRequest);

        return courseService.createCourse(courseRequest);
    }

    @GetMapping("")
    public ResponseEntity<CourseResponse> getCourses(@PageableDefault(size = 5) Pageable pageable) {

        return courseService.getCourse(pageable);
    }
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourses(@PathVariable String courseId) {

        return courseService.getCourseById(courseId);
    }

    @PutMapping("/{courseId}")
    public void updateCourseById(HttpServletRequest httpServletRequest,@PathVariable String courseId,@RequestBody CourseUpdateRequest courseUpdateRequest) {

         authService.pink(httpServletRequest);
         courseService.updateCourseById(courseId,courseUpdateRequest);
    }
}