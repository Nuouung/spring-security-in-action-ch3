package springSecurity.study.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerateCodeUtils {

    public static String generateCode() {
        String code;

        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            code = Integer.toString(secureRandom.nextInt(9000) + 1000); // 1000 ~ 9999
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code");
        }

        return code;
    }
}
