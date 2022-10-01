package springSecurity.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springSecurity.study.model.Otp;

public interface OtpRepository extends JpaRepository<Otp, String> {
}
