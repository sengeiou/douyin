package com.douyin;

public class Statis {
    /**
     * 文件保存的命名空间
     */
    public static String FILE_PATH = "G:/douyin_dev/";

    public static String FACE_PATH = "/face/";

    public static String VIDEO_PATH = "/video/";

    public static String getFaceFilePath(String userId, String fileName) {
        return FILE_PATH + userId + FACE_PATH + fileName;
    }


    public static String getVideoFilePath(String userId, String fileName) {
        return FILE_PATH + userId + VIDEO_PATH + fileName;
    }

    public static String getFaceFilePathDB(String userId, String fileName) {
        return "/" + userId + FACE_PATH + fileName;
    }

}
