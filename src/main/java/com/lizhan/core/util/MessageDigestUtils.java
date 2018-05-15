package com.lizhan.core.util;

import org.apache.commons.codec.binary.Hex;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtils {

    public static String getFileDigestHex(File file, String type) throws NoSuchAlgorithmException, IOException {
        byte[] bytes = getFileDigest(file, type);
        return Hex.encodeHexString(bytes);
    }

    public static byte[] getFileDigest(File file, String type) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance(type);
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            md.update(byteBuffer);
            byteBuffer.clear();
        }
        fileChannel.close();
        fileInputStream.close();
        return md.digest();
    }

    public static String getMessageDigestHex(byte[] input, String type) throws NoSuchAlgorithmException {
        byte[] bytes = getMessageDigest(input, type);
        return Hex.encodeHexString(bytes);
    }

    public static byte[] getMessageDigest(byte[] input, String type) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(type);
        md.update(input);
        return md.digest();
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        long startTime = System.currentTimeMillis();
        String sha256 = getFileDigestHex(new File("D:\\cn_windows_server_2016_x64_dvd_9718765.iso.emule.td"), "SHA-256");
        System.out.println("用时" + (System.currentTimeMillis() - startTime));
        System.out.println(sha256);
//        Provider[] providers = Security.getProviders();
//        for (Provider provider : providers) {
//            System.out.println();
//            System.out.println(provider.toString() + "=====");
//            for (Object o : provider.keySet()) {
//                System.out.println(o + ":" + provider.getProperty(o.toString()));
//            }
//        }


    }


}
