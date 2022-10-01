package springSecurity.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springSecurity.study.model.Otp;
import springSecurity.study.model.User;
import springSecurity.study.repository.OtpRepository;
import springSecurity.study.repository.UserRepository;
import springSecurity.study.util.GenerateCodeUtils;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;

    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user.getUsername();
    }

    public void auth(User userParam) {
        User user = userRepository.findById(userParam.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Bad credentials"));

        if (passwordEncoder.matches(userParam.getPassword(), user.getPassword())) {
            renewOtp(user);
        } else throw new BadCredentialsException("Bad credentials");
    }

    public boolean check(Otp otpParam) {
        Optional<Otp> maybeOtp = otpRepository.findById(otpParam.getUsername());

        if (maybeOtp.isPresent()) {
            Otp otp = maybeOtp.get();
            return otpParam.getCode().equals(otp.getCode());
        }

        return false;
    }

    private void renewOtp(User user) {
        String code = GenerateCodeUtils.generateCode(); // 1000 ~ 9999

        Optional<Otp> maybeOtp = otpRepository.findById(user.getPassword());

        if (maybeOtp.isPresent()) {
            maybeOtp.get().setCode(code);
        } else {
            Otp otp = new Otp();
            otp.setUsername(user.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }
}
