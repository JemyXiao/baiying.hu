package com.baiying.hu.util;

/**
 * Created by jmx
 * 2017/10/20.
 */
public class FileUploadAddressUtils {
    private static final String ADDRESS_PREFIX = "http://";
    private static final String ADDRESS_SUFFIX = ":8080/upload/";

    public static String getImageAddress(String ip, String fileName) {
        return ADDRESS_PREFIX + ip + ADDRESS_SUFFIX + fileName;
    }
}
