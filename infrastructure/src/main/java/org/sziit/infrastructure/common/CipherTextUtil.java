package org.sziit.infrastructure.common;

import org.sziit.infrastructure.exception.UtilException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/18 22:14
 */
@SuppressWarnings("all")
public class CipherTextUtil {

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String ALGORITHM = "RSA";
    private static final int KEY_SIZE = 1024;

    private CipherTextUtil() {
    }

    public static String md5(String str) {
        str = str == null ? "" : str;
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] btInput = str.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] strA = new char[j * 2];
            int k = 0;
            byte[] var8 = md;
            int var9 = md.length;

            for (int var10 = 0; var10 < var9; ++var10) {
                byte byte0 = var8[var10];
                strA[k++] = hexDigits[byte0 >>> 4 & 15];
                strA[k++] = hexDigits[byte0 & 15];
            }

            return new String(strA);
        } catch (Exception var12) {
            throw new UtilException("MD5加密出现错误");
        }
    }

    public static String sha1(String str) {
        try {
            str = str == null ? "" : str;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            return getShaHexString(str, messageDigest);
        } catch (Exception var2) {
            throw new UtilException("SHA1加密出现错误");
        }
    }

    public static String sha256(String str) {
        try {
            str = str == null ? "" : str;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return getShaHexString(str, messageDigest);
        } catch (Exception var2) {
            throw new UtilException("SHA256加密出现错误");
        }
    }

    public static String sha384(String str) {
        try {
            str = str == null ? "" : str;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            return getShaHexString(str, messageDigest);
        } catch (Exception var2) {
            throw new UtilException("SHA384加密出现错误");
        }
    }

    public static String sha512(String str) {
        try {
            str = str == null ? "" : str;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            return getShaHexString(str, messageDigest);
        } catch (Exception var2) {
            throw new UtilException("SHA512加密出现错误");
        }
    }

    private static String getShaHexString(String str, MessageDigest messageDigest) {
        messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = messageDigest.digest();
        StringBuilder builder = new StringBuilder();
        byte[] var5 = bytes;
        int var6 = bytes.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            byte aByte = var5[var7];
            String temp = Integer.toHexString(aByte & 255);
            if (temp.length() == 1) {
                builder.append("0");
            }

            builder.append(temp);
        }

        return builder.toString();
    }

    public static String md5BySalt(String str, String salt) {
        return md5(md5(str) + md5(salt));
    }

    public static String sha256BySalt(String str, String salt) {
        return sha256(sha256(str) + sha256(salt));
    }

    public static String aesEncrypt(String key, String text) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = text.getBytes(StandardCharsets.UTF_8);
            cipher.init(1, getSecretKey(key));
            byte[] result = cipher.doFinal(byteContent);
            return encoder.encodeToString(result);
        } catch (Exception var5) {
            throw new UtilException("对称加密出现错误");
        }
    }

    public static String aesDecrypt(String key, String text) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, getSecretKey(key));
            byte[] result = cipher.doFinal(decoder.decode(text));
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception var4) {
            throw new UtilException("对称解密出现错误");
        }
    }

    private static SecretKeySpec getSecretKey(String password) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        kg.init(128, random);
        SecretKey secretKey = kg.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), "AES");
    }

    public static HashMap<String, String> rsaGenerateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024, new SecureRandom(UUID.randomUUID().toString().replaceAll("-", "").getBytes()));
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        HashMap<String, String> map = new HashMap(16);
        map.put("private", encoder.encodeToString(rsaPrivateKey.getEncoded()));
        map.put("public", encoder.encodeToString(rsaPublicKey.getEncoded()));
        return map;
    }

    public static String rsaEncryptByPublic(String publicKeyString, String content) {
        try {
            PublicKey publicKey = getPublicKeyFromString(publicKeyString);
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
            cipher.init(1, publicKey);
            int splitLength = ((RSAPublicKey) publicKey).getModulus().bitLength() / 8 - 11;
            byte[][] arrays = splitBytes(content.getBytes(), splitLength);
            StringBuilder stringBuilder = new StringBuilder();
            byte[][] var7 = arrays;
            int var8 = arrays.length;

            for (int var9 = 0; var9 < var8; ++var9) {
                byte[] array = var7[var9];
                stringBuilder.append(bytesToHexString(cipher.doFinal(array)));
            }

            return stringBuilder.toString();
        } catch (Exception var11) {
            throw new UtilException("非对称加密出现错误");
        }
    }

    public static String rsaEncryptByPrivate(String privateKeyString, String content) {
        try {
            PrivateKey privateKey = getPrivateKeyFromString(privateKeyString);
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
            cipher.init(1, privateKey);
            int splitLength = ((RSAPrivateKey) privateKey).getModulus().bitLength() / 8 - 11;
            byte[][] arrays = splitBytes(content.getBytes(), splitLength);
            StringBuilder stringBuilder = new StringBuilder();
            byte[][] var7 = arrays;
            int var8 = arrays.length;

            for (int var9 = 0; var9 < var8; ++var9) {
                byte[] array = var7[var9];
                stringBuilder.append(bytesToHexString(cipher.doFinal(array)));
            }

            return stringBuilder.toString();
        } catch (Exception var11) {
            throw new UtilException("非对称加密出现错误");
        }
    }

    public static String rsaDecryptByPublic(String publicKeyString, String content) {
        try {
            PublicKey publicKey = getPublicKeyFromString(publicKeyString);
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
            cipher.init(2, publicKey);
            int splitLength = ((RSAPublicKey) publicKey).getModulus().bitLength() / 8;
            byte[] contentBytes = hexStringToBytes(content);
            byte[][] arrays = splitBytes(contentBytes, splitLength);
            StringBuilder stringBuilder = new StringBuilder();
            byte[][] var8 = arrays;
            int var9 = arrays.length;

            for (int var10 = 0; var10 < var9; ++var10) {
                byte[] array = var8[var10];
                stringBuilder.append(new String(cipher.doFinal(array)));
            }

            return stringBuilder.toString();
        } catch (Exception var12) {
            throw new UtilException("非对称解密出现错误");
        }
    }

    public static String rsaDecryptByPrivate(String privateKeyString, String content) {
        try {
            PrivateKey privateKey = getPrivateKeyFromString(privateKeyString);
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
            cipher.init(2, privateKey);
            int splitLength = ((RSAPrivateKey) privateKey).getModulus().bitLength() / 8;
            byte[] contentBytes = hexStringToBytes(content);
            byte[][] arrays = splitBytes(contentBytes, splitLength);
            StringBuilder stringBuilder = new StringBuilder();
            byte[][] var8 = arrays;
            int var9 = arrays.length;

            for (int var10 = 0; var10 < var9; ++var10) {
                byte[] array = var8[var10];
                stringBuilder.append(new String(cipher.doFinal(array)));
            }

            return stringBuilder.toString();
        } catch (Exception var12) {
            throw new UtilException("非对称解密出现错误");
        }
    }

    private static PublicKey getPublicKeyFromString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        key = key.replace("\r\n", "");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(decoder.decode(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(x509KeySpec);
    }

    private static PrivateKey getPrivateKeyFromString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        key = key.replace("\r\n", "");
        PKCS8EncodedKeySpec x509KeySpec = new PKCS8EncodedKeySpec(decoder.decode(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(x509KeySpec);
    }

    private static byte[][] splitBytes(byte[] bytes, int splitLength) {
        int remainder = bytes.length % splitLength;
        int quotient = remainder != 0 ? bytes.length / splitLength + 1 : bytes.length / splitLength;
        byte[][] arrays = new byte[quotient][];

        for (int i = 0; i < quotient; ++i) {
            byte[] array;
            if (i == quotient - 1 && remainder != 0) {
                array = new byte[remainder];
                System.arraycopy(bytes, i * splitLength, array, 0, remainder);
            } else {
                array = new byte[splitLength];
                System.arraycopy(bytes, i * splitLength, array, 0, splitLength);
            }

            arrays[i] = array;
        }

        return arrays;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length);
        byte[] var3 = bytes;
        int var4 = bytes.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            byte aByte = var3[var5];
            String temp = Integer.toHexString(255 & aByte);
            if (temp.length() < 2) {
                sb.append(0);
            }

            sb.append(temp);
        }

        return sb.toString();
    }

    private static byte[] hexStringToBytes(String hex) {
        int len = hex.length() / 2;
        hex = hex.toUpperCase();
        byte[] result = new byte[len];
        char[] chars = hex.toCharArray();

        for (int i = 0; i < len; ++i) {
            int pos = i * 2;
            result[i] = (byte) (toByte(chars[pos]) << 4 | toByte(chars[pos + 1]));
        }

        return result;
    }

    private static byte toByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
