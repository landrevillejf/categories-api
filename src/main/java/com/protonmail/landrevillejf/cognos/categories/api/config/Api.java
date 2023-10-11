package com.protonmail.landrevillejf.cognos.categories.api.config;

/**
 * Configuration class for API-related constants and paths.
 *
 */

@SuppressWarnings("CheckStyle")
public class Api {
    // URI Path
    /** API_URI_PATH. */
    public static final String API_URI_PATH = "/**";
    /** SWAGGER_URI_PATH. */
    public static final String SWAGGER_URI_PATH = "/swagger-ui/**";

    // angular-ui
    /** CROSS_ORIGINS. */
    public static final String CROSS_ORIGINS = "http://localhost:4200/";

    // Categories Api
    /** Categories endpoint. */
    public static final String CATEGORY = "/categories";
    /** Get Category by Uid. */
    public static final String CATEGORY_BY_CATEGORY_UID = "/{uid}";

    // SubCategories Api
    /** SubCategories endpoint. */
    public static final String SUBCATEGORY = "/subcategories";
    /** Get SubCategory by Uid. */
    public static final String SUBCATEGORY_BY_SUBCATEGORY_UID = "/{uid}";

    // Reports Api
    public static final String REPORTS = "/reports";
    public static final String EXCEL_CATEGORIES_REPORT = "/excel/categories";
    public static final String EXCEL_SUB_CATEGORIES_REPORT = "/excel/subcategories";
    public static final String PDF_FULL_REPORT = "/pdf/full-report";
    public static final String ZIP_REPORT = "/zip";
    public static final String MULTI_SHEET_EXCEL_REPORT = "/multi-sheet-excel";
    public static final String EXPORT_TO_HTML = "/export-to-html";
    public static final String EXPORT_TO_CSV = "/export-to-csv";
    public static final String PDF_SUBCATEGORIES_REPORT = "/pdf/subcategories-report";
    public static final String PDF_CATEGORIES_REPORT = "/pdf/categories-report";

    /** Get Verb. */
    public static final String GET = "GET";
    /** Post Verb. */
    public static final String POST = "POST";
    /** Put Verb. */
    public static final String PUT = "PUT";
    /** Delete Verb. */
    public static final String DELETE = "DELETE";
    /** ALLOWED_HEADERS. */
    public static final String ALLOWED_HEADERS = "*";

}
