package com.project.springbootwebstore.controller.constants;

public class CatalogUrlConstants {

    public static final String CATALOG = "/catalog";
    public static final String CATEGORY = "/{category}";
    public static final String SUBCATEGORY = "/{subcategory}";
    public static final String PRODUCT_ID = "/{productId}";
    public static final String GET_PRODUCTS = CATEGORY + SUBCATEGORY;
    public static final String GET_PRODUCT = CATEGORY + SUBCATEGORY + PRODUCT_ID;
}
