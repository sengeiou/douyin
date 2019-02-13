package com.douyin.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Component
public class MergeVideoMp3Utils {

    private String ffmpegExe = "H:\\utils_software\\ffmpeg\\ffmpeg-20181220-16ec62b-win64-static\\bin\\ffmpeg.exe";


    public void convertor(String videoInputPath,String mp3InputPath,double seconds,
                          String videoOutputStream) throws IOException {
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
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;

    }
}
