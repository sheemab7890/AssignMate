package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.BatchRequestDto;
import com.sheemab.Assignment.Management.System.dto.BatchResponseDto;
import com.sheemab.Assignment.Management.System.entity.Batch;
import com.sheemab.Assignment.Management.System.entity.Lab;
import com.sheemab.Assignment.Management.System.entity.Section;
import com.sheemab.Assignment.Management.System.repository.BatchRepository;
import com.sheemab.Assignment.Management.System.repository.LabRepository;
import com.sheemab.Assignment.Management.System.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final BatchRepository batchRepository;
    private final SectionRepository sectionRepository;
    private final LabRepository labRepository;

    public BatchResponseDto createBatch(BatchRequestDto dto) {

        Section section = sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found"));

        if (batchRepository.existsByBatchNameAndSectionId(
                dto.getBatchName(), section.getId())) {
            throw new RuntimeException("Batch already exists in this section");
        }

        Lab lab = labRepository.findById(dto.getLabId())
                .orElseThrow(() -> new RuntimeException("Lab not found"));

        Batch batch = new Batch();
        batch.setBatchName(dto.getBatchName());
        batch.setSection(section);
        batch.setLab(lab);

        Batch saved = batchRepository.save(batch);

        return new BatchResponseDto(
                saved.getId(),
                saved.getBatchName(),
                section.getSectionName(),
                lab.getLabName()
        );
    }

    public List<BatchResponseDto> getBatchesBySection(Long sectionId) {

        if (!sectionRepository.existsById(sectionId)) {
            throw new RuntimeException("Section not found");
        }

        return batchRepository.findBySectionId(sectionId)
                .stream()
                .map(batch -> new BatchResponseDto(
                        batch.getId(),
                        batch.getBatchName(),
                        batch.getSection().getSectionName(),
                        batch.getLab().getLabName()
                ))
                .toList();
    }
}

