package springSecurity.study.security.csrf.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;
import springSecurity.study.entity.Token;
import springSecurity.study.repository.JpaTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final JpaTokenRepository jpaTokenRepository;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> maybeToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if (maybeToken.isPresent()) {
            maybeToken.get()
                    .setToken(csrfToken.getToken());
        } else {
            Token newToken = new Token(identifier, csrfToken.getToken());
            jpaTokenRepository.save(newToken);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");

        Optional<Token> maybeToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if (maybeToken.isEmpty()) return null;

        Token token = maybeToken.get();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
    }
}
