package com.adobe.aem.guides.esi.core.servlets;
// 設置packageName
import com.adobe.aem.guides.esi.core.models.ProductModel;  // 引入你的 ProductModel
//import Model


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
                "sling.servlet.resourceTypes=esi/practice/components/product",  // 指定的component resourceType
                "sling.servlet.methods=GET",  // 處理怎樣的方法?
                "sling.servlet.extensions=json",  // 處理URL 後綴為怎樣的請求？
                "sling.servlet.selectors=data"  //選擇器用於區分數據請求，例如/content/your-site/product.data.json
        })
public class ProductSourceServlet extends SlingSafeMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        LOG.info("處理 Product resourceType 的 GET 請求");

        // 獲取當前資源（你的 Product component 的 JCR 節點）
        Resource resource = request.getResource();
        if (resource == null) {
            LOG.error("資源為空");
            response.sendError(SlingHttpServletResponse.SC_NOT_FOUND, "找不到資源");
            return;
        }

        // 將資源適配到你的 ProductModel
        ProductModel product = resource.adaptTo(ProductModel.class);
        if (product == null) {
            LOG.error("無法適配資源到 ProductModel");
            response.sendError(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR, "適配資源失敗");
            return;
        }

        // 創建 JSON 數據，使用你的 ProductModel 的 getter 方法
        Gson gson = new Gson();
        String json = gson.toJson(new ProductData(
                product.getProductTitle(),
                product.getProductInfo(),
                product.getFormattedProductDate(),
                product.getButtonText(),
                product.getTitleColor(),
                product.getFileReference()
        ));

        // 設置回應為 JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    // 內部類，用來結構化 JSON 數據（基於你的 ProductModel 屬性）
    private static class ProductData {
        private final String productTitle;
        private final String productInfo;
        private final String productDate;
        private final String buttonText;
        private final String titleColor;
        private final String fileReference;

        public ProductData(String productTitle, String productInfo, String productDate,
                           String buttonText, String titleColor, String fileReference) {
            this.productTitle = productTitle;
            this.productInfo = productInfo;
            this.productDate = productDate;
            this.buttonText = buttonText;
            this.titleColor = titleColor;
            this.fileReference = fileReference;
        }
    }
}