package com.codewithmosh.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    /*
     * Now let's talk about our project structure.
     *
     * First, we have this folder `.idea` that contains a bunch of configuration files used by IntelliJ.
     * You never have to touch this.
     *
     * Then we have `.mvn`.
     * This is part of Maven wrapper,
     * which is a way to run Maven without requiring it to be globally installed on your machine.
     * With this, we can ensure consistent Maven builds across different environments.
     * So we can take this project,
     * put it on a different machine and build it with the exact same version of Maven.
     * And with this, we can prevent surprises.
     *
     * So inside this folder, we have the `wrapper` folder.
     * And in this folder,
     * we have a configuration file that specifies the version of Maven we're going to build this project with.
     * That is `3.3.2`.
     * This is different from the Maven you have globally installed on your machine.
     *
     * Now once again, I want to emphasize.
     * If you use IntelliJ,
     * you don't need to install Maven globally on your machine because IntelliJ comes with Maven built-in.
     * Okay?
     * So this is our configuration file.
     *
     * Now in this project, we also have two Maven wrapper files in the root.
     * We have `mvnw`, which is for Mac or Linux, and `mvnw.cmd` for Windows.
     * Both of these files are shell scripts.
     * So here we have some code
     * that would automatically download the version of Maven specified in this configuration file.
     *
     * Okay?
     * So that's the `.mvn` folder.
     *
     * Then we have a couple of files for our git repositories.
     * That's pretty standard.
     * We're not going to talk about them in this course.
     *
     * We have `HELP.md`, which is a Markdown file that contains instructions for getting started.
     * Again, we don't care about it.
     *
     * Next, we have `pom.xml`.
     * This is short for Project Object Model.
     * And this is the heart of a Maven project.
     * So in this file, we have some configuration about our project and its dependencies.
     *
     * Now what you see here is a format called XML, which a lot of younger developers are not familiar with.
     * It's similar to HTML.
     * So here we have open and close tags.
     * And in between these tags, we have some data.
     *
     * So here we have all the attributes we specified at the time of creating our project.
     * We have `groupId`, `artifactId`;
     * This is the version of our project and so on.
     *
     * Further down below, we have a tag called `dependencies`,
     * which we're going to talk about later in this section.
     *
     * So `pom.xml` is the heart of Maven projects.
     * Maven uses this file to download dependencies and build our project.
     *
     * Now back to our project structure.
     * In the `src` folder, we have the actual code for our project.
     * We have `main` and `test`.
     * In the `test` folder, we write our automated tests.
     * In the `main` folder, we write the actual code.
     *
     * Here we have two subfolders, `java`,
     * where we have our Java files, and `resources` where we have non-Java files,
     * like configuration files, as well as static assets like HTML, CSS, JavaScript, and so on.
     *
     * In the `resources` folder, we have a configuration file called `application.properties`.
     * Here, we can have one or more key-value pairs.
     * So here is a key `spring.application.name`, and the value is `store`.
     * In this file, we can specify the server port, our database settings, and so on.
     *
     * Now here in the `java` folder,
     * we have a package based on the group and artifact we specified when creating this project.
     * In this package, we currently have one file, `StoreApplication`,
     * which is the entry point to our application.
     *
     * So here we have a Java class.
     * And inside this class, we have our familiar `main` method.
     * In this method, we have a call to the ` SpringApplication.run ` method.
     *
     * So that's the basics of our project structure.
     */

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
