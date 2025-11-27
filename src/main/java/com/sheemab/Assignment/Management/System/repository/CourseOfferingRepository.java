package com.sheemab.Assignment.Management.System.repository;

import com.sheemab.Assignment.Management.System.entity.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {

    List<CourseOffering> findByFaculty_Id(Long facultyId);

    List<CourseOffering> findByLab_LabIncharge_Id(Long labInchargeId);
}
