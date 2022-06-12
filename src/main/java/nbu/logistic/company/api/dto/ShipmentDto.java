package nbu.logistic.company.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.enums.ShipmentStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentDto {

    Long id;

    String senderName;

    String recipientName;

    String deliveryAddress;

    double weight;

    @Enumerated(EnumType.STRING)
    ShipmentStatus shipmentStatus;

    boolean toOffice;

    Long sentFromOfficeId;

    Long sentToOfficeId;

    LocalDateTime sentDate;

    LocalDateTime updatedDate;

    String agent;

    double price;

}
