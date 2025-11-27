package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.CourseOfferingResponse;
import com.sheemab.Assignment.Management.System.dto.CreateCourseOfferingRequest;
import com.sheemab.Assignment.Management.System.entity.Course;
import com.sheemab.Assignment.Management.System.entity.CourseOffering;
import com.sheemab.Assignment.Management.System.entity.Lab;
import com.sheemab.Assignment.Management.System.entity.User;
import com.sheemab.Assignment.Management.System.exception.ResourceNotFoundException;
import com.sheemab.Assignment.Management.System.repository.CourseOfferingRepository;
import com.sheemab.Assignment.Management.System.repository.CourseRepository;
import com.sheemab.Assignment.Management.System.repository.LabRepository;
import com.sheemab.Assignment.Management.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseOfferingService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final LabRepository labRepository;
    private final CourseOfferingRepository courseOfferingRepository;


    public CourseOfferingResponse createOffering(CreateCourseOfferingRequest request) {

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        User faculty = userRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));

        Lab lab = labRepository.findById(request.getLabId())
                .orElseThrow(() -> new ResourceNotFoundException("Lab not found"));

        CourseOffering offering = CourseOffering.builder()
                .course(course)
                .faculty(faculty)
                .lab(lab)
                .semester(request.getSemester())
                .academicYear(request.getAcademicYear())
                .build();

        CourseOffering saved = courseOfferingRepository.save(offering);

        return toDto(saved);
    } // Admin -> Assign a teacher to teach a course in a lab."


    public List<CourseOfferingResponse> getOfferingsForFaculty() {
        Long facultyId = getCurrentUserId();
        return courseOfferingRepository.findByFaculty_Id(facultyId)
                .stream()
                .map(this::toDto)
                .toList();
    } // Faculty -> "Tell me which classes I am teaching."


    public List<CourseOfferingResponse> getOfferingsForLabIncharge() {
        Long labInchargeId = getCurrentUserId();
        return courseOfferingRepository.findByLab_LabIncharge_Id(labInchargeId)
                .stream()
                .map(this::toDto)
                .toList();
    } // Lab Incharge -> Classes running in their lab.

    private Long getCurrentUserId() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    }

    private CourseOfferingResponse toDto(CourseOffering off) {

        return CourseOfferingResponse.builder()
                .id(off.getId())
                .courseCode(off.getCourse().getCourseCode())
                .courseName(off.getCourse().getCourseName())
                .facultyId(off.getFaculty().getId())
                .facultyName(off.getFaculty().getFullName())
                .labNumber(off.getLab().getLabNumber())
                .labName(off.getLab().getLabName())
                .labInchargeId(off.getLab().getLabIncharge().getId())
                .labInchargeName(off.getLab().getLabIncharge().getFullName())
                .semester(off.getSemester())
                .academicYear(off.getAcademicYear())
                .build();
    }
}
