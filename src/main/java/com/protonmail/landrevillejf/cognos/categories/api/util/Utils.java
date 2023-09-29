package com.protonmail.landrevillejf.cognos.categories.api.util;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * Utility class containing various helper methods.
 */
@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Maintainer(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Revision(
        date = "2020-01-01",
        revision = 1,
        comments = "Author Utils"
)
public class Utils {

    /**
     * Creates a pageable object based on page, size, and sorting criteria.
     *
     * @param sortList A list of sorting items to define sorting criteria.
     * @param page     The page number for pagination (0-based index).
     * @param size     The number of items per page.
     * @return A Pageable object configured with the specified sorting criteria, page, and size.
     */
    public static Pageable createPageableBasedOnPageAndSizeAndSorting(List<SortItem> sortList, Integer page, Integer size) {
        List<Order> orders = new ArrayList<>();

        if (sortList != null) {
            // Iterate through the SortList to determine the sorting criteria.
            for (SortItem sortValue : sortList) {
                orders.add(new Order(sortValue.getDirection(), sortValue.getField()));
            }
        }

        if (page == null) {
            page = 0;
        }

        if (size == null) {
            size = 0;
        }

        return PageRequest.of(page, size, Sort.by(orders));
    }

    /**
     * Get the current date as a formatted string.
     *
     * @return A string representation of the current date in the format "dd-MM-yyyy".
     */
    public static String getCurrentDateAsString() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return dateTimeFormatter.format(localDate);
    }
}

