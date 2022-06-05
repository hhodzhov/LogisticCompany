package nbu.logistic.company.repository;

import nbu.logistic.company.domain.ApiUser;
import nbu.logistic.company.domain.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
