package com.sheemab.Assignment.Management.System.repository;


import com.sheemab.Assignment.Management.System.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {

    boolean existsByStudent_IdAndCourseOffering_Id(Long studentId, Long offeringId);

    List<StudentEnrollment> findByStudent_Id(Long studentId);

}
