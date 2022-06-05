package nbu.logistic.company.mapper;

import nbu.logistic.company.api.dto.ShipmentDto;
import nbu.logistic.company.domain.Shipment;
import nbu.logistic.company.util.ConversionUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = {ConversionUtils.class}, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShipmentMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "sentFromOfficeId", expression = "java( shipment.getSentFromOffice() != null ? shipment.getSentFromOffice().getId() : null )")
    @Mapping(target = "sentToOfficeId", expression = "java( shipment.getSentToOffice() != null ? shipment.getSentToOffice().getId() : null )")
    ShipmentDto toShipmentDto(Shipment shipment);
    Shipment toShipment(ShipmentDto shipmentDto);
}
