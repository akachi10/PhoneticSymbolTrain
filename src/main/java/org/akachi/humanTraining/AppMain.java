package org.akachi.humanTraining;

import org.akachi.humanTraining.entity.Symbol;
import org.akachi.humanTraining.service.Player;
import org.akachi.humanTraining.service.Trainer;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        Player.loopSound("sound/007.It's only the fairy tale.wav");
        while (true) {
            System.out.println("请选择每组混合测试的音标量最大48最小1:");
            Scanner in = new Scanner(System.in);
            String groupStr = in.nextLine();
            System.out.println();
            System.out.println("请选择要测试第几组最大48/组 最小1:");
            String pageStr = in.nextLine();
            System.out.println();
            System.out.println("请选择是否乱序测试 true 乱序 false 有序 plaher 播放 write 默写");
            String randomStr = in.nextLine();
            System.out.println();
            float score = 0;
            try {
                boolean random = Boolean.valueOf(randomStr);
                int group = Integer.valueOf(groupStr);
                int page = Integer.valueOf(pageStr);
                Trainer trainer = new Trainer(group);
                if ("t".equals(randomStr) || "true".equals(randomStr)) {
                    randomStr = "true";
                } else if ("player".equals(randomStr) || "p".equals(randomStr)) {
                    System.out.println("开始朗读训练");
                    trainer.playerTrain(page, false);
                    continue;
                } else if ("write".equals(randomStr) || "w".equals(randomStr)) {
                    System.out.println("开始默写训练");
                    trainer.playerTrain(page, true);
                    continue;
                } else {
                    randomStr = "false";
                }
                System.out.println("开始测试训练");
                score = trainer.randomTestTrainer(page, random);
            } catch (Exception e) {
                System.out.println("错误的入参");
            }
            System.out.println("这次测试的正确率是:" + score);
        }
    }
}
