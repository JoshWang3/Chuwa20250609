package com.codebase.microservices.AgentService.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Citizen {
    private int id;
    private String name;
    private int vaccinationCenterId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getVaccinationCenterId() { return vaccinationCenterId; }
    public void setVaccinationCenterId(int vaccinationCenterId) { this.vaccinationCenterId = vaccinationCenterId; }
}