package com.aura.recursion;

import java.util.Map;

/**
 * Author:panghu
 * Date:2021-07-02
 * Description: 使用递归回溯解决八皇后问题
 */
public class Queue8 {
    static int count = 0;
    int max = 8; //皇后数量
    //创建数组，存储每行皇后所在位置
    //如：arr = {0 , 4, 7, 5, 2, 6, 1, 3}。其中arr[1] = 4，即第2个皇后在第2行第5列
    int[] arr = new int[max];

    public static void main(String[] args) {
        new Queue8().check(0);
        System.out.printf("共有%d种解法\n",count);
    }

    //向棋盘放置皇后，n从0开始
    public void check(int n) {
        //递归出口
        if (n == max) {
            count++;
            print();
            return;
        }

        //开始放入第n个皇后
        for (int i = 0; i < max; i++) {
            //先将当前皇后放在第一列
            arr[n] = i;
            //判断是否可以放置
            if (judge(n)) {
                //可以放置，就放下一个皇后
                check(n + 1);
            }
            //如果不能放置，就进入下一个循环，即放到下一列
        }
    }

    //判断此位置是否可放置，n表示第n个皇后
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //数组的存储规则：索引 = 皇后序号-1，索引 = 行-1，值 = 列-1
            //和其他皇后在同一列或者在对角线
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    //打印数组中皇后的位置
    public void print() {
        for (int i = 0; i < max; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
