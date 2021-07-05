package com.aura.sort;

import java.util.Arrays;

/**
 * Author:panghu
 * Date:2021-07-05
 * Description: 插入排序（从小到大）
 * 将一个数组分为有序列表和无序列表，有序列表最初只有arr[0]这个元素，无序列表是除了arr[0]之外的元素
 * 无序列表中的元素依次和有序列表中的元素从后向前依次进行比较，如果当前元素<有序列表中的元素，就将
 * 当前元素插入到有序列表中这个元素的前方。
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {10, 2, 3, 5, 1};
        insertMethod(arr);
        System.out.println("排序后："+ Arrays.toString(arr));
    }

    public static void insertMethod(int[] arr) {
        //利用循环，找到待插入的位置
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            //条件1：索引不能越界
            //条件2：当前元素小于前一个元素
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                //大的值后移
                arr[insertIndex + 1] = arr[insertIndex];
                //索引前移
                insertIndex--;
            }
            //出了循环，代表找到了插入位置
            arr[insertIndex + 1] = insertVal;
        }

        /*
        //第一轮
        //定义待插入的值
        int insertVal = arr[1];
        //要比较的有序列表元素索引，从待插入值的前一个元素开始
        int insertIndex = 1 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            //大的值向后移动
            arr[insertIndex + 1] = arr[insertIndex];
            //有序列表前移
            insertIndex--;
        }
        //退出循环时，代表插入的位置已经找到
        //插入，位置是inserIndex+1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第一轮排序后："+ Arrays.toString(arr));

        //第二轮
        insertVal = arr[2];
        insertIndex = 2 - 1;
        while (insertIndex >=0 && insertVal < arr[insertIndex]) {
            //大的值向后移动
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }
        //出了循环代表找到了插入位置
        arr[insertIndex + 1] = insertVal;
        System.out.println("第二轮排序后："+Arrays.toString(arr));

        //第三轮
        insertVal = arr[3];
        insertIndex = 3 - 1;
        while (insertIndex >=0 && insertVal < arr[insertIndex]) {
            //大的值向后移动
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }
        //出了循环代表找到了插入位置
        arr[insertIndex + 1] = insertVal;
        System.out.println("第三轮排序后："+Arrays.toString(arr));

        //第四轮
        insertVal = arr[4];
        insertIndex = 4 - 1;
        while (insertIndex >=0 && insertVal < arr[insertIndex]) {
            //大的值向后移动
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }
        //出了循环代表找到了插入位置
        arr[insertIndex + 1] = insertVal;
        System.out.println("第四轮排序后："+Arrays.toString(arr));
        */
    }
}

