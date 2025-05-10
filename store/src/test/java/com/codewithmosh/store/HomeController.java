package com.codewithmosh.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
To make this a controller, we have to decorate it with the @Controller annotation.
An annotation is like a label or a tag that we add in the code to give instructions or additional context
to the compiler. We can apply them to classes, methods, fields, parameters, and so on.

So in this example, by adding this annotation to this class, we are telling Spring that this class should
be used as a web controller
for receiving web traffic.
*/
@Controller
/*
Now when we send a request to the root of our website, we want this method to be called. To do that, we have
to apply another special annotation here that is @RequestMapping.

This annotation is defined in the package org.springframework.web. This is part of the web starter dependency
that we added in the previous lesson.

So we add this annotation and then give it an argument. That is a URL pattern. Here, we can type a forward
slash that represents the root of our website. So when a request goes to the root of our website, this
method gets called. If you want to receive traffic at a different endpoint like "about" or "contact," we can
change the argument here.
*/
@RequestMapping("/")
public class HomeController {
    @GetMapping()
    public String index() {
        /*
        In this method, we return the name of the view that should be returned to the browser. So we return
        "index.html."

        Now we need to create this view. Back to our project. We go to the resources' folder. This is where we
        add non-Java files.

        Now here we add a new folder called "static." And inside the static folder, we add a new file. Well, a
        new HTML file called "index.html."
        */
        return "index.html";
    }
}
