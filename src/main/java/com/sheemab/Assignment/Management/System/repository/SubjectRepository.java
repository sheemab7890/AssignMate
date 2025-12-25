package com.sheemab.Assignment.Management.System.repository;

import com.sheemab.Assignment.Management.System.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    boolean existsBySubjectCodeAndSemesterId(
            String subjectCode,
            Long semesterId
    );

    List<Subject> findBySemesterId(Long semesterId);
}

