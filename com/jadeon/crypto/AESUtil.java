    import org.apache.commons.codec.binary.Base64;

    import javax.crypto.Cipher;
    import javax.crypto.spec.IvParameterSpec;
    import javax.crypto.spec.SecretKeySpec;

    public class AESUtil {

        private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

        public static String encrypt(String content, byte[] key, byte[] iv) {
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            Cipher cipher;
            String encodeBase64String = null;
            try {
                cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
                byte[] encData = cipher.doFinal(content.getBytes());
                encodeBase64String = Base64.encodeBase64String(encData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return encodeBase64String;
        }

        public static String decrypt(String content, byte[] key, byte[] iv) {
            byte[] encData = Base64.decodeBase64(content);
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            Cipher cipher;
            byte[] decbbdt = null;
            try {
                cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
                decbbdt = cipher.doFinal(encData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new String(decbbdt);
        }

        public static void main(String[] args) throws Exception {

            String str = "fot4Ginq4iey7kLUUmA+dA==";

            byte[] key = Base64.decodeBase64(str);
            String content = "12345678ilkljklkjv";
            String iv = "1234567890123456";
            System.out.println("加密前： "+ content);
            String encrypt = AESUtil.encrypt(content, key, iv.getBytes());
            System.out.println("加密后： "+new String(encrypt));
            String decrypt = AESUtil.decrypt(encrypt, key, iv.getBytes());
            System.out.println("解密后： "+decrypt);


            System.out.println();
    //        str = "1234567890abcdef";
    //        key = Base64.decodeBase64(str);
            content = "{\"OperatorID\":\"123456789\",\"OperatorSecret\":\"1234567890abcdef\"}";
    //        iv = "1234567890abcdef";
            System.out.println("加密前： " + content);
            encrypt = AESUtil.encrypt(content, key, iv.getBytes());
            System.out.println("加密后： " + new String(encrypt));
            encrypt = "mYvffpNoFf4E/ZTC1tOw41TC5OlkEobfAYCm5N8hEusaLUaUIqOrXtdbMrSck0DSmfM7mRuOGMoCQzH0nWPGuw==";

            System.out.println("解密后： " + decrypt);
        }
    }
