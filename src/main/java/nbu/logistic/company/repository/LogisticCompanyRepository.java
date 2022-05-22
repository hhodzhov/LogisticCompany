package nbu.logistic.company.repository;

import nbu.logistic.company.domain.LogisticCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticCompanyRepository extends JpaRepository<LogisticCompany, Long> {

}
