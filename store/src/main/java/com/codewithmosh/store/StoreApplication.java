package com.codewithmosh.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    /*
     * Earlier we talked about dependencies.
     * I mentioned that dependencies are third-party libraries or frameworks we use in our application.
     * For example, to build a web application, we need an embedded web server like Tomcat.
     * We need libraries for handling web requests, building APIs, processing JSON data, logging, and so on.
     *
     * Now in Spring Boot applications,
     * instead of adding multiple individual libraries, we can use a starter dependency,
     * which is a curated collection of libraries and frameworks that are commonly used together.
     * They are tested and verified by the Spring development team.
     *
     * So in this example, if we add the starter web dependency,
     * it will bring in a compatible version of these libraries.
     * Let's see this in action.
     * Open up your browser and search for Maven Central.
     * It's located at central.sonatype.com.
     * Maven Central is a public repository where we can find dependencies for our projects.
     * It's similar to NPM for JavaScript, PyPI for Python, NuGet for .NET, and so on.
     *
     * Now let's search for `spring-boot-starter-web`.
     * This dependency or project is owned by `org.springframework.boot`.
     *
     * To use this in our project, all we have to do is copy this piece of code into our `pom.xml` file.
     *
       <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.4.5</version>
       </dependency>
     *
     *
     * Back to Apache Maven.
     * Here you can see we have a dependency.
     * The group ID is `org.springframework.boot`,
     * and the artifact ID is `spring-boot-starter-web`.
     * We also have the version.
     * Now let's copy this to the clipboard.
     *
     * Back to IntelliJ.
     * Let's go to our `pom.xml` file.
     */

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
