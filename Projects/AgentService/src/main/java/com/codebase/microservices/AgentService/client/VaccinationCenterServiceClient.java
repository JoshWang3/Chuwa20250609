package com.codebase.microservices.AgentService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "VACCINATION-CENTER")
public interface VaccinationCenterServiceClient {

    @PostMapping("/vaccinationcenter/add")
    Object addVaccinationCenter(@RequestBody Object vaccinationCenter);

    @GetMapping("/vaccinationcenter/id/{id}")
    Object getCenterDetails(@PathVariable("id") Integer id);
}