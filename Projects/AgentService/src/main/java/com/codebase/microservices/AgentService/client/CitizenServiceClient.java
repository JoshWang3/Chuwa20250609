package com.codebase.microservices.AgentService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "CLIENT-SERVICE")
public interface CitizenServiceClient {

    @PostMapping("/citizen/add")
    Object addCitizen(@RequestBody Object citizen);

    @GetMapping("/citizen/id/{id}")
    List<Object> getCitizensByVaccinationCenterId(@PathVariable("id") Integer id);

    @GetMapping("/citizen/test")
    String testCitizenService();
}