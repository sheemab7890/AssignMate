package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.CourseResponse;
import com.sheemab.Assignment.Management.System.dto.CreateCourseRequest;
import com.sheemab.Assignment.Management.System.entity.Course;
import com.sheemab.Assignment.Management.System.exception.BadRequestException;
import com.sheemab.Assignment.Management.System.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService  {

    private final CourseRepository courseRepository;

    public CourseResponse createCourse(CreateCourseRequest request) {

        if (courseRepository.existsByCode(request.getCode())) {
            throw new BadRequestException("Course code already exists");
        }

        Course course = Course.builder()
                .courseCode(request.getCode())
                .courseName(request.getName())
                .build();

        Course saved = courseRepository.save(course);

        return CourseResponse.builder()
                .id(saved.getId())
                .code(saved.getCourseCode())
                .name(saved.getCourseCode())
                .build();
    } // Admin -> "Creates a new subject"

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(c -> CourseResponse.builder()
                        .id(c.getId())
                        .code(c.getCourseCode())
                        .name(c.getCourseName())
                        .build()
                )
                .toList();
    } // Admin check which subject exist and faculty check available subjects to teach
}
