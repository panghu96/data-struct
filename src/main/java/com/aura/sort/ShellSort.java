package com.aura.sort;

import java.util.Arrays;

/**
 * Author:panghu
 * Date:2021-07-06
 * Description: 希尔排序，对插入排序的改进，分为交换式和移动式。
 * 原理：
 * 对元素脚标按照一定的增量分组，对每组使用插入排序，随着增量逐渐减少，每组的元素助教增多，当增量减少到1时，
 * 算法即可结束
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    //移动法（移位法）
    public static void shellSort2(int[] arr) {
        //增量递减
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从步长处开始向后遍历
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertVal = arr[i];
                //如果后面的值小于前面的值
                if (insertVal < arr[insertIndex - gap]) {
                    while (insertIndex - gap >= 0 && insertVal < arr[insertIndex - gap]) {
                        //大的值后移
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -= gap;
                    }
                    //退出循环代表找到了插入的位置
                    arr[insertIndex] = insertVal;
                }
            }
        }
    }

    //交换法
    public static void shellSort(int[] arr) {
        //利用循环实现
        int temp = 0;
        //增量递减
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历每组中的元素，步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果组内前面的元素比后面的元素大，交换位置
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }


        /*
        //第一轮排序，10个元素分为5组
        for (int i = 5; i < arr.length; i++) {
            //取出每组中的元素，步长5，每组2个元素
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果组内前面的元素大于后面的元素，那么交换位置
                if (arr[j] > arr[j + 5]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一轮排序后：" + Arrays.toString(arr));

        //第二轮排序，10个元素分为5/2=2组
        for (int i = 2; i < arr.length; i++) {
            //取出每组中的元素，步长为2，每组5个元素
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果组内前面的元素大于后面的元素，那么交换位置
                if (arr[j] > arr[j + 2]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二轮排序后：" + Arrays.toString(arr));

        //第三轮排序，10个元素分为2/2=1组
        for (int i = 1; i < arr.length; i++) {
            //每组10个元素，步长为1
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果组内前面的元素大于后面的元素，那么交换位置
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第三轮排序后：" + Arrays.toString(arr));
        */
    }

}
