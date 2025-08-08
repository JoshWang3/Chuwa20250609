1. microservice repo: Create a new service called  AgentService and register it to Euraka server and API gateway. use open feign in AgentService to make calls to both CitizenService and VaccinationService (any endpoint is fine). (create your database record as needed)
Share your screen shot in markdown file.
example code will be provided after 07/28 in feign-client branch. try without looking at this first.

[microservice](../../Coding/HW17/microservices/AgentService)

!()[EurekaServer.png]

!()[postman.png]

2. kafka repo: create your database/DAO and save the message in the  consumer. implement both At-Least-Once and At-Most-Once.  
share your screen shot in the markdown file

[microservice](../../Coding/HW17/Spring-Producer-Consumer/src/main/java/com/chuwa/demo/service/AtLeastOnceConsumer.java)

[microservice](../../Coding/HW17/Spring-Producer-Consumer/src/main/java/com/chuwa/demo/service/AtMostOnceConsumer.java)

!()[database.png]