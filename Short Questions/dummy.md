### Short Questions


question3:
In Spring (especially in Spring Boot REST APIs), we often use ModelMapper (or similar libraries like MapStruct) to convert between different object types, such as:

Entity ↔ DTO (Data Transfer Object)

Form object ↔ Domain object
Why do we need ModelMapper?
Separation of concerns
Entities often represent database structure, while DTOs are shaped for API responses or client consumption. ModelMapper helps keep them separate cleanly.

Avoid exposing internal structures
Directly exposing your JPA entities (e.g., Post, User) can leak internal database logic. Mapping lets you control what to show.

Custom shaping of API responses
Often, you don’t want to return the full entity but a subset (or transformed data). DTOs + mapping let you customize API output.

Reduce boilerplate

question4:
1. public class Post {
    private String title;
}

public class PostDto {
    private String heading;  // 字段名不同
}

2. 嵌套对象未展开

public class Post {
    private User author;  // 嵌套对象
}

public class PostDto {
    private String authorName;  // 扁平结构
}
public class User {
    private String name;
}
3. 类型不兼容（如 List ↔ Set）或转换失败
public class Post {
    private List<String> tags;
}

public class PostDto {
    private Set<String> tags;  // 类型不一致
}

question5:
 How ModelMapper Handles Type Conversion
Same Types (e.g., String → String):
→ Direct copy. ✅

Compatible Types (e.g., int → Integer, long → Long):
→ Uses Java's autoboxing/unboxing. ✅

Incompatible Types (e.g., String → Integer, List → Set):
→ ❌ Will fail unless you register a custom Converter.

Nested or Custom Objects:
→ Will try to recursively map using default constructor and field names.
→ Fails if types are too different or need transformation.

question7:
While @ControllerAdvice is the most commonly used approach for global exception handling in Spring, you can choose the right approach based on your needs. If you need centralized, consistent exception handling, @ControllerAdvice or @RestControllerAdvice is the best option. However, if you require more granular control, or need to handle exceptions differently per controller, then @ExceptionHandler or custom exception resolvers may be appropriate.

question8:
比较点	                                      普通异常	                                        自定义异常 + ControllerAdvice
抛出位置	                    可以在任何位置直接 throw new Exception()	                         通常是 throw new CustomApiException()
是否统一响应结构	             不一定，可能返回 HTML 错误页面或默认 JSON                          是，返回统一 JSON 格式（如 code/message/timestamp）
前端友好性	                           不适合 API 客户端解析	                                   易于前端统一处理错误码
日志追踪	                            默认日志不详细	                                          可以定制日志输出、异常分类、分级等

question10:
Spring empowers developers to focus on business logic while handling infrastructure concerns under the hood. It’s a key foundation for modern Java applications, from small APIs to enterprise microservices.
1. 控制反转（IoC）和依赖注入（DI）
2. 面向切面编程（AOP）
3. 声明式事务管理
4. MVC 架构
5. 整合持久层框架
6. 模块化设计

question11:
 1. 构造器注入（Constructor Injection）
 2. Setter 注入（Setter Injection）
  3. 字段注入（Field Injection）

question12:
1. ClassPathXmlApplicationContext
描述：
从 classpath 路径下 加载 XML 配置文件。
常用于早期基于 XML 的配置项目。
2. FileSystemXmlApplicationContext
描述：
从 磁盘上的任意位置 加载 XML 配置文件（不是 classpath）。
可加载外部配置
3. AnnotationConfigApplicationContext
描述：
专门用于加载基于 Java 配置类（@Configuration） 的 Spring 应用上下文。
是现代 Spring Boot 项目的主流方式。

4. WebApplicationContext
描述：
专门为 Web 应用（如 Spring MVC） 设计的 ApplicationContext。
能与 ServletContext 集成。

question13：
注解	                所在位置	                      作用
@Component	             类上	                     将该类标记为一个 Spring 管理的组件（Bean）
@Bean	         方法上（@Configuration 类中）	       手动注册一个方法返回的对象为 Spring Bean

question14：
Use Case	                                                    Recommended Scope
Stateless service or utility	                                  singleton
New instance needed every time (e.g., DTO builder)	              prototype
Web request handling with request-specific state	              request
Session-based storage for user login/session data	              session
Application-wide config/resource in a web app	                  application
Per WebSocket connection data	                                  websocket

question 15：
bean class:
定义： class 是这个 Bean 所对应的 Java 类的全限定名（Fully Qualified Class Name）。

作用： 告诉 Spring 要实例化哪个类的对象。

bean id：
定义： id 是该 Bean 在 Spring 容器中的 唯一标识符（名字）。

作用： 允许其他 Bean 或代码通过名字引用它。

question16:
当一个接口有多个实现类（Multiple Implementations）时，Spring 在自动注入（@Autowired）时可能会出现 歧义性注入（Ambiguity） 的问题。Spring 默认不知道该注入哪一个实现，因此你需要手动指定或使用一些机制来告诉 Spring 选择哪一个。
只有一个实现类 → 自动注入，不报错。

多个实现类

如果有 @Primary → 自动选择带 @Primary 的。

如果用 @Qualifier → 精确匹配对应名字。

如果都没有 → 报错：NoUniqueBeanDefinitionException