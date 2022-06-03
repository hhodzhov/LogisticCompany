package nbu.logistic.company.repository;

import nbu.logistic.company.domain.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    Office findByOfficeName(String officeName);

    void deleteByOfficeName(String officeName);
}
