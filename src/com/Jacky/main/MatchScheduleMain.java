package com.Jacky.main;

import java.util.Scanner;

/**
 * @ClassName MatchScheduleMain
 * @Author Jacky
 * @Description
 **/
public class MatchScheduleMain {
    // 初始时参赛选手人数
    private static int PLAYERS_NUMBER;
    // 使用二维数组模拟赛程表
    private static int[][] matchSchedule;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入k值(参赛人数=2^k)以指定参赛人数：");
        int k = scanner.nextInt();
        int temp = 1;
        for (int i = 0; i < k; i++) {
            temp = 2 * temp;
        }
        PLAYERS_NUMBER = temp;
        System.out.println("k值为" + k + ", 参赛人数为" + PLAYERS_NUMBER);
        // 初始化赛程表
        matchSchedule = new int[PLAYERS_NUMBER + 1][PLAYERS_NUMBER + 1];
        createMatchSchedule(k);
        printMatchSchedule();
    }

    public static void createMatchSchedule(int k) {
        int numbers = PLAYERS_NUMBER;
        // 初始化选手1的赛程
        for (int i = 1; i <= numbers; i++) {
            matchSchedule[1][i] = i;
        }
        int m = 1;
        // s指对称赋值的总循环次数，即分成几大步进行制作日程表
        for (int s = 1; s <= k; s++) {
            numbers = numbers / 2;
            // t指明内部对称赋值的循环次数
            for (int t = 1; t <= numbers; t++)
                for (int i = m + 1; i <= 2 * m; i++)
                    for (int j = m + 1; j <= 2 * m; j++) {
                        int temp = j + (t - 1) * m * 2;
                        // 右上角等于左上角的值
                        matchSchedule[i][temp] = matchSchedule[i - m][temp - m];
                        // 左下角等于右上角的值
                        matchSchedule[i][temp - m] = matchSchedule[i - m][temp];
                    }
            m *= 2;
        }
    }

    public static void printMatchSchedule() {
        System.out.print("选手\\赛程日\t");
        for (int i = 1; i < PLAYERS_NUMBER; i++) {
            System.out.print("\t第" + i + "天\t");
        }
        System.out.println();
        for (int i = 1; i <= PLAYERS_NUMBER; i++) {
            for (int j = 1; j <= PLAYERS_NUMBER; j++) {
                if (j == 1) {
                    if (i > 9) {
                        System.out.print("选手" + i + "号");
                    } else {
                        System.out.print("选手" + i + "号\t");
                    }
                } else {
                    if (matchSchedule[i][j] < 10) {
                        System.out.print("\t\t" + matchSchedule[i][j] + "号\t");
                    } else {
                        System.out.print("\t    " + matchSchedule[i][j] + "号");
                    }
                }
            }
            System.out.println();
        }
    }
}
