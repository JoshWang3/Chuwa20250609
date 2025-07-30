# AgentService Implementation

## Overview
Created AgentService microservice that integrates with existing architecture (Eureka + API Gateway) and uses OpenFeign to call CitizenService and VaccinationService.

## Configuration
- **Port**: 8084
- **Database**: AgentService (MySQL)  
- **Eureka Name**: AGENT-SERVICE
- **Gateway Route**: `/agent/**`

## Structure
```
AgentService/
├── src/main/java/.../AgentService/
│   ├── AgentServiceApplication.java
│   ├── controller/AgentController.java
│   ├── entity/Agent.java
│   ├── repository/AgentRepository.java
│   ├── client/
│   │   ├── CitizenServiceClient.java
│   │   └── VaccinationCenterClient.java
│   └── model/
│       ├── Citizen.java
│       ├── VaccinationCenter.java
│       └── RequiredResponse.java
└── src/main/resources/application.yml
```

## Agent Entity
```java
@Entity
public class Agent {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email; 
    private String phone;
    private String department;
}
```

## Endpoints

### Direct Agent Operations
- `GET /agent/test` - Health check
- `POST /agent/add` - Create agent
- `GET /agent/all` - List all agents
- `GET /agent/{id}` - Get agent by id

### Feign Client Operations
- `GET /agent/citizen/test` - Test CitizenService connection
- `GET /agent/citizens/center/{centerId}` - Get citizens by center
- `POST /agent/citizen/add` - Add citizen via agent
- `POST /agent/vaccinationcenter/add` - Add vaccination center
- `GET /agent/vaccinationcenter/{id}` - Get center with citizens
- `GET /agent/report/center/{centerId}` - Generate report

## How to Run
1. Start MySQL with databases: `CitizenService`, `VaccinationCenter`, `AgentService`
2. Start services in order:
   - EurekaServer (8761)
   - CitizenService (8081) 
   - VaccinationCenter (8082)
   - API_Gateway (8083)
   - AgentService (8084)

## Test Examples
```bash
# Health check
curl http://localhost:8084/agent/test

# Add agent
curl -X POST http://localhost:8084/agent/add \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","phone":"123-456-7890","department":"Health"}'

# Test feign client
curl http://localhost:8084/agent/citizen/test

# Generate report
curl http://localhost:8084/agent/report/center/1
```

**Status**: ✅ Compiles and runs successfully