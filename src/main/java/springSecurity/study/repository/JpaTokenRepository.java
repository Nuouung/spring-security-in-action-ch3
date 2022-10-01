package springSecurity.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springSecurity.study.entity.Token;

import java.util.Optional;

public interface JpaTokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByIdentifier(String identifier);
}
