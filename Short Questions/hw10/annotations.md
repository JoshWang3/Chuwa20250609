<p> Spring Annotation Cheatsheet </p>
<p> List all the annotations you’ve learned from class and homework in a file named annotations.md.
   This will serve as your Spring annotation reference. </p>
<p> @SpringBootApplication The main class of a Spring Boot Application </p>
<p> @Repository Interact with a database. This is used to indicate that the class is a Data Access Object or repository </p>
<p> @Entity Map a Java Object with database </p>
<p> @Table Specifies the table name </p>
<p> @Column	Maps a field to a specific column in the table, and whether the field can be null or not </p>
<p> @Id Specify a field as a primary key </p>
<p> @GeneratedValue Specifies that the ID should be generated automatically </p>
<p> @RestController Handle Rest APIs such as GET, PUT, POST, DELETE </p>
<p> @RequestMapping Map the HTTP requests with the handler methods inside the controller class </p>
<p> @Autowired  Injects dependencies automatically into fields, constructors, or setters </p>
<p> @PostMapping HTTP Post call, create a new object </p>
<p> @GetMapping  HTTP Get call </p>
<p> @PutMapping  HTTP Put call, update the data based on the primary key, like id </p>
<p> @DeleteMapping HTTP Delete call, delete the data based on the primary key, like id </p>
<p> @RequestBody Converts incoming JSON payload to a Java object </p>
<p> @RequestParam Binds a query parameter (like sorting, pagination, filtering) to a method parameter </p>
<p> @PathVariable Binds part of the URL path (like id) to a method parameter </p>
<p> @ResponseStatus Changes the default HTTP status code returned by a method </p>
