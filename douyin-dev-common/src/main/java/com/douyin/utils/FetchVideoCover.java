package com.douyin.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author manchaoyang
 */
@Component
public class FetchVideoCover {

    private String ffmpegExe = "H:\\utils_software\\ffmpeg\\ffmpeg-20181220-16ec62b-win64-static\\bin\\ffmpeg.exe";


    public void fetchCover(String videoInputPath, String coverOutputPath) throws IOException {
        //		ffmpeg.exe -ss 00:00:01 -i spring.mp4 -vframes 1 bb.jpg
        List<String> command = new java.util.ArrayList<String>();
        command.add(ffmpegExe);

        // 指定截取第1秒
        command.add("-ss");
        command.add("00:00:01");

        command.add("-y");
        command.add("-i");
        command.add(videoInputPath);

        command.add("-vframes");
        command.add("1");

        command.add(coverOutputPath);

        for (String c : command) {
            System.out.print(c + " ");
        }

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line = "";
        while ((line = br.readLine()) != null) {
        }
        if (br != null) {
            br.close();
        }
    }

    public static void main(String[] args){
        FetchVideoCover fetchVideoCover =new FetchVideoCover();
        try {
            fetchVideoCover.fetchCover("G:\\测试视频.mp4","G:\\测试截图.jpg");
            System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
