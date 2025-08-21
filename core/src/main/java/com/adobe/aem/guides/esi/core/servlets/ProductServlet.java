package com.adobe.aem.guides.esi.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = { Servlet.class },
        property = {
                "sling.servlet.paths=/bin/esi-practice/product",
                "sling.servlet.methods=GET",
                "sling.servlet.extensions=json"
        })
public class ProductServlet extends SlingSafeMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        LOG.info("Processing GET request for /bin/esi-practice/product");

        // 模擬產品資料
        String json = "{"
                + "\"productTitle\": \"智慧手機\","
                + "\"buttonText\": \"立即購買\","
                + "\"fileReference\": \"/content/dam/esi-practice/smartphone.jpg\""
                + "}";

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
