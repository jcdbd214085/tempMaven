
        package com.adobe.aem.guides.esi.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = { Servlet.class },
        property = {
                "sling.servlet.paths=/bin/esi-practice/simple",
                "sling.servlet.methods=GET",
                "sling.servlet.extensions=json"
        })
public class HelloWorldServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Hello World!\"}");
    }
}
