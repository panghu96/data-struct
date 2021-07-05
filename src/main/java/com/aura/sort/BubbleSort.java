package com.aura.sort;

import java.util.Arrays;

/**
 * Author:panghu
 * Date:2021-07-03
 * Description: 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};
        int temp = 0;
        //优化
        boolean flag = false;
        for (int i = 0;i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 -i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag) {//元素没有发生交换，代表已经排序好
                break;
            } else {
                //重置flag
                flag = false;
            }
        }

        System.out.println("排序后的数组为："+ Arrays.toString(arr));
    }
}
