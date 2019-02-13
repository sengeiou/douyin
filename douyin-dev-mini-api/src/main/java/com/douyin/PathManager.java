package com.douyin;

public class PathManager {
    /**
     * 文件保存的命名空间
     */
    public static String FILE_PATH_PRE = "G:/douyin_dev/";

    public static String FACE_PATH = "/face/";

    public static String VIDEO_PATH = "/video/";

    public static String getFaceFilePath(String userId, String fileName) {
        return FILE_PATH_PRE + userId + FACE_PATH + fileName;
    }


    public static String getVideoFilePath(String userId, String fileName) {
        return FILE_PATH_PRE + userId + VIDEO_PATH + fileName;
    }

    public static String getFaceFilePathDB(String userId, String fileName) {
        return "/" + userId + FACE_PATH + fileName;
    }

    public static String getMp3Path(String partPath){
        return FILE_PATH_PRE+partPath;
    }

}
