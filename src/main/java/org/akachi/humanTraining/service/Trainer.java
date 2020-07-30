package org.akachi.humanTraining.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.akachi.humanTraining.entity.Symbol;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class Trainer {
    private int groupSize;

    public Trainer(int groupSize) {
        if (groupSize > 48) {
            groupSize = 48;
        }
        this.groupSize = groupSize;
    }

    private Player player = new Player();

    /**
     * 获得所有音标
     *
     * @return
     * @throws IOException
     */
    public List<Symbol> getSymbols() throws IOException {
        File file = new File("source/phoneticTranscription.json");
        String content = FileUtils.readFileToString(file, "UTF-8");
        Gson gson = new Gson();
        Type symbolListtype = new TypeToken<ArrayList<Symbol>>() {
        }.getType();
        List<Symbol> listSymbol = gson.fromJson(content, symbolListtype);
        return listSymbol;
    }

    /**
     * 测试训练
     *
     * @param page   训练第几页
     * @param random 是否随机训练
     * @return
     * @throws IOException
     */
    public float randomTestTrainer(int page, boolean random) throws IOException {
        if (page > 48 / groupSize) {
            page = 48 / groupSize;
        } else if (page < 1) {
            page = 1;
        }
        int endIndex = page * groupSize;
        int startIndex = endIndex - groupSize;
        List<Symbol> symbols = this.getSymbols();
        List<Symbol> samples = symbols.subList(startIndex, endIndex);//获得部分集合

        int count = 0;
        int rCount = 0;
        boolean isRight = true;
        Map<Integer, Symbol> theSymbolMap = null;
        int i = -1;
        while (true) {
            if (isRight) {
                theSymbolMap = printMenu(samples, random);
            }
            printSymbol(theSymbolMap);
            count++;
            if (isRight) {
                i = new Double((Math.random() * groupSize) + 1).intValue();//随机抽取
            }
            Symbol symbol = theSymbolMap.get(i);
            player.playerSound(symbol.getFilePath());//播放声音
            System.out.println("要选择新测试请输入end");
            System.out.print("请在听到语音后选择:");
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            if ("end".equals(s) || "quit".equals(s) || "q".equals(s) || "e".equals(s) || "exit".equals(s)) {
                break;
            } else if ("".equals(s)) {
                break;
            }
            System.out.println();
            try {
                int choose = Integer.parseInt(s);
                if (choose <= 0 || choose > groupSize) {
                    System.out.println("输入超出范围请重新输入");
                    count--;

                } else if (choose == i) {
                    System.out.println("正确！");
                    isRight=true;
                    rCount++;
                } else {
                    System.out.println("错误！");
                    isRight=false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

        }
        return (rCount + 0f) / count;
    }

    /**
     * 排序并打印
     *
     * @param symbolListtype
     * @param random
     * @return
     */
    public Map<Integer, Symbol> printMenu(List<Symbol> symbolListtype, boolean random) {
        if (random) {//乱序
            Collections.shuffle(symbolListtype);
            Collections.shuffle(symbolListtype);
            Collections.shuffle(symbolListtype);
            Collections.shuffle(symbolListtype);
        }
        Map<Integer, Symbol> symbolMap = new HashMap<Integer, Symbol>();
        int i = 0;
        for (Symbol symbol : symbolListtype) {
            i++;
            symbolMap.put(i, symbol);
        }
        return symbolMap;
    }

    /**
     * 打印选项
     *
     * @param symbolMap
     */
    private void printSymbol(Map<Integer, Symbol> symbolMap) {
        for (int i = 0; i < symbolMap.size(); i++) {
            if (i % 10 == 0) {
                System.out.println();
            }
            System.out.print("(" + (i + 1) + ")" + symbolMap.get(i + 1).getMark());
        }
        System.out.println();
    }
}