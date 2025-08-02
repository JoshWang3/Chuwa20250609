package com.codebase.microservices.AgentServiceApplication.client;

import com.codebase.microservices.AgentServiceApplication.dto.RequiredResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "VACCINATION-CENTER")
public interface VaccinationClient {
    @GetMapping("/vaccinationcenter/id/{id}")
    RequiredResponse getCenterDetails(@PathVariable("id") Integer id);
}
