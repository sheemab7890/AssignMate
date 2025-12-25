package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.SubjectRequestDto;
import com.sheemab.Assignment.Management.System.dto.SubjectResponseDto;
import com.sheemab.Assignment.Management.System.entity.Semester;
import com.sheemab.Assignment.Management.System.entity.Subject;
import com.sheemab.Assignment.Management.System.repository.SemesterRepository;
import com.sheemab.Assignment.Management.System.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SemesterRepository semesterRepository;

    public SubjectResponseDto createSubject(SubjectRequestDto dto) {

        Semester semester = semesterRepository.findById(dto.getSemesterId())
                .orElseThrow(() -> new RuntimeException("Semester not found"));

        if (subjectRepository.existsBySubjectCodeAndSemesterId(
                dto.getSubjectCode(), semester.getId())) {
            throw new RuntimeException("Subject already exists in this semester");
        }

        Subject subject = new Subject();
        subject.setSubjectCode(dto.getSubjectCode());
        subject.setSubjectName(dto.getSubjectName());
        subject.setType(dto.getType());
        subject.setSemester(semester);

        Subject saved = subjectRepository.save(subject);

        return new SubjectResponseDto(
                saved.getId(),
                saved.getSubjectCode(),
                saved.getSubjectName(),
                saved.getType()
        );
    }

    public List<SubjectResponseDto> getSubjectsBySemester(Long semesterId) {

        // optional safety check (good practice)
        if (!semesterRepository.existsById(semesterId)) {
            throw new RuntimeException("Semester not found");
        }

        return subjectRepository.findBySemesterId(semesterId)
                .stream()
                .map(subject -> new SubjectResponseDto(
                        subject.getId(),
                        subject.getSubjectCode(),
                        subject.getSubjectName(),
                        subject.getType()
                ))
                .toList();
    }

}

