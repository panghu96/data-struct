package com.aura.queue;

import java.util.Scanner;

/**
 * Author:panghu
 * Date:2021-06-28
 * Description: 数组模拟环形队列实现
 */
public class CircleQueueDemo {
    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue(4);
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

class CircleQueue {
    private int maxSize;    //队列长度
    private int front;  //队首，指向第一个元素的位置
    private int rear;   //队尾，指向最后一个元素的下一位置，所以队列会预留一个空间
    private int[] queue;

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        //初始化数组
        queue = new int[this.maxSize];
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //入队
    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法入队！");
        }
        queue[rear] = n;
        //队尾指向最后元素的下一位置，并且成环状(取模)
        rear = (rear + 1) % maxSize;
    }

    //出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法出队！");
        }
        int temp = queue[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    //获取队首元素
    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return queue[front];
    }

    //遍历
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        int size = getSize();
        for (int i = front; i< front + size; i++) {
            //保证数组的索引成环，取模
            System.out.printf("arr[%d]=%d\n",i % maxSize,queue[i % maxSize]);
        }
    }

    //计算队列中元素的数量
    public int getSize() {
        return (rear + maxSize - front) % maxSize;
    }
}