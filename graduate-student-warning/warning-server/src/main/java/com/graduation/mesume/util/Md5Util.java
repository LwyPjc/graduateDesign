package com.graduation.mesume.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.InputStream;
import java.security.MessageDigest;

/**
 * 计算文件的md值
 */
public class Md5Util {

    /**
     * 计算文件的全MD5值后，采用base64编码
     * @param fis
     * @return
     */
    public static String fullFileBase64Md5(InputStream fis){
        if(fis == null){
            return  null;
        }
        try {
            MessageDigest md5Md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int length ;
            while ((length = fis.read(buffer)) != -1){
                md5Md.update(buffer,0,length);
            }
            return Base64.encodeBase64String(md5Md.digest());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
