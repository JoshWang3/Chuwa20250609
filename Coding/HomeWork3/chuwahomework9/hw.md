### 1 why use customer exception
    clear code writing
    flexibility and feasiblilty to adapt scalable project
    eaiser to handle specific error and debugging 
    enforce domain logic
    


### 2 Explain how @Table , @Column , and @Id work. What is the default behavior if you don’t use them?
    map pojo to a DB table
    ORM will use class name as default
    map pojo field to DB column
    ORM will use field name as default
    mark a field as primary key  generate Id for id colum. required 
    ORM failed to identify the primary key, leading to errors.

### 3 What happens if you don’t annotate a class with @Entity , but still try to save it with JPA?
    ORM will not be able to create the table and persistence fails 

### 4 What happens if we forget to annotate a controller with @RestController and only use @RequestMapping ?
    it will fail to generate any restful api. cuz the class is not registered as a spring bean for handling
    web requests. @RestController will not recognize it which is just a regular class.

### 5 What is the default naming strategy of JPA for tables and columns when no explicit name is given?
    table will use class name 
    column will use field name 

### 6 How does @PathVariable differ from @RequestParam ?
    @PathVariable defines what path var needed in the request 
    @RequestParam defines what ? param needed in the request 


## part 2 

### here is a method in a repository to find all posts with the title containing a certain keyword. 

![Screenshot 2025-07-02 at 5.40.19 PM.png](Screenshot%202025-07-02%20at%205.40.19%E2%80%AFPM.png)


![Screenshot 2025-07-02 at 6.23.15 PM.png](Screenshot%202025-07-02%20at%206.23.15%E2%80%AFPM.png)


![Screenshot 2025-07-02 at 6.23.22 PM.png](Screenshot%202025-07-02%20at%206.23.22%E2%80%AFPM.png)