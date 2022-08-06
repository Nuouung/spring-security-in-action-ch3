package springSecurity.study.security.crypto;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha512PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return hasWithSHA512(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashedPassword = encode(rawPassword);
        return encodedPassword.equals(hashedPassword);
    }

    private String hasWithSHA512(String rawPassword) {

        StringBuilder encodedPassword = new StringBuilder();
        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] digestedRawPasswordBytes = messageDigest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));

            for (byte digestedRawPasswordByte : digestedRawPasswordBytes) {
                encodedPassword.append(Integer.toHexString(0xFF & digestedRawPasswordByte));
            }
        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("Bad Algorithm");
        }

        return encodedPassword.toString();
    }
}
