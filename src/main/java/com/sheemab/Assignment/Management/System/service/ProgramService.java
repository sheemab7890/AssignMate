package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.ProgramRequestDto;
import com.sheemab.Assignment.Management.System.dto.ProgramResponseDto;
import com.sheemab.Assignment.Management.System.entity.Program;
import com.sheemab.Assignment.Management.System.entity.User;
import com.sheemab.Assignment.Management.System.enums.RoleName;
import com.sheemab.Assignment.Management.System.repository.ProgramRepository;
import com.sheemab.Assignment.Management.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;
    private final UserRepository userRepository;

    public ProgramResponseDto createProgram(ProgramRequestDto dto) {

        // 🔒 Duplicate Program validation
        if (programRepository.existsByProgramNameIgnoreCase(dto.getProgramName())) {
            throw new RuntimeException("Program with same name already exists");
        }

        // 👤 Coordinator validation
        User coordinator = userRepository.findById(dto.getCoordinatorId())
                .orElseThrow(() -> new RuntimeException("Coordinator not found"));

        if (!coordinator.hasRole(RoleName.FACULTY)) {
            throw new RuntimeException("Coordinator must be a faculty");
        }

        Program program = new Program();
        program.setProgramName(dto.getProgramName());
        program.setCoordinator(coordinator);

        Program saved = programRepository.save(program);

        return new ProgramResponseDto(
                saved.getId(),
                saved.getProgramName(),
                coordinator.getFullName()
        );
    }

    public List<ProgramResponseDto> getAllPrograms() {
        return programRepository.findAll()
                .stream()
                .map(p -> new ProgramResponseDto(
                        p.getId(),
                        p.getProgramName(),
                        p.getCoordinator().getFullName()
                ))
                .toList();
    }
}


