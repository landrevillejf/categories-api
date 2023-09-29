package com.protonmail.landrevillejf.cognos.categories.api.config;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;

/**
 * Configuration class for API-related constants and paths.
 *
 */
@Author(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
         date = "2020-01-01",
         revision = 1,
         comments = "Author Api"
)
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
