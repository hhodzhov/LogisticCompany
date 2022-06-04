package nbu.logistic.company.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.api.dto.ShipmentDto;
import nbu.logistic.company.api.exceptions.GeneralApiException;
import nbu.logistic.company.config.tax.TaxConfig;
import nbu.logistic.company.domain.Office;
import nbu.logistic.company.domain.Shipment;
import nbu.logistic.company.mapper.ShipmentMapper;
import nbu.logistic.company.repository.ShipmentRepository;
import nbu.logistic.company.util.ConversionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShipmentService {

    ShipmentRepository shipmentRepository;
    ShipmentMapper shipmentMapper;
    OfficeService officeService;
    TaxConfig taxConfig;

    public List<ShipmentDto> getAllShipments() {

        return shipmentRepository
                .findAll()
                .stream()
                .map(shipmentMapper::toShipmentDto)
                .collect(Collectors.toList());
    }

    public double getProfitFromTo(LocalDateTime from, LocalDateTime to) {
        return shipmentRepository
                .findAll()
                .stream()
                .filter(s -> s.getSentDate().isAfter(from))
                .filter(s -> s.getSentDate().isEqual(to))
                .mapToDouble(Shipment::getPrice)
                .sum();
    }

    public void updateShipment(Long id, ShipmentDto shipmentDto) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new GeneralApiException(String.format("Shipment with id: %s does not exist!", id)));

        shipment.setDeliveryAddress(shipmentDto.getDeliveryAddress());
        shipment.setRecipientName(shipmentDto.getRecipientName());
        shipment.setToOffice(shipmentDto.isToOffice());
        shipment.setSenderName(shipmentDto.getSenderName());
        shipment.setWeight(shipmentDto.getWeight());
        shipment.setShipmentStatus(shipmentDto.getShipmentStatus());

        Optional<Office> officeFrom = officeService.findById(shipmentDto.getSentFromOfficeId());
        officeFrom.ifPresent(shipment::setSentFromOffice);

        Optional<Office> officeTo = officeService.findById(shipmentDto.getSentToOfficeId());
        officeTo.ifPresent(shipment::setSentToOffice);

        shipment.setSentDate(shipmentDto.getSentDate());
        shipment.setUpdatedDate(shipmentDto.getUpdatedDate());

    }

    public void delete(Long id) {
        shipmentRepository.deleteById(id);
    }

    public void createShipment(ShipmentDto shipmentDto) {
        Shipment shipment = shipmentMapper.toShipment(shipmentDto);
        shipment.setPrice(taxConfig.getPrice(shipmentDto.getWeight()));

        shipmentRepository.save(shipment);
    }
}
