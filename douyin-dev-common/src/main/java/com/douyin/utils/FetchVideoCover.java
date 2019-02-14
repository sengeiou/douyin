package com.douyin.utils;

import com.douyin.statics.EnvironmentStatics;
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
public class FetchVideoCover extends FFMPEGUtils {

    public void fetchCover(String videoInputPath, String coverOutputPath) throws IOException {
        List<String> command = fetchCoverCommandCombination(videoInputPath,coverOutputPath);
        executeCommandByFFMPEG(command);
    }

    /**
     *  提取视频中的第一秒的一帧作为封面
     * @param videoInputPath
     * @param coverOutputPath
     * @return
     */
    private List<String> fetchCoverCommandCombination(String videoInputPath, String coverOutputPath){
        //	ffmpeg.exe -ss 00:00:01 -i spring.mp4 -vframes 1 bb.jpg
        List<String> command = new java.util.ArrayList<String>();
        command.add(EnvironmentStatics.FFMPEGEXE);
        command.add("-ss");
        command.add("00:00:01");
        command.add("-y");
        command.add("-i");
        command.add(videoInputPath);
        command.add("-vframes");
        command.add("1");
        command.add(coverOutputPath);
        return command;
    }


    public static void main(String[] args){
        FetchVideoCover fetchVideoCover =new FetchVideoCover();
        try {
            fetchVideoCover.fetchCover("G:\\测试视频.mp4","G:\\测试截图1.jpg");
            System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
