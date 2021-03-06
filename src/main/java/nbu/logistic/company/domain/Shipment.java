package nbu.logistic.company.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.enums.ShipmentStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String senderName;

    String recipientName;

    String deliveryAddress;

    Double weight;

    ShipmentStatus shipmentStatus;

    boolean toOffice;

    @OneToOne
    Office sentFromOffice;

    @OneToOne
    Office sentToOffice;

    LocalDateTime sentDate;

    LocalDateTime updatedDate;

    String agent;

    double price;

}
