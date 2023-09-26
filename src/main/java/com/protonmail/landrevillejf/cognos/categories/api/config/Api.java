package com.protonmail.landrevillejf.cognos.categories.api.config;

@SuppressWarnings("CheckStyle")
public class Api {
    //URI Path
    /**API_URI_PATH.
     *
     */
    public static final String API_URI_PATH = "/**";
    /**SWAGGER_URI_PATH.
     *
     */
    public static final String SWAGGER_URI_PATH = "/swagger-ui/**";

    //angular-ui
    /**CROSS_ORIGINS.
     *
     */
    public static final String CROSS_ORIGINS = "http://localhost:4200/";

    //Categories Api
    /**Categories endpoint.
     *
     */
    public static final String CATEGORY = "/categories";
    /**Get Category by Uid.
     *
     */
    public static final String CATEGORY_BY_CATEGORY_UID = "/{uid}";

    //SubCategories Api
    /**SubCategories endpoint.
     *
     */
    public static final String SUBCATEGORY = "/subcategories";
    /**Get SubCategory by Uid.
     *
     */
    public static final String SUBCATEGORY_BY_SUBCATEGORY_UID = "/{uid}";

    /** Get Verb.
     *
     */
    public static final String GET = "GET";
    /** Post Verb.
     *
     */
    public static final String POST = "POST";
    /** Put Verb
     *
     */
    public static final String PUT = "PUT";
    /** Delete Verb
     *
     */
    public static final String DELETE = "DELETE";
    /** ALLOWED_HEADERS
     *
     */
    public static final String ALLOWED_HEADERS = "*";
}
