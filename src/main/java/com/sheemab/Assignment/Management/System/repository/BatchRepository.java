package com.sheemab.Assignment.Management.System.repository;

import com.sheemab.Assignment.Management.System.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    boolean existsByBatchNameAndSectionId(
            String batchName,
            Long sectionId
    );

    List<Batch> findBySectionId(Long sectionId);

}

