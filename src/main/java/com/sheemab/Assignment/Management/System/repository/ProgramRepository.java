package com.sheemab.Assignment.Management.System.repository;

import com.sheemab.Assignment.Management.System.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    boolean existsByProgramNameIgnoreCase(String programName);
}

