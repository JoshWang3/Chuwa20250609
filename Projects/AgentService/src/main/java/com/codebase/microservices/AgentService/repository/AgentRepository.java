package com.codebase.microservices.AgentService.repository;

import com.codebase.microservices.AgentService.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {
    List<Agent> findByVaccinationCenterId(Integer id);
}