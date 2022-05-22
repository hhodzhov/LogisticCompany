package nbu.logistic.company.repository;

import nbu.logistic.company.domain.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
