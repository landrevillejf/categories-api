package com.protonmail.landrevillejf.cognos.categories.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class UUIDGenerator {
    private final static Logger logger = LoggerFactory.getLogger(UUIDGenerator.class);

    /**
     * Type 1 UUID Generation
     */
    public static UUID generateType1UUID() {

        long most64SigBits = 0;
        long least64SigBits = 0;
        try {
            most64SigBits = get64MostSignificantBitsForVersion1();
            least64SigBits = get64LeastSignificantBitsForVersion1();
        }  catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
        }

        return new UUID(most64SigBits, least64SigBits);
    }

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

    private static long get64MostSignificantBitsForVersion1() {
        long timeForUuidIn100Nanos = 0;
        long version = 0;
        long least12SignificatBitOfTime = 0;
        try {
            LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
            Duration duration = Duration.between(start, LocalDateTime.now());
            long seconds = duration.getSeconds();
            long nanos = duration.getNano();
            timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
            least12SignificatBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
            version = 1 << 12;
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
        }
        return (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificatBitOfTime;
    }
}
