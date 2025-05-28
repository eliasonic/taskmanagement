
**SPRING_IOC_REST.md**

# Understanding Spring, IoC, and REST

## Spring Framework Overview
Spring is a comprehensive framework for Java development that provides infrastructure support for building robust applications. It offers:
- Dependency Injection (DI) and Inversion of Control (IoC)
- Aspect-oriented programming (AOP)
- Data access support
- Transaction management
- MVC framework for web applications

## Inversion of Control (IoC) and Dependency Injection (DI)
IoC is a design principle where the control of object creation and binding is transferred from the application code to the framework. In Spring, this is implemented through DI.

### Key Concepts:
1. **IoC Container**: Manages the lifecycle of beans and their dependencies
2. **Beans**: Objects managed by the Spring IoC container
3. **Dependency Injection**: Providing dependencies to objects rather than having them create dependencies themselves

### Implementation in Spring:
- `@Component`, `@Service`, `@Repository` annotations mark classes as Spring beans
- `@Autowired` or constructor injection is used to inject dependencies
- The Spring IoC container (`ApplicationContext`) manages the beans

## REST in Spring
Spring MVC provides excellent support for building RESTful web services. Key features include:
- Annotation-based controllers (`@RestController`)
- HTTP method mapping (`@GetMapping`, `@PostMapping`, etc.)
- Request/response body conversion (JSON/XML)
- Exception handling
- Validation support

### REST Principles:
1. **Stateless**: Each request contains all necessary information
2. **Resource-based**: Focus on resources (nouns) rather than actions (verbs)
3. **Uniform Interface**: Standard HTTP methods (GET, POST, PUT, DELETE)
4. **Representation**: Resources can have multiple representations (JSON, XML)

### Spring MVC Annotations:
- `@RestController`: Combines `@Controller` and `@ResponseBody`
- `@RequestMapping`: Maps web requests to handler methods
- `@PathVariable`: Extracts values from the URI
- `@RequestBody`: Binds HTTP request body to a Java object
- `@ResponseStatus`: Specifies HTTP response status
