package com.codebase.microservices.AgentService.controller;

import com.codebase.microservices.AgentService.client.CitizenServiceClient;
import com.codebase.microservices.AgentService.client.VaccinationCenterServiceClient;
import com.codebase.microservices.AgentService.entity.Agent;
import com.codebase.microservices.AgentService.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController

@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private CitizenServiceClient citizenServiceClient;

    @Autowired
    private VaccinationCenterServiceClient vaccinationCenterServiceClient;

    @GetMapping
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgent(@PathVariable Integer id) {
        Optional<Agent> agent = agentRepository.findById(id);
        return agent.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentRepository.save(agent);
    }

    @PostMapping("/add-vaccination-center")
    public Object addVaccinationCenter(@RequestBody Map<String, Object> centerData) {
        return vaccinationCenterServiceClient.addVaccinationCenter(centerData);
    }

    @PostMapping("/add-citizen")
    public Object addCitizen(@RequestBody Map<String, Object> citizenData) {
        return citizenServiceClient.addCitizen(citizenData);
    }

    @GetMapping("/get-citizens-by-center/{centerId}")
    public List<Object> getCitizensByCenter(@PathVariable Integer centerId) {
        return citizenServiceClient.getCitizensByVaccinationCenterId(centerId);
    }

    @GetMapping("/get-center-details/{centerId}")
    public Object getCenterDetails(@PathVariable Integer centerId) {
        return vaccinationCenterServiceClient.getCenterDetails(centerId);
    }
}