package com.adobe.aem.guides.esi.core.servlets;

import com.adobe.aem.guides.esi.core.models.ProductModel;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = { Servlet.class },
        property = {
                "sling.servlet.resourceTypes=esi/practice/components/product",
                "sling.servlet.methods=GET",
                "sling.servlet.extensions=json",
                "sling.servlet.selectors=data"
        })
public class ProductSourceServlet extends SlingSafeMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ProductSourceServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        LOG.info("Processing GET request for Product resourceType");

        // 獲取當前資源
        Resource resource = request.getResource();
        if (resource == null) {
            LOG.error("Resource is null");
            response.sendError(SlingHttpServletResponse.SC_NOT_FOUND, "Resource not found");
            return;
        }

        // 適配資源到 ProductModel
        ProductModel product = resource.adaptTo(ProductModel.class);
        if (product == null) {
            LOG.error("Failed to adapt resource to ProductModel");
            response.sendError(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to adapt resource");
            return;
        }

        // 創建 JSON 數據
        Gson gson = new Gson();
        String json = gson.toJson(new ProductData(
                product.getProductTitle(),
                product.getProductInfo(),
                product.getFormattedProductDate(),
                product.getButtonText(),
                product.getTitleColor(),
                product.getFileReference()
        ));

        // 設置響應
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    // 內部類用於結構化 JSON 數據
    private static class ProductData {
        private final String productTitle;
        private final String productInfo;
        private final String productDate;
        private final String buttonText;
        private final String titleColor;
        private final String fileReference;

        public ProductData(String productTitle, String productInfo, String productDate, String buttonText, String titleColor, String fileReference) {
            this.productTitle = productTitle;
            this.productInfo = productInfo;
            this.productDate = productDate;
            this.buttonText = buttonText;
            this.titleColor = titleColor;
            this.fileReference = fileReference;
        }
    }
}