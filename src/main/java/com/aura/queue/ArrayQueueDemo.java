package com.aura.queue;

import java.util.Scanner;

/**
 * Author:panghu
 * Date:2021-06-28
 * Description:使用数组模拟队列（此版本数组不能重复使用）
 * 队列：先进先出
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("========================================================");
            System.out.println("请输入想要进行的操作：");
            System.out.println("s(show):查看队列元素");
            System.out.println("a(add):向队列插入元素");
            System.out.println("g(get):从队列取出元素");
            System.out.println("h(head):查看队首");
            System.out.println("e(exit):退出程序");
            char c = sc.next().charAt(0);
            try {
                switch (c) {

                    case 's':
                        queue.show();
                        break;
                    case 'a':
                        System.out.println("请输入要插入的值：");
                        int num = sc.nextInt();
                        queue.addQueue(num);
                        break;
                    case 'g':
                        int elem = queue.getQueue();
                        System.out.println(elem);
                        break;
                    case 'h':
                        int head = queue.head();
                        System.out.println(head);
                        break;
                    case 'e':
                        flag = false;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("程序退出！");
    }
}

class ArrayQueue {
    private int maxSize;    //队列最大长度
    private int front = -1;  //队首，指向元素的前一个位置
    private int rear = -1;  //队尾，指向元素所在位置
    private int[] queue;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        //初始化数组
        queue = new int[this.maxSize];
    }

    //判断队列是否已满
    public boolean isFull() {
        //如果队尾在队列长度-1的位置，就满了
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        //队首和队尾的位置相同代表队列为空
        return front == rear;
    }

    //入队
    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法入队！");
        }
        //队尾后移
        rear++;
        queue[rear] = n;
    }

    //出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法出队！");
        }
        //队首后移
        front++;
        return queue[front];
    }

    //获取队首元素
    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        //队首后移
        front++;
        return queue[front];
    }

    //遍历
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        for (int elem : queue) {
            System.out.printf("%d\n", elem);
        }
    }
}