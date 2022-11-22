package springSecurity.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springSecurity.study.domain.Account;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
