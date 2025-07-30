package com.codebase.microservices.AgentService.controller;

import com.codebase.microservices.AgentService.client.CitizenServiceClient;
import com.codebase.microservices.AgentService.client.VaccinationCenterClient;
import com.codebase.microservices.AgentService.entity.Agent;
import com.codebase.microservices.AgentService.model.Citizen;
import com.codebase.microservices.AgentService.model.RequiredResponse;
import com.codebase.microservices.AgentService.model.VaccinationCenter;
import com.codebase.microservices.AgentService.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private CitizenServiceClient citizenServiceClient;

    @Autowired
    private VaccinationCenterClient vaccinationCenterClient;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Agent Service is running!", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
        Agent savedAgent = agentRepository.save(agent);
        return new ResponseEntity<>(savedAgent, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Agent>> getAllAgents() {
        List<Agent> agents = agentRepository.findAll();
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable Integer id) {
        Optional<Agent> agent = agentRepository.findById(id);
        if (agent.isPresent()) {
            return new ResponseEntity<>(agent.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/citizen/test")
    public ResponseEntity<String> testCitizenService() {
        return citizenServiceClient.testCitizenService();
    }

    @GetMapping("/citizens/center/{centerId}")
    public ResponseEntity<List<Citizen>> getCitizensByCenter(@PathVariable Integer centerId) {
        return citizenServiceClient.getCitizensByVaccinationCenterId(centerId);
    }

    @PostMapping("/citizen/add")
    public ResponseEntity<Citizen> addCitizenViaAgent(@RequestBody Citizen citizen) {
        return citizenServiceClient.addCitizen(citizen);
    }

    @PostMapping("/vaccinationcenter/add")
    public ResponseEntity<VaccinationCenter> addVaccinationCenterViaAgent(@RequestBody VaccinationCenter center) {
        return vaccinationCenterClient.addVaccinationCenter(center);
    }

    @GetMapping("/vaccinationcenter/{id}")
    public ResponseEntity<RequiredResponse> getVaccinationCenterWithCitizens(@PathVariable Integer id) {
        return vaccinationCenterClient.getVaccinationCenterWithCitizens(id);
    }

    @GetMapping("/report/center/{centerId}")
    public ResponseEntity<String> generateReport(@PathVariable Integer centerId) {
        try {
            ResponseEntity<RequiredResponse> response = vaccinationCenterClient.getVaccinationCenterWithCitizens(centerId);
            RequiredResponse data = response.getBody();
            
            StringBuilder report = new StringBuilder();
            report.append("=== VACCINATION CENTER REPORT ===\n");
            
            if (data != null && data.getCenter() != null) {
                report.append("Center Name: ").append(data.getCenter().getCenterName()).append("\n");
                report.append("Center Address: ").append(data.getCenter().getCenterAddress()).append("\n");
                report.append("Total Citizens: ").append(data.getCitizens() != null ? data.getCitizens().size() : 0).append("\n");
                
                if (data.getCitizens() != null && !data.getCitizens().isEmpty()) {
                    report.append("\nCitizen List:\n");
                    for (Citizen citizen : data.getCitizens()) {
                        report.append("- ").append(citizen.getName()).append(" (ID: ").append(citizen.getId()).append(")\n");
                    }
                }
            }
            
            return new ResponseEntity<>(report.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating report: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}