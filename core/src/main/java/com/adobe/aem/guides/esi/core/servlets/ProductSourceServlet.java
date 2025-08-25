package com.adobe.aem.guides.esi.core.servlets;

import com.adobe.aem.guides.esi.core.models.ProductModel;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

import javax.jcr.Session;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.*;

@Component(
        service = {Servlet.class},
        property = {
                "sling.servlet.paths=/bin/productquery",
                "sling.servlet.methods=GET"
        }
)
// 測試路徑 http://localhost:4502/bin/productquery
public class ProductSourceServlet extends SlingSafeMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ProductSourceServlet.class);

    @Reference
    private QueryBuilder queryBuilder;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        try {
            Session session = request.getResourceResolver().adaptTo(Session.class);

            Map<String, String> params = new HashMap<>();
            params.put("path", "/content/esi-practice");
            params.put("property", "sling:resourceType");
            params.put("property.value", "esi-practice/components/product");
            params.put("p.limit", "-1"); // -1 = 全部
            params.put("primaryType", "nt:unstructured");

            PredicateGroup predicateGroup = PredicateGroup.create(params);

            Query query = queryBuilder.createQuery(predicateGroup, session);

            SearchResult result = query.getResult();


            List<String> paths = new ArrayList<>();
            Iterator<Hit> hits = result.getHits().iterator();
            while (hits.hasNext()) {
                Hit hit = hits.next();
                try {
                    Resource resource = hit.getResource();
                    ProductModel product = resource.adaptTo(ProductModel.class);

                    paths.add(hit.getPath());
                } catch (Exception e) {
                    LOG.error("無法獲取路徑: {}", e.getMessage());
                }
            }

            Map<String, List<String>> responseData = new HashMap<>();
            responseData.put("results", paths);

            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(responseData));

        } catch (Exception e) {
            LOG.error("Query Builder 錯誤: {}", e.getMessage(), e);
        }
    }
}
