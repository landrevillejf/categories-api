package com.protonmail.landrevillejf.cognos.categories.api.config;

public class Api {
    //URI Path
    public static final String API_URI_PATH = "/api/**";
    public static final String SWAGGER_URI_PATH = "/swagger-ui/**";

    //angular-ui
    public static final String CROSS_ORIGINS = "http://localhost:4200/";

    //Categories Api
    public static final String CATEGORY = "/categories";
    public static final String CATEGORY_BY_CATEGORY_UID = "/{uid}";

    //SubCategories Api
    public static final String SUBCATEGORY = "/subcategories";
    public static final String SUBCATEGORY_BY_SUBCATEGORY_UID = "/{uid}";
}
