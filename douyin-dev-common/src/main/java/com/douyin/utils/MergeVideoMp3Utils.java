package com.douyin.utils;

import com.douyin.statics.EnvironmentStatics;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author manchaoyang
 */
@Component
public class MergeVideoMp3Utils extends FFMPEGUtils{

    public void convertor(String videoInputPath, String mp3InputPath, double seconds,
                          String videoOutputStream) throws IOException {
        List<String> command = convertorCommandCombination(videoInputPath, mp3InputPath, seconds, videoOutputStream);
        executeCommandByFFMPEG(command);
    }

    private List<String> convertorCommandCombination(String videoInputPath, String mp3InputPath, double seconds,
                                                     String videoOutputStream) {
        //		ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
        List<String> command = new ArrayList<>();
        command.add(EnvironmentStatics.FFMPEGEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-i");
        command.add(mp3InputPath);
        command.add("-t");
        command.add(String.valueOf(seconds));
        command.add("-y");
        command.add(videoOutputStream);
        return command;
    }

    public static void main(String[] args) {
        MergeVideoMp3Utils mergeVideoMp3Utils = new MergeVideoMp3Utils();
        try {
            mergeVideoMp3Utils.convertor("G:\\测试视频.mp4",
                    "D:\\CloudMusic\\Alan Walker - Fade.mp3", 9, "G:\\测试合成视频1.mp4");
            System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

