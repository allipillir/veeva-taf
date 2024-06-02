package org.veeva.atf.utils;

import org.veeva.atf.constants.*;

public class ProductInformation {


    public static int getProductIndex(String productCategory, String productType) {
        int index = -1;
        switch (productCategory.toUpperCase()) {
            case "MEN":
                index = MenCategory.valueOf(productType).getValue();
                break;
            case "WOMEN":
                index = WomenCategory.valueOf(productType).getValue();
                break;
            case "KIDS":
                index = KidCategory.valueOf(productType).getValue();
                break;
        }
        return index;
    }

    public static String getProductCategory(String productCategory) {
        return ProductCategory.valueOf(productCategory).getValue();
    }

    public static String getProductType(String productType) {
        return ProductType.valueOf(productType).getValue();
    }

    public static int getProductMenu(String productCategory) {
        return MenuCategory.valueOf(productCategory).getValue();
    }

}
