package com.douyin.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileSaveUtils {
    /**
     * 存储给定的文件流
     * @param finalFilePath
     * @param inputStream
     */
    public void saveFile(InputStream inputStream,String finalFilePath) throws IOException {
        FileOutputStream fileOutput = null;
        try{
            File outFile = new File(finalFilePath);
            makeSurePathIsValid(outFile);
            fileOutput = new FileOutputStream(outFile);
            IOUtils.copy(inputStream, fileOutput);
        } catch (FileNotFoundException e) {
            //todo logger
            e.printStackTrace();
        } catch (IOException e) {
            //todo logger
            e.printStackTrace();
        }finally {
            if (fileOutput != null) {
                fileOutput.flush();
                fileOutput.close();
            }
        }
    }

    private void makeSurePathIsValid(File outFile){
        //确保文件的父层级是个目录
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            outFile.getParentFile().mkdirs();
        }
    }
}
