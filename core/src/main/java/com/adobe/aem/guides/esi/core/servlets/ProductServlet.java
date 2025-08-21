package com.adobe.aem.guides.esi.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = {Servlet.class},
        property = {
                "sling.servlet.paths=/bin/esi-practice/product",
                "sling.servlet.methods=GET",
                "sling.servlet.extensions=json"
        })
//http://localhost:4502/bin/esi-practice/product.json
public class ProductServlet extends SimpleServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ProductSourceServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {

        LOG.info("Processing GET request for /bin/esi-practice/product");
        resp.setContentType("application/json");
        resp.getWriter().write("{\"message\": \"我來自ProductServlet API呼叫\"}");
    }

}