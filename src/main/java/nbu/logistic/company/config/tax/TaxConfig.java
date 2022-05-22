package nbu.logistic.company.config.tax;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "tax-config")
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaxConfig {

    Map<String, Range> weightKgMultipliers;

    public double getPrice(double weight) {

        return weightKgMultipliers.values()
                .stream()
                .filter(range -> between(weight, range.getMin(), range.getMax()))
                .findFirst()
                .map(Range::getTransportationTax)
                .orElse(100d);
    }

    @Setter
    @Getter
    public static class Range {
        double min;
        double max;
        double transportationTax;
    }

    private boolean between(double weight, double min, double max) {
        return weight >= min && weight <= max;
    }
}
