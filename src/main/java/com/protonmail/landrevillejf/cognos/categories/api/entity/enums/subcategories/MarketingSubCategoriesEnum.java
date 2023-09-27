package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@SuppressWarnings("CheckStyle")
@Getter
public enum MarketingSubCategoriesEnum {
    Digital_Marketing("Digital Marketing","Digital Marketing"),
    Search_Engine_Optimization("Search Engine Optimization","Search Engine Optimization"),
    Social_Media_Marketing("Social Media Marketing","Social Media Marketing"),
    Branding("Branding","Branding"),
    Marketing_Fundamentals("Marketing Fundamentals","Marketing Fundamentals"),
    Analytics_Automaton("Analytics Automaton","Analytics Automaton"),
    Public_Relations("Public Relations","Public Relations"),
    Advertising("Advertising","Advertising"),
    Video_Mobile_Marketing("Video Mobile Marketing","Video Mobile Marketing"),
    Content_Marketing("Content Marketing","Content Marketing"),
    Non_Digital_Marketing("Non Digital Marketing","Non Digital Marketing"),
    Growth_Hacking("Growth Hacking","Growth Hacking"),
    Product_Marketing("Product Marketing","Product Marketing"),
    Affiliate_Marketing("Affiliate Marketing","Affiliate Marketing"),
    OTHER("Marketing Other","Marketing Other");

    /**
     *
     */
    private final String displayName;
    /**
     *
     */
    private final String description;

    /**
     *
     * @param displayName
     * @param description
     */
    MarketingSubCategoriesEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
