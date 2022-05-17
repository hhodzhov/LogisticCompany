package nbu.logistic.company.repository;

import nbu.logistic.company.domain.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApiUser, Long> {

    ApiUser findByUsername(String userName);
}
