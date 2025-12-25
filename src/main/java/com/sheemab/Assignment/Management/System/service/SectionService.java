package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.SectionRequestDto;
import com.sheemab.Assignment.Management.System.dto.SectionResponseDto;
import com.sheemab.Assignment.Management.System.entity.Section;
import com.sheemab.Assignment.Management.System.entity.Semester;
import com.sheemab.Assignment.Management.System.entity.User;
import com.sheemab.Assignment.Management.System.enums.RoleName;
import com.sheemab.Assignment.Management.System.repository.SectionRepository;
import com.sheemab.Assignment.Management.System.repository.SemesterRepository;
import com.sheemab.Assignment.Management.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final SemesterRepository semesterRepository;
    private final UserRepository userRepository;

    public SectionResponseDto createSection(SectionRequestDto dto) {

        Semester semester = semesterRepository.findById(dto.getSemesterId())
                .orElseThrow(() -> new RuntimeException("Semester not found"));

        if (sectionRepository.existsBySectionNameAndSemesterId(
                dto.getSectionName(), semester.getId())) {
            throw new RuntimeException("Section already exists in this semester");
        }

        User mentor = userRepository.findById(dto.getMentorId())
                .orElseThrow(() -> new RuntimeException("Mentor not found"));

        if (!mentor.hasRole(RoleName.FACULTY)) {
            throw new RuntimeException("Mentor must be faculty");
        }

        Section section = new Section();
        section.setSectionName(dto.getSectionName());
        section.setSemester(semester);
        section.setMentor(mentor);

        Section saved = sectionRepository.save(section);

        return new SectionResponseDto(
                saved.getId(),
                saved.getSectionName(),
                mentor.getFullName()
        );
    }

    public List<SectionResponseDto> getSectionsBySemester(Long semesterId) {

        if (!semesterRepository.existsById(semesterId)) {
            throw new RuntimeException("Semester not found");
        }

        return sectionRepository.findBySemesterId(semesterId)
                .stream()
                .map(section -> new SectionResponseDto(
                        section.getId(),
                        section.getSectionName(),
                        section.getMentor().getFullName()
                ))
                .toList();
    }


}

