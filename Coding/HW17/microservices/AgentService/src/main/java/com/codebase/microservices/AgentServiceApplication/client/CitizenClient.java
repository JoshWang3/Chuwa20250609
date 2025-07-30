package com.codebase.microservices.AgentServiceApplication.client;

import com.codebase.microservices.AgentServiceApplication.dto.Citizen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CITIZEN-SERVICE")
public interface CitizenClient {
    @GetMapping("/citizen/id/{id}")
    List<Citizen> getCitizensByCenterId(@PathVariable("id") Integer id);
}
