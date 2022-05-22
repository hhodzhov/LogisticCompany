package nbu.logistic.company.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.enums.ShipmentStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentDto {

    String senderName;

    String recipientName;

    String deliveryAddress;

    Double weight;

    ShipmentStatus shipmentStatus;

    boolean toOffice;

    Long sentFromOfficeId;

    Long sentToOfficeId;

    LocalDateTime sentDate;

    LocalDateTime updatedDate;
}
