package com.protonmail.landrevillejf.cognos.categories.api.util;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.ExecutionTime;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.License;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * This class provides utility methods for generating Type 1 UUIDs (Universally Unique Identifiers)
 * based on time and random data.
 */
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
        date = "2022-10-28",
        revision = 1,
        comments = "Author UUIDGenerator"
)
@License(name = "Apache", version = "2.0", site = "https://www.apache.org/licenses/LICENSE-2.0.html")
@SuppressWarnings("CheckStyle")
public class UUIDGenerator {

    /**
     * Logger instance for UUIDGenerator class.
     */
    private final static Logger logger = LoggerFactory.getLogger(UUIDGenerator.class);

    /**
     * Generates a Type 1 UUID.
     *
     * @return A Type 1 UUID based on time and random data.
     */
    @ExecutionTime
    public static UUID generateType1UUID() {
        long most64SigBits = 0;
        long least64SigBits = 0;
        try {
            most64SigBits = get64MostSignificantBitsForVersion1();
            least64SigBits = get64LeastSignificantBitsForVersion1();
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
        }

        return new UUID(most64SigBits, least64SigBits);
    }

    /**
     * Generates the 64 least significant bits for a Type 1 UUID.
     *
     * @return The 64 least significant bits for a Type 1 UUID.
     */
    private static long get64LeastSignificantBitsForVersion1() {
        long random63BitLong = 0;
        long variant3BitFlag = 0;
        try {
            Random random = new Random();
            random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
            variant3BitFlag = 0x8000000000000000L;
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
        }
        return random63BitLong + variant3BitFlag;
    }

    /**
     * Generates the 64 most significant bits for a Type 1 UUID.
     *
     * @return The 64 most significant bits for a Type 1 UUID.
     */
    private static long get64MostSignificantBitsForVersion1() {
        long timeForUuidIn100Nanos = 0;
        long version = 0;
        long least12SignificantBitOfTime = 0;
        try {
            LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
            Duration duration = Duration.between(start, LocalDateTime.now());
            long seconds = duration.getSeconds();
            long nanos = duration.getNano();
            timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
            least12SignificantBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
            version = 1 << 12;
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
        }
        return (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificantBitOfTime;
    }
}
