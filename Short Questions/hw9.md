
## Part 1
1. specific error messages allows easier debugging.
2. - @Table maps entity to database table. default: class name
    - @Column maps field to table column. default: field name
    - @Id marks primary key. No default
3. "Not a managed type" error at run time.
4. returns view names instead of JSON.
5. camel case in code matches snake case in table. Uppercase letters match "_" + lowercase. 
6. - @PathVariable is part of path, e.g. /{id}/...
    - @RequestParam is parameter in URL, e.g. /users?name=john

## Part 2
![demo_1](demo_1.png)