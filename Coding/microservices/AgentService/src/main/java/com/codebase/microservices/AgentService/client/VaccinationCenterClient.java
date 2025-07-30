package com.codebase.microservices.AgentService.client;

import com.codebase.microservices.AgentService.model.RequiredResponse;
import com.codebase.microservices.AgentService.model.VaccinationCenter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "VACCINATION-CENTER")
public interface VaccinationCenterClient {

    @PostMapping("/vaccinationcenter/add")
    ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter);

    @GetMapping("/vaccinationcenter/id/{id}")
    ResponseEntity<RequiredResponse> getVaccinationCenterWithCitizens(@PathVariable Integer id);
}