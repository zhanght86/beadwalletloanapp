//package com.waterelephant.sxyDrainage.utils.dkdh360util;
//
//import java.io.ByteArrayOutputStream;
//import java.security.Key;
//import java.security.KeyFactory;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.Signature;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.crypto.Cipher;
//
///**
// * (code:dkdh001)
// *
// * @Author: zhangchong
// * @Date: 2018/7/24 18:03
// * @Description: rsa工具类
// */
//public class RsaUtils {
//    /**
//     * 加密算法RSA
//     */
//    private static final String KEY_ALGORITHM = "RSA";
//
//    /**
//     * 签名算法
//     */
//    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
//
//    /**
//     * 获取公钥的key
//     */
//    private static final String PUBLIC_KEY = "RSAPublicKey";
//
//    /**
//     * 获取私钥的key
//     */
//    private static final String PRIVATE_KEY = "RSAPrivateKey";
//
//    /**
//     * RSA最大加密明文大小
//     */
//    private static final int MAX_ENCRYPT_BLOCK = 117;
//
//    /**
//     * RSA最大解密密文大小
//     */
//    private static final int MAX_DECRYPT_BLOCK = 128;
//
//    /**
//     * <p>
//     * 生成密钥对(公钥和私钥)
//     * </p>
//     *
//     * @return Map
//     */
//    private static Map<String, Object> genKeyPair() throws Exception {
//        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
//        keyPairGen.initialize(1024);
//        KeyPair keyPair = keyPairGen.generateKeyPair();
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        Map<String, Object> keyMap = new HashMap<>(2);
//        keyMap.put(PUBLIC_KEY, publicKey);
//        keyMap.put(PRIVATE_KEY, privateKey);
//        return keyMap;
//    }
//
//    /**
//     * <p>
//     * 用私钥对信息生成数字签名
//     * </p>
//     *
//     * @param data       待加密数据
//     * @param privateKey 私钥(BASE64编码)
//     * @return String
//     */
//    public static String sign(byte[] data, String privateKey) throws Exception {
//        byte[] keyBytes = Base64Utils.decode(privateKey);
//        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
//        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//        signature.initSign(privateK);
//        signature.update(data);
//        return Base64Utils.encode(signature.sign());
//    }
//
//    /**
//     * <p>
//     * 校验数字签名
//     * </p>
//     *
//     * @param data      已加密数据
//     * @param publicKey 公钥(BASE64编码)
//     * @param sign      数字签名
//     * @return boolean
//     */
//    public static boolean verify(byte[] data, String publicKey, String sign)
//        throws Exception {
//        byte[] keyBytes = Base64Utils.decode(publicKey);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        PublicKey publicK = keyFactory.generatePublic(keySpec);
//        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//        signature.initVerify(publicK);
//        signature.update(data);
//        return signature.verify(Base64Utils.decode(sign));
//    }
//
//    /**
//     * <P>
//     * 私钥解密
//     * </p>
//     *
//     * @param encryptedData 已加密数据
//     * @param privateKey    私钥(BASE64编码)
//     * @return byte[]
//     */
//    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
//        throws Exception {
//        byte[] keyBytes = Base64Utils.decode(privateKey);
//        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.DECRYPT_MODE, privateK);
//        int inputLen = encryptedData.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段解密
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
//                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * MAX_DECRYPT_BLOCK;
//        }
//        byte[] decryptedData = out.toByteArray();
//        out.close();
//        return decryptedData;
//    }
//
//    /**
//     * <p>
//     * 公钥解密
//     * </p>
//     *
//     * @param encryptedData 已加密数据
//     * @param publicKey     公钥(BASE64编码)
//     * @return byte[]
//     */
//    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
//        throws Exception {
//        byte[] keyBytes = Base64Utils.decode(publicKey);
//        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        Key publicK = keyFactory.generatePublic(x509KeySpec);
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.DECRYPT_MODE, publicK);
//        int inputLen = encryptedData.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段解密
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
//                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * MAX_DECRYPT_BLOCK;
//        }
//        byte[] decryptedData = out.toByteArray();
//        out.close();
//        return decryptedData;
//    }
//
//    /**
//     * <p>
//     * 公钥加密
//     * </p>
//     *
//     * @param data      源数据
//     * @param publicKey 公钥(BASE64编码)
//     * @return byte[]
//     */
//    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
//        throws Exception {
//        byte[] keyBytes = Base64Utils.decode(publicKey);
//        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        Key publicK = keyFactory.generatePublic(x509KeySpec);
//        // 对数据加密
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.ENCRYPT_MODE, publicK);
//        int inputLen = data.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段加密
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
//                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(data, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * MAX_ENCRYPT_BLOCK;
//        }
//        byte[] encryptedData = out.toByteArray();
//        out.close();
//        return encryptedData;
//    }
//
//    /**
//     * <p>
//     * 私钥加密
//     * </p>
//     *
//     * @param data       源数据
//     * @param privateKey 私钥(BASE64编码)
//     * @return byte[]
//     */
//    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
//        throws Exception {
//        byte[] keyBytes = Base64Utils.decode(privateKey);
//        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.ENCRYPT_MODE, privateK);
//        int inputLen = data.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段加密
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
//                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(data, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * MAX_ENCRYPT_BLOCK;
//        }
//        byte[] encryptedData = out.toByteArray();
//        out.close();
//        return encryptedData;
//    }
//
//    /**
//     * <p>
//     * 获取私钥
//     * </p>
//     *
//     * @param keyMap 密钥对
//     * @return String
//     */
//    public static String getPrivateKey(Map<String, Object> keyMap) {
//        Key key = (Key) keyMap.get(PRIVATE_KEY);
//        return Base64Utils.encode(key.getEncoded());
//    }
//
//    /**
//     * <p>
//     * 获取公钥
//     * </p>
//     *
//     * @param keyMap 密钥对
//     * @return String
//     */
//    public static String getPublicKey(Map<String, Object> keyMap) {
//        Key key = (Key) keyMap.get(PUBLIC_KEY);
//        return Base64Utils.encode(key.getEncoded());
//    }
//
//    public static void main(String[] args) {
//        try {
//            Map<String, Object> keyMap = RsaUtils.genKeyPair();
//            String privateKey = RsaUtils.getPrivateKey(keyMap);
//            System.out.println("私钥：" + privateKey);
//            String publicKey = RsaUtils.getPublicKey(keyMap);
//            System.out.println("公钥：" + publicKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
