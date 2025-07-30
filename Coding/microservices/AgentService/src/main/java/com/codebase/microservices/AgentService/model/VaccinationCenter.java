package com.codebase.microservices.AgentService.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class VaccinationCenter {
    private int id;
    private String centerName;
    private String centerAddress;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCenterName() { return centerName; }
    public void setCenterName(String centerName) { this.centerName = centerName; }
    public String getCenterAddress() { return centerAddress; }
    public void setCenterAddress(String centerAddress) { this.centerAddress = centerAddress; }
}