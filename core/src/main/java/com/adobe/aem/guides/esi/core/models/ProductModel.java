package com.adobe.aem.guides.esi.core.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductModel {

    @ValueMapValue(name = "./productTitle")
    private String productTitle;

    @ValueMapValue(name = "./productInfo")
    private String productInfo;


    @ValueMapValue(name = "./productDate")
    private Date productDate;


    @ValueMapValue(name = "./buttonText")
    private String buttonText;


    public String getButtonText() {
        if (buttonText == null) {
            return "基礎資訊";
        }
        return buttonText;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductInfo() {
        return productInfo;
    }

    @ValueMapValue(name = "./fileReference")
    private String fileReference;

    public String getFileReference() {
        return fileReference;
    }

    public String getFormattedProductDate() {
        if (productDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(productDate);
        }
        return null;
    }
}