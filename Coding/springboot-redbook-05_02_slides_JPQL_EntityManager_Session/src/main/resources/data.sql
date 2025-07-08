-- Sample data for Posts table
INSERT INTO posts (id, title, description, content, create_date_time, update_date_time) VALUES 
(1, 'Spring Boot Tutorial', 'Learn Spring Boot basics', 'This is a comprehensive guide to Spring Boot...', NOW(), NOW()),
(2, 'JPQL Guide', 'Understanding JPQL queries', 'JPQL (Java Persistence Query Language) is...', NOW(), NOW()),
(3, 'EntityManager Usage', 'How to use EntityManager', 'EntityManager is the main interface for...', NOW(), NOW()),
(4, 'Hibernate Session', 'Working with Hibernate Sessions', 'Hibernate Session provides methods for...', NOW(), NOW()); 