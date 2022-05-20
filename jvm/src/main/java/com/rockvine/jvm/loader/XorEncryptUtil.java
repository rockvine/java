package com.rockvine.jvm.loader;

import java.io.*;

/**
 * @author rocky
 * @date 2022-05-18 23:12
 * @description 异或加密工具类
 */
public class XorEncryptUtil {

    // 异或操作, 可以进行加密和解密
    private static void xor(InputStream in, OutputStream out) throws Exception {
        int ch;
        while (-1 != (ch = in.read())) {
            ch = ch ^ 0xff;
            out.write(ch);
        }
    }

    // 加密方法
    public static void encrypt(File src, File des) throws Exception {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(des);

        xor(in, out);

        in.close();
        out.close();
    }

    // 解密方法
    public static byte[] decrypt(File src) throws Exception {
        InputStream in = new FileInputStream(src);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        xor(in, bos);
        byte[] data = bos.toByteArray();

        in.close();
        bos.close();
        return data;
    }
}
