package com.codebase.microservices.AgentService.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class RequiredResponse {
    private VaccinationCenter center;
    private List<Citizen> citizens;

    public VaccinationCenter getCenter() { return center; }
    public void setCenter(VaccinationCenter center) { this.center = center; }
    public List<Citizen> getCitizens() { return citizens; }
    public void setCitizens(List<Citizen> citizens) { this.citizens = citizens; }
}