package org.akachi.humanTraining;

import org.akachi.humanTraining.entity.Symbol;
import org.akachi.humanTraining.service.Player;
import org.akachi.humanTraining.service.Trainer;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) throws IOException {
        System.out.println("请选择每组混合测试的音标量最大48最小1:");
        Scanner in = new Scanner(System.in);
        String groupStr = in.nextLine();
        System.out.println();
        System.out.println("请选择要测试第几组最大48/组 最小1:");
        String pageStr = in.nextLine();
        System.out.println();
        System.out.println("请选择是否乱序测试 true 乱序 false 有序");
        String randomStr = in.nextLine();
        System.out.println();
        int group = Integer.valueOf(groupStr);
        int page = Integer.valueOf(pageStr);
        boolean random =Boolean.valueOf(randomStr);
        Trainer trainer = new Trainer(group);
        float score = trainer.randomTestTrainer(page, random);
        System.out.println("这次测试的正确率是:"+score);
    }

}
