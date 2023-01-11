package com.aeflheim.quasar;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {

    private GenerateCodeUtil() {}

    public static String generateCode() {
        String code;

        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            int c = secureRandom.nextInt(9000) + 1000;

            code = String.valueOf(c);
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem generating the random code.");
        }
        return code;
    }
}
