package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@SuppressWarnings("CheckStyle")
@Getter
public enum DesignSubCategoriesEnum {
    WEB_DESIGN("Web Design","Web Design"),
    GRAPHIC_DESIGN("Graphic Design","Graphic Design"),
    DESIGN_TOOLS("Design Tools","Design Tools"),
    USER_EXPERIENCE("User Experience","User Experience"),
    GAME_DESIGN("Game Design","Game Design"),
    DESIGN_THINKING("Design Thinking","Design Thinking"),
    ANIMATION("3D Animation","3D Animation"),
    FASHION("Fashion","Fashion"),
    ARCHITECTURAL_DESIGN("Architectural Design","Architectural Design"),
    INTERIOR_DESIGN("Interior Design","Interior Design"),
    OTHER("Design Other","Design Other");

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
    DesignSubCategoriesEnum(final String displayName, final String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
