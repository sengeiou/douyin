package com.douyin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FFMPEGUtils {

    protected  void executeCommandByFFMPEG(List<String> command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        //如果有错误防止当前的线程卡死进行相应的处理
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line = "";
        while ((line = br.readLine()) != null) {
        }
        if (br != null) {
            br.close();
        }
    }
}
