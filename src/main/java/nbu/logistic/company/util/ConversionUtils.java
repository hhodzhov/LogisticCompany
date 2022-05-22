package nbu.logistic.company.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.function.LongFunction;
import java.util.function.ToLongFunction;

public class ConversionUtils {
    private ConversionUtils() {
        throw new IllegalStateException("Utility class!");
    }

    private static final Integer MILLS_MULTIPLIER = 1000;


    public static final LongFunction<LocalDateTime> FROM_EPOC_TO_LOCAL_DATE_TIME = epoch ->
            Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();

    public static final LongFunction<Long> SECONDS_TO_MILLS = aLong -> aLong * MILLS_MULTIPLIER;

    public static final ToLongFunction<LocalDateTime> FROM_MILLIS_TO_UTC = localDateTime ->
            SECONDS_TO_MILLS.apply(localDateTime.toEpochSecond(ZoneOffset.UTC));
}
