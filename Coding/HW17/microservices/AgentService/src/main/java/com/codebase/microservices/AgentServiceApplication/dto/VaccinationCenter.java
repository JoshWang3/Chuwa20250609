package com.codebase.microservices.AgentServiceApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationCenter {
    private int id;
    private String centerName;
    private String centerAddress;
}