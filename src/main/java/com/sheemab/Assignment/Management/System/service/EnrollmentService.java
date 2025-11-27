package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.EnrollStudentRequest;
import com.sheemab.Assignment.Management.System.dto.StudentEnrollmentResponse;
import com.sheemab.Assignment.Management.System.entity.CourseOffering;
import com.sheemab.Assignment.Management.System.entity.StudentEnrollment;
import com.sheemab.Assignment.Management.System.entity.User;
import com.sheemab.Assignment.Management.System.enums.RoleName;
import com.sheemab.Assignment.Management.System.exception.BadRequestException;
import com.sheemab.Assignment.Management.System.exception.ResourceNotFoundException;
import com.sheemab.Assignment.Management.System.repository.CourseOfferingRepository;
import com.sheemab.Assignment.Management.System.repository.StudentEnrollmentRepository;
import com.sheemab.Assignment.Management.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final UserRepository userRepository;
    private final CourseOfferingRepository offeringRepository;
    private final StudentEnrollmentRepository enrollmentRepository;

    // ADMIN ENROLL STUDENT
    public void adminEnrollStudent(EnrollStudentRequest request) {

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        boolean isStudent = student.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals(RoleName.STUDENT));

        if (!isStudent) {
            throw new BadRequestException("User is not a student");
        }

        CourseOffering offering = offeringRepository.findById(request.getCourseOfferingId())
                .orElseThrow(() -> new ResourceNotFoundException("Course offering not found"));

        if (enrollmentRepository.existsByStudent_IdAndCourseOffering_Id(
                student.getId(), offering.getId())) {
            throw new BadRequestException("Student already enrolled in this course");
        }

        StudentEnrollment enrollment = StudentEnrollment.builder()
                .student(student)
                .courseOffering(offering)
                .enrolledAt(java.time.Instant.now())
                .build();

        enrollmentRepository.save(enrollment);
    }

    // STUDENT VIEW OWN ENROLLMENTS
    public List<StudentEnrollmentResponse> getMyEnrollments() {

        Long studentId = getCurrentUserId();

        return enrollmentRepository.findByStudent_Id(studentId)
                .stream()
                .map(e -> StudentEnrollmentResponse.builder()
                        .courseCode(e.getCourseOffering().getCourse().getCourseCode())
                        .courseName(e.getCourseOffering().getCourse().getCourseName())
                        .facultyName(e.getCourseOffering().getFaculty().getFullName())
                        .labNumber(e.getCourseOffering().getLab().getLabNumber())
                        .labName(e.getCourseOffering().getLab().getLabName())
                        .semester(e.getCourseOffering().getSemester())
                        .academicYear(e.getCourseOffering().getAcademicYear())
                        .build()
                ).toList();
    }

    private Long getCurrentUserId() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    } // Get the currently logged-in user's email
}
