package nbu.logistic.company.api.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogisticCompanyDto {

    String name;

    String country;

    String city;

    String centralOfficeAddress;
}
