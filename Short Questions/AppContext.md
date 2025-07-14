1. Using XML config:
```
ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
context.getBean("dependencyInjectionByTypeByName", DependencyInjectionByTypeByName.class).printFirstMessage();
```
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

        <context:component-scan base-package="com.chuwa.springbasic"/>
        <bean id="dataNucleusChuwaNoComponent"  class="com.chuwa.springbasic.components.impl.DataNucleusChuwaNoComponent" init-method="init" destroy-method="destroy"></bean>
</beans>
```

2. Using bean class(Annotation based):
```
ApplicationContext context2 = new AnnotationConfigApplicationContext(BeanConfig.class);
context2.getBean("dependencyInjectionByTypeByName", DependencyInjectionByTypeByName.class).printFirstMessage();
```
```
@Configuration
@ComponentScan(basePackages = {"com.chuwa.springbasic"})
public class BeanConfig {

    /**
     * bean 名是方法名
     */
    @Bean
    @Primary
    @Scope("prototype")
    public JpaChuwa myDataNucleus() {
        return new DataNucleusChuwaNoComponent();//new Object(), builder pattern
    }
}
```