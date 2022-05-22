package nbu.logistic.company.mapper;

import nbu.logistic.company.api.dto.ShipmentDto;
import nbu.logistic.company.domain.Shipment;
import nbu.logistic.company.util.ConversionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = {ConversionUtils.class}, componentModel = "spring")
public interface ShipmentMapper {

    @Mapping(target = "sentFromOfficeId", expression = "java( shipment.getSentFromOffice() != null ? shipment.getSentFromOffice().getId() : null )")
    @Mapping(target = "sentToOfficeId", expression = "java( shipment.getSentToOffice() != null ? shipment.getSentToOffice().getId() : null )")
    @Mapping(target = "sentDate", expression = "java( ConversionUtils.FROM_MILLIS_TO_UTC.applyAsLong(shipment.getSentDate()) )")
    @Mapping(target = "updatedDate", expression = "java( ConversionUtils.FROM_MILLIS_TO_UTC.applyAsLong(shipment.getUpdatedDate()) )")
    ShipmentDto toShipmentDto(Shipment shipment);

    @Mapping(target = "sentDate", expression = "java( ConversionUtils.FROM_EPOC_TO_LOCAL_DATE_TIME.apply(shipmentDto.getSentDate()) )")
    @Mapping(target = "updatedDate", expression = "java( ConversionUtils.FROM_EPOC_TO_LOCAL_DATE_TIME.apply(shipmentDto.getUpdatedDate()) )")
    Shipment toShipment(ShipmentDto shipmentDto);
}
