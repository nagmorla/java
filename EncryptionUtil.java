import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncryptionUtil {
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    public BGOCMEncryptionUtil() {
        super();
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    private SecretKey getKey() throws Exception {
        String strSaltKey = "SipSupportKeyForBGOCMApp";
        byte[] arrayBytes = strSaltKey.getBytes(UNICODE_FORMAT);
        KeySpec objKeySpec = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
        SecretKey key = skf.generateSecret(objKeySpec);
        return key;
    }
    
    /**
     *
     * @param strClearText
     * @return
     */
    public String encrypt(String strClearText) {
        String encryptedString = null;
        try {
            Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            cipher.init(Cipher.ENCRYPT_MODE, getKey());
            byte[] plainText = strClearText.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }
    
    /**
     *
     * @param strEncryptedText
     * @return
     */
    public String decrypt(String strEncryptedText) {
        String decryptedText = null;
        try {
            Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            cipher.init(Cipher.DECRYPT_MODE, getKey());
            byte[] encryptedText = Base64.decodeBase64(strEncryptedText);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
    
}

