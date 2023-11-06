package hu.otpmobile.ticketing.core.model.specifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import hu.otpmobile.ticketing.core.model.UserBankcard;

@Repository
public interface UserBankcardRepository extends JpaRepository<UserBankcard, String>, JpaSpecificationExecutor<UserBankcard> {

}
