package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.LabRequestDto;
import com.sheemab.Assignment.Management.System.dto.LabResponseDto;
import com.sheemab.Assignment.Management.System.entity.Lab;
import com.sheemab.Assignment.Management.System.entity.User;
import com.sheemab.Assignment.Management.System.enums.RoleName;
import com.sheemab.Assignment.Management.System.repository.LabRepository;
import com.sheemab.Assignment.Management.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LabService {

    private final LabRepository labRepository;
    private final UserRepository userRepository;

    public LabResponseDto createLab(LabRequestDto dto) {

        if (labRepository.existsByLabName(dto.getLabName())) {
            throw new RuntimeException("Lab already exists");
        }

        User incharge = userRepository.findById(dto.getLabInchargeId())
                .orElseThrow(() -> new RuntimeException("Lab incharge not found"));

        if (!incharge.hasRole(RoleName.FACULTY)) {
            throw new RuntimeException("Lab incharge must be faculty");
        }

        Lab lab = new Lab();
        lab.setLabName(dto.getLabName());
        lab.setLabIncharge(incharge);

        Lab saved = labRepository.save(lab);

        return new LabResponseDto(
                saved.getId(),
                saved.getLabName(),
                incharge.getFullName()
        );
    }

//    public List<LabResponseDto> getAllLabs() {
//
//        return labRepository.findAll()
//                .stream()
//                .map(lab -> new LabResponseDto(
//                        lab.getId(),
//                        lab.getLabName(),
//                        lab.getLabIncharge().getFullName()
//                ))
//                .toList();
//    }
}

