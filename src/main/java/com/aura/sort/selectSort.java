package com.aura.sort;

import java.util.Arrays;

/**
 * Author:panghu
 * Date:2021-07-05
 * Description: 选择排序
 * 假设数组中最小的元素索引为0，用当前元素和后面的元素依次进行比较，如果当前元素>后面的元素，
 * 那么记录这个较小元素的索引和值，继续和后面的元素比较，直至最后，交换两个元素的位置。
 * 下一次循环，最小元素即为记录的值和索引，第一个元素不用再次比较。
 */
public class selectSort {
    public static void main(String[] args) {
        int[] arr = {10, 20, 1, 3, 5, 21, 8};
        System.out.println("交换前元素顺序：" + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    //记录较小值的索引和值
                    minIndex = j;
                    min = arr[j];
                }
            }
            //优化，判断最小值的索引是否发生了变化
            if (i != minIndex) {
                //交换当前元素和较小元素的位置
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        System.out.println("交换后元素顺序：" + Arrays.toString(arr));

    }
}
