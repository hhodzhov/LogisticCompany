package nbu.logistic.company.repository;

import nbu.logistic.company.domain.ApiUser;
import nbu.logistic.company.domain.LogisticCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface LogisticCompanyRepository extends JpaRepository<LogisticCompany, Long> {

    LogisticCompany findByName(String name);

    void deleteByName(String name);

}
