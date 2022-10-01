package springSecurity.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springSecurity.study.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
