
package com.adobe.aem.guides.esi.core.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductModel {

    @ValueMapValue(name = "productTitle")
    private String productTitle;

    @ValueMapValue(name = "productInfo")
    private String productInfo;

    @ValueMapValue(name = "productDate")
    private Date productDate;

    @ValueMapValue(name = "buttonText")
    private String buttonText;

    @ValueMapValue(name = "titleColor")
    private String titleColor;

    @ValueMapValue(name = "fileReference")
    private String fileReference;

    @PostConstruct
    protected void init() {
        if (productTitle == null || productTitle.isEmpty()) {
            productTitle = "預設標題";
        }
        if (productInfo == null || productInfo.isEmpty()) {
            productInfo = "無產品資訊";
        }
        if (buttonText == null || buttonText.isEmpty()) {
            buttonText = "詳細資訊";
        }
        if (titleColor == null || titleColor.isEmpty()) {
            titleColor = "Red"; // 預設顏色
        }
        if (fileReference == null || fileReference.isEmpty()) {
            fileReference = "Warning!NoPath!";
        }
    }

    public String getProductTitle() {
        if (productTitle != null && productTitle.length() >= 6) {
            return productTitle.substring(0, 6);
        }
        return productTitle;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public String getFormattedProductDate() {
        if (productDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(productDate);
        }
        return "";
    }

    public String getButtonText() {
        if (buttonText != null && buttonText.length() >= 6) {
            return buttonText.substring(0, 4);
        }
        return buttonText;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public String getFileReference() {
        return fileReference;
    }
}
