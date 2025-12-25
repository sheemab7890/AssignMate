package com.sheemab.Assignment.Management.System.repository;

import com.sheemab.Assignment.Management.System.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {

    boolean existsBySemesterNumberAndProgramId(
            Integer semesterNumber,
            Long programId
    );

    List<Semester> findByProgramId(Long programId);
}

