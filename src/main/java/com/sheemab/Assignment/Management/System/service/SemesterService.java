package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.SemesterRequestDto;
import com.sheemab.Assignment.Management.System.dto.SemesterResponseDto;
import com.sheemab.Assignment.Management.System.entity.Program;
import com.sheemab.Assignment.Management.System.entity.Semester;
import com.sheemab.Assignment.Management.System.repository.ProgramRepository;
import com.sheemab.Assignment.Management.System.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository semesterRepository;
    private final ProgramRepository programRepository;

    public SemesterResponseDto createSemester(SemesterRequestDto dto) {

        Program program = programRepository.findById(dto.getProgramId())
                .orElseThrow(() -> new RuntimeException("Program not found"));

        if (semesterRepository.existsBySemesterNumberAndProgramId(
                dto.getSemesterNumber(), program.getId())) {
            throw new RuntimeException("Semester already exists for this program");
        }

        Semester semester = new Semester();
        semester.setSemesterNumber(dto.getSemesterNumber());
        semester.setProgram(program);

        Semester saved = semesterRepository.save(semester);

        return new SemesterResponseDto(
                saved.getId(),
                saved.getSemesterNumber(),
                program.getId(),
                program.getProgramName()
        );
    }

    public List<SemesterResponseDto> getSemestersByProgram(Long programId) {
        return semesterRepository.findByProgramId(programId)
                .stream()
                .map(s -> new SemesterResponseDto(
                        s.getId(),
                        s.getSemesterNumber(),
                        s.getProgram().getId(),
                        s.getProgram().getProgramName()
                ))
                .toList();
    }
}


