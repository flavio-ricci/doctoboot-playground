package com.doctolib.doctobootplayground.services;

import com.doctolib.doctobootplayground.dto.PracticeDTO;
import com.doctolib.doctobootplayground.entities.PracticeEntity;

import java.util.List;
import java.util.stream.Collectors;

import com.doctolib.doctobootplayground.repositories.PracticesRepository;
import org.springframework.stereotype.Service;

@Service
public class PracticesService {
    private final PracticesRepository practicesRepository;

    public PracticesService(PracticesRepository practicesRepository) {
        this.practicesRepository = practicesRepository;
    }

    public List<PracticeDTO> getPracticesByOrganizationId(Long organizationId) {
        return practicesRepository.findByOrganizationId(organizationId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PracticeDTO convertToDTO(PracticeEntity practiceEntity) {
        PracticeDTO practiceDTO = new PracticeDTO();
        practiceDTO.setId(practiceEntity.getId());
        practiceDTO.setOrganizationId(practiceEntity.getOrganizationId());
        practiceDTO.setAddress(practiceEntity.getAddress());
        practiceDTO.setCity(practiceEntity.getCity());
        practiceDTO.setZipcode(practiceEntity.getZipcode());
        practiceDTO.setLongitude(practiceEntity.getLongitude());
        practiceDTO.setLatitude(practiceEntity.getLatitude());
        return practiceDTO;
    }
}
