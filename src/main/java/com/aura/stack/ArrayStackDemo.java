package com.aura.stack;


import java.util.Scanner;

/**
 * Author:panghu
 * Date:2021-07-01
 * Description: 使用数组模拟实现栈
 * 栈的特点：先进后出
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //创建stack对象
        ArrayStack stack = new ArrayStack(4);
        //显示菜单
        Scanner sc = new Scanner(System.in);
        String key = null;
        boolean loop = true;
        while (loop) {
            System.out.println("====================== 菜单 =======================");
            System.out.println("show:显示栈元素");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("exit:退出程序");
            System.out.println("请输入要进行的操作：");
            key = sc.next();
            try {
                switch (key) {
                    case "show":
                        stack.show();
                        break;
                    case "push":
                        System.out.println("请输入要插入的数据：");
                        int num = sc.nextInt();
                        stack.push(num);
                        break;
                    case "pop":
                        int pop = stack.pop();
                        System.out.printf("%d出栈\n",pop);
                        break;
                    case "exit":
                        sc.close();
                        loop = false;
                        break;
                    default:
                        System.out.println("输入的指令有误，请重新输入！");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        System.out.println("程序退出");
    }
}

class ArrayStack {
    private int maxSize;    //初始长度
    private int[] stack;    //数组代表栈
    private int top = -1;   //栈顶，默认-1

    //入栈 push
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈已满，无法入栈！");
            return;
        }
        top++;
        stack[top] = num;
    }

    //出栈 pop
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法出栈！");
        }
        return stack[top--];
    }

    //遍历栈，从后向前遍历
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    //判断栈空
    private boolean isEmpty() {
        return top == -1;
    }

    //判断栈满
    private boolean isFull() {
        return top == maxSize - 1;
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

}