package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.datatransferobject.PlanRequestDTO;
import com.sample.datatransferobject.PlanResponseDTO;
import com.sample.exception.EntityNotFoundException;
import com.sample.service.PlanGeneratorService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("plan/")
public class PlanController
{

    private final PlanGeneratorService planService;


    @Autowired
    public PlanController(final PlanGeneratorService planService)
    {
        this.planService = planService;
    }


    @PostMapping("/generator")
    public List<PlanResponseDTO> geeneratePlan(@RequestBody PlanRequestDTO request) throws EntityNotFoundException
    {
        return planService.generatePlan(request);
    }

}
