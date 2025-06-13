# HW2: Java-Core-Assignment
@ Jun 12, 2025 _Gloria Wang_

## 1. Java POJO named "Employee"
_Coding details in HW2 package under Coding package_

## 2. How Employee Objects Are Allocated to JVM Memory
_Coding explanations are in `main` class_

**Stack:**
- `department` → reference to Department object on heap
- `address` → reference to Address object on heap
- `employee` → reference to Employee object on heap

**Heap:**
- Department object (fields: `id`, `name`)
- Address object (fields: `street`, `district`, `city`, `postcode`)
- Employee object (fields: `name`, `ssn`, `dateOfBirth`, `department`, `address`)
  
  (Employee contains references to Department and Address objects)

**Visualization:**

    Stack                        Heap

    employee1 ----+-------> Employee object (Harry Potter)
                                  |
                                  +-- department1 ----> Department object (Ministry of Magic)
                                  +-- address1    ----> Address object (Underground of Whitehall and the HM Treasury building, Westminster, London, SW1A 2HQ)
    
    employee2 ----+-------> Employee object (Sherlock Holmes)
                                  |
                                  +-- department2 ----> Department object (Detective)
                                  +-- address2    ----> Address object (221b Baker St., Marylebone, London, NW1 6XE)
                                  
    employee3 ----+-------> Employee object (SpongeBob SquarePants)
                                  |
                                  +-- department3 ----> Department object (Krusty Krab)
                                  +-- address3    ----> Address object (Krusty Krab, Bikini Bottom, Pacific Ocean, 00000)

**Summary:**
- The `new` keyword allocates an object on the **heap**
- Variable names (like `employee1`) are references stored on the **stack**
- Each `Employee` also holds references to its own `Department` and `Address` objects (which are also on the heap)

---

## 3. How Static Differs from Instance
_Coding explanations are in `main` class_
- **Static fields/methods** belong to the class itself, not to any one object.
  - Shared across all instances
  - Don’t need to create an object to use
  - Example: `Employee.getEmployeeCount()` or `Employee.printCompanyPolicy()`


- **Instance fields/methods** belong to individual objects.
  - Each object has its own values
  - Must access them via the object
  - Example: `employee1.getName()`

