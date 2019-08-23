package com.sample.service;

import java.util.List;

import com.sample.datatransferobject.PlanRequestDTO;
import com.sample.datatransferobject.PlanResponseDTO;

public interface PlanGeneratorService
{

    List<PlanResponseDTO> generatePlan(PlanRequestDTO request);

}
