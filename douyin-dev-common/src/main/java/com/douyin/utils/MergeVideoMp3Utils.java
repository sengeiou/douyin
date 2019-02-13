package com.douyin.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author manchaoyang
 */
@Component
public class MergeVideoMp3Utils {

    private String ffmpegExe = "H:\\utils_software\\ffmpeg\\ffmpeg-20181220-16ec62b-win64-static\\bin\\ffmpeg.exe";


    public void convertor(String videoInputPath, String mp3InputPath, double seconds,
                          String videoOutputStream) throws IOException {
        //		ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
        List<String> command = new ArrayList<>();
        command.add(ffmpegExe);

        command.add("-i");
        command.add(videoInputPath);

        command.add("-i");
        command.add(mp3InputPath);


        command.add("-t");
        command.add(String.valueOf(seconds));


        command.add("-y");
        command.add(videoOutputStream);

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

    public static void main(String[] args){
        MergeVideoMp3Utils mergeVideoMp3Utils =new MergeVideoMp3Utils();
        try {
            mergeVideoMp3Utils.convertor("G:\\测试视频.mp4",
                    "D:\\CloudMusic\\Alan Walker - Fade.mp3",9,"G:\\测试合成视频.mp4");
            System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

