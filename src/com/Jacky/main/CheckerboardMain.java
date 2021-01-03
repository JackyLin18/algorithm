package com.Jacky.main;

import java.util.Scanner;

/**
 * @ClassName CheckerboardMain
 * @Author Jacky
 * @Description
 **/
public class CheckerboardMain {
    // 初始时棋盘的大小
    private static int BOARD_SIZE;
    // 使用二维数组模拟棋盘
    private static int[][] board;
    // 全局变量，记录骨牌编号
    private static int number = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入k值(棋盘大小=2^k)以指定棋盘大小：");
        int k = scanner.nextInt();
        int temp = 1;
        for (int i = 0; i < k; i++) {
            temp = 2 * temp;
        }
        BOARD_SIZE = temp;
        System.out.println("k值为" + k + ", 棋盘大小为" + BOARD_SIZE + "*" + BOARD_SIZE
                + "=" + BOARD_SIZE * BOARD_SIZE);
        System.out.print("请输入指定的特殊点所在的行号：");
        int specialRow = scanner.nextInt(k);
        System.out.print("请输入指定的特殊点所在的列号：");
        int specialColumn = scanner.nextInt(k);
        scanner.close();
        // 初始化棋盘
        board = new int[BOARD_SIZE][BOARD_SIZE];
        createBoard(0, 0,
                specialRow - 1, specialColumn - 1,
                BOARD_SIZE);
        System.out.println("划分后的棋盘为：");
        printBoard();
    }

    public static void createBoard(int nowRow, int nowColumn,
                                   int specialRow, int specialColumn,
                                   int size) {
        // 递归出口，size<=1 时退出
        if (size <= 1) {
            return;
        }

        int newNumber = number++;
        // 分割棋盘为四个子棋盘
        int newSize = size / 2;

        // 处理左上角的子棋盘
        // 判断特殊方块是否位于该子棋盘中
        if (specialColumn < nowColumn + newSize && specialRow < nowRow + newSize) {
            // 特殊方块位于该子棋盘
            // 继续递归调用
            createBoard(nowRow, nowColumn, specialRow, specialColumn, newSize);
        } else {
            // 特殊方块不位于该子棋盘
            // 用newNumber号覆盖右下角
            board[nowRow + newSize - 1][nowColumn + newSize - 1] = newNumber;
            // 继续递归调用
            createBoard(nowRow, nowColumn,
                    nowRow + newSize - 1, nowColumn + newSize - 1,
                    newSize);
        }

        // 处理右上角的子棋盘
        // 判断特殊方块是否位于该子棋盘中
        if (specialColumn >= nowColumn + newSize && specialRow < nowRow + newSize) {
            // 特殊方块位于该子棋盘
            // 继续递归调用
            createBoard(nowRow, nowColumn + newSize,
                    specialRow, specialColumn, newSize);
        } else {
            // 特殊方块不位于该子棋盘
            // 用newNumber号覆盖左下角
            board[nowRow + newSize - 1][nowColumn + newSize] = newNumber;
            // 继续递归调用
            createBoard(nowRow, nowColumn + newSize,
                    nowRow + newSize - 1, nowColumn + newSize,
                    newSize);
        }

        // 处理左下角的子棋盘
        // 判断特殊方块是否位于该子棋盘中
        if (specialColumn < nowColumn + newSize && specialRow >= nowRow + newSize) {
            // 特殊方块位于该子棋盘
            // 继续递归调用
            createBoard(nowRow + newSize, nowColumn,
                    specialRow, specialColumn, newSize);
        } else {
            // 特殊方块不位于该子棋盘
            // 用newNumber号覆盖右上角
            board[nowRow + newSize][nowColumn + newSize - 1] = newNumber;
            // 继续递归调用
            createBoard(nowRow + newSize, nowColumn,
                    nowRow + newSize, nowColumn + newSize - 1,
                    newSize);
        }

        // 处理右下角的子棋盘
        // 判断特殊方块是否位于该子棋盘中
        if (specialColumn >= nowColumn + newSize && specialRow >= nowRow + newSize) {
            // 特殊方块位于该子棋盘
            // 继续递归调用
            createBoard(nowRow + newSize, nowColumn + newSize,
                    specialRow, specialColumn, newSize);
        } else {
            // 特殊方块不位于该子棋盘
            // 用newNumber号覆盖左上角
            board[nowRow + newSize][nowColumn + newSize] = newNumber;
            // 继续递归调用
            createBoard(nowRow + newSize, nowColumn + newSize,
                    nowRow + newSize, nowColumn + newSize,
                    newSize);
        }
    }

    public static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int temp = board[i][j];
                if (temp % 10 == 0) {
                    System.out.print("@\t");
                } else if (temp % 10 == 1) {
                    System.out.print("#\t");
                } else if (temp % 10 == 2) {
                    System.out.print("$\t");
                } else if (temp % 10 == 3) {
                    System.out.print("%\t");
                } else if (temp % 10 == 4) {
                    System.out.print("*\t");
                } else if (temp % 10 == 5) {
                    System.out.print("^\t");
                } else if (temp % 10 == 6) {
                    System.out.print("<\t");
                } else if (temp % 10 == 7) {
                    System.out.print("&\t");
                } else if (temp % 10 == 8) {
                    System.out.print(">\t");
                } else if (temp % 10 == 9) {
                    System.out.print("|\t");
                }
            }
            System.out.println();
        }
    }
}
