package com.doctolib.doctobootplayground.controller;

import com.doctolib.doctobootplayground.dto.PracticeDTO;
import com.doctolib.doctobootplayground.services.PracticesService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/practices")
@RestController
public class PracticesController {
    private final PracticesService practicesService;

    public PracticesController(PracticesService practicesService) {
        this.practicesService = practicesService;
    }

    @GetMapping("/{organizationId}")
    public List<PracticeDTO> show(@PathVariable Long organizationId) {
        return practicesService.getPracticesByOrganizationId(organizationId);
    }
}
