package util;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class PasswordHashing {
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 100000;
    private static final int KEY_LENGTH = 256;

    public String hashPassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        try{
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            //  онвертируем байты в строки, использу€ кодировку Base64
            String saltStr = Base64.getEncoder().encodeToString(salt);
            String hashStr = Base64.getEncoder().encodeToString(hash);

            // ќбъедин€ем соль и хеш-код в одну строку, раздел€€ их символом ":"
            return saltStr + ":" + hashStr;
        }
        catch (Exception e){
            return null;
        }
    }

    public boolean verifyPassword(String password, String hashedPassword){
        // –аздел€ем соль и хеш-код в строке, использу€ символ ":"
        String[] parts = hashedPassword.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = Base64.getDecoder().decode(parts[1]);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        try{
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] testHash = factory.generateSecret(spec).getEncoded();
            return Arrays.equals(hash, testHash);
        }
        catch (Exception e){
           return false;
        }
    }
/*
    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public byte[] decodeUsingBigInteger(String hexString) {
        byte[] byteArray = new BigInteger(hexString, 16)
                .toByteArray();
        if (byteArray[0] == 0) {
            byte[] output = new byte[byteArray.length - 1];
            System.arraycopy(
                    byteArray, 1, output,
                    0, output.length);
            return output;
        }
        return byteArray;
    }*/
}
