package com.codebase.microservices.AgentService.entity;

import javax.persistence.*;

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;

    @Column(name = "vaccination_center_id")
    private Integer vaccinationCenterId;

    // Constructors
    public Agent() {}

    public Agent(String name, String email, Integer vaccinationCenterId) {
        this.name = name;
        this.email = email;
        this.vaccinationCenterId = vaccinationCenterId;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getVaccinationCenterId() { return vaccinationCenterId; }
    public void setVaccinationCenterId(Integer vaccinationCenterId) { this.vaccinationCenterId = vaccinationCenterId; }
}