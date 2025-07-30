package com.codebase.microservices.AgentServiceApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Citizen {
    private int id;
    private String name;
    private int vaccinationCenterId;
}
