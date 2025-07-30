package com.codebase.microservices.AgentServiceApplication.controller;

import com.codebase.microservices.AgentServiceApplication.client.CitizenClient;
import com.codebase.microservices.AgentServiceApplication.client.VaccinationClient;
import com.codebase.microservices.AgentServiceApplication.dto.Citizen;
import com.codebase.microservices.AgentServiceApplication.dto.RequiredResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private CitizenClient citizenClient;

    @Autowired
    private VaccinationClient vaccinationClient;

    @GetMapping("/full-info/{centerId}")
    public ResponseEntity<RequiredResponse> getAllInfo(@PathVariable Integer centerId) {
        RequiredResponse response = vaccinationClient.getCenterDetails(centerId);
        List<Citizen> citizens = citizenClient.getCitizensByCenterId(centerId);
        response.setCitizens(citizens);
        return ResponseEntity.ok(response);
    }
}
