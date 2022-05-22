package nbu.logistic.company.mapper;

import nbu.logistic.company.api.dto.ShipmentDto;
import nbu.logistic.company.domain.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ShipmentMapper {

    @Mapping(target = "sentFromOfficeId", expression = "java( shipment.getSentFromOffice() != null ? shipment.getSentFromOffice().getId() : null )")
    @Mapping(target = "sentToOfficeId", expression = "java( shipment.getSentToOffice() != null ? shipment.getSentToOffice().getId() : null )")
    ShipmentDto toShipmentDto(Shipment shipment);
}
