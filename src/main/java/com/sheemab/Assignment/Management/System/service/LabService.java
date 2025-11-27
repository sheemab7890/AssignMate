package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.CreateLabRequest;
import com.sheemab.Assignment.Management.System.dto.LabResponse;
import com.sheemab.Assignment.Management.System.entity.Lab;
import com.sheemab.Assignment.Management.System.entity.User;
import com.sheemab.Assignment.Management.System.exception.ResourceNotFoundException;
import com.sheemab.Assignment.Management.System.repository.LabRepository;
import com.sheemab.Assignment.Management.System.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LabService {

    private final LabRepository labRepository;
    private final UserRepository userRepository;


    public LabResponse createLab(CreateLabRequest request) {

        User labIncharge = userRepository.findById(request.getLabInchargeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lab incharge not found")
                );

        Lab lab = Lab.builder()
                .labNumber(request.getLabNumber())
                .labName(request.getLabName())
                .labIncharge(labIncharge)
                .build();

        Lab saved = labRepository.save(lab);

        return toDto(saved);
    } // Admin -> "Create a new lab and assign a lab incharge."


    public List<LabResponse> getAllLabs() {
        return labRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    } // Admin/Faculty/Lab-Incharge -> "Show me all labs in the college."

    private LabResponse toDto(Lab lab) {
        return LabResponse.builder()
                .id(lab.getId())
                .labNumber(lab.getLabNumber())
                .labName(lab.getLabName())
                .labInchargeId(lab.getLabIncharge().getId())
                .labInchargeName(lab.getLabIncharge().getFullName())
                .build();
    }

}
