package com.sheemab.Assignment.Management.System.repository;

import com.sheemab.Assignment.Management.System.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    boolean existsBySectionNameAndSemesterId(
            String sectionName,
            Long semesterId
    );

    List<Section> findBySemesterId(Long semesterId);
}

