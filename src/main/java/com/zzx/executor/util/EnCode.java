package com.zzx.executor.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;

public class EnCode {
    private static final String AES = "AES";
    private static final String CRYPT_KEY = "y4W01L8BkRAF3jhN";
    private static final String IV_STRING = "dMitH2RyqbeYVE2o";


    public static void main(String[] args) {
        String b = encrypt("9999999");
        System.out.println(b);
        System.out.println(decrypt(b));
    }



    public static String decrypt(String content) {
        try {
            byte[] encryptedBytes = hexToByteArray(content);
            byte[] enCodeFormat = CRYPT_KEY.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, AES);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] result = cipher.doFinal(encryptedBytes);
            return new String(result, "UTF-8");
        } catch (Exception e) {
        }
        return null;
    }


    public static String encrypt(String content) {
        byte[] encryptedBytes;
        try {
            byte[] byteContent = content.getBytes("UTF-8");
            // 注意，为了能与 iOS 统一
            // 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
            byte[] enCodeFormat = CRYPT_KEY.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, AES);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            // 指定加密的算法、工作模式和填充方式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            encryptedBytes = cipher.doFinal(byteContent);
            return new BigInteger(1, encryptedBytes).toString(16);
        } catch (Exception e) {
        }
        return "";
    }

    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {          //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {          //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }



    /*
     *//*
     * 加密
     * 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     *//*
        public static String encrypt(String content){
            try {
                //1.构造密钥生成器，指定为AES算法,不区分大小写
                KeyGenerator keygen=KeyGenerator.getInstance("AES");
                //2.根据ecnodeRules规则初始化密钥生成器
                //生成一个128位的随机源,根据传入的字节数组
                String encodeRules="1cb23387-d51a-42d8-b1af-6e3915ede276";
                keygen.init(128, new SecureRandom(encodeRules.getBytes()));
                //3.产生原始对称密钥
                SecretKey original_key=keygen.generateKey();
                //4.获得原始对称密钥的字节数组
                byte [] raw=original_key.getEncoded();
                //5.根据字节数组生成AES密钥
                SecretKey key=new SecretKeySpec(raw, "AES");
                //6.根据指定算法AES自成密码器
                Cipher cipher=Cipher.getInstance("AES");
                //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
                cipher.init(Cipher.ENCRYPT_MODE, key);
                //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
                byte [] byte_encode=content.getBytes("utf-8");
                //9.根据密码器的初始化方式--加密：将数据加密
                byte [] byte_AES=cipher.doFinal(byte_encode);
                //10.将加密后的数据转换为字符串
                //这里用Base64Encoder中会找不到包
                //解决办法：
                //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
                String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
                //11.将字符串返回
                return AES_encode;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            //如果有错就返加nulll
            return null;
        }

        *//*
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     *//*
        public static String decrypt(String content){
            try {
                //1.构造密钥生成器，指定为AES算法,不区分大小写
                KeyGenerator keygen=KeyGenerator.getInstance("AES");
                //2.根据ecnodeRules规则初始化密钥生成器
                //生成一个128位的随机源,根据传入的字节数组
                String encodeRules="1cb23387-d51a-42d8-b1af-6e3915ede276";
                keygen.init(128, new SecureRandom(encodeRules.getBytes()));
                //3.产生原始对称密钥
                SecretKey original_key=keygen.generateKey();
                //4.获得原始对称密钥的字节数组
                byte [] raw=original_key.getEncoded();
                //5.根据字节数组生成AES密钥
                SecretKey key=new SecretKeySpec(raw, "AES");
                //6.根据指定算法AES自成密码器
                Cipher cipher=Cipher.getInstance("AES");
                //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
                cipher.init(Cipher.DECRYPT_MODE, key);
                //8.将加密并编码后的内容解码成字节数组
                byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
                *//*
     * 解密
     *//*
                byte [] byte_decode=cipher.doFinal(byte_content);
                String AES_decode=new String(byte_decode,"utf-8");
                return AES_decode;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException e) {
                e.printStackTrace();
            }

            //如果有错就返加nulll
            return null;
        }*/

}
