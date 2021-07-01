package com.aura.stack;


import java.util.Scanner;

/**
 * Author:panghu
 * Date:2021-07-01
 * Description: 单链表模拟实现栈
 */
public class LinkedStackDemo {
    public static void main(String[] args) {
        //创建stack对象
        LinkedStack stack = new LinkedStack(4);
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
                        System.out.printf("%d出栈\n", pop);
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

class LinkedStack {
    private int maxSize;    //栈最大容量
    private int top = -1;   //栈顶
    //定义头节点
    private Node head = new Node(0);

    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
    }

    //入栈
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈已满！无法入栈！");
            return;
        }
        //定义辅助遍历
        Node temp = head;
        while (true) {
            if (temp.getNext() == null) {//遍历到了最后的节点
                Node pushNode = new Node(num);
                temp.setNext(pushNode);
                //栈顶+1
                top++;
                break;
            }
            //节点后移
            temp = temp.getNext();
        }
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！不可出栈！");
        }
        //定义辅助节点
        Node temp = head;
        //弹出栈顶的数据
        for (int i = 0; i <= top; i++) {
            //将temp指向栈顶的数据
            temp = temp.getNext();
        }
        int value = temp.getNo();
        //栈顶-1
        top--;
        return value;
    }

    //遍历（链表倒序打印）
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！");
        }
        //定义辅助变量
        Node temp = null;
        int topCopy = top;
        while (true) {
            if (topCopy == -1) {
                break;
            }
            temp = head;
            for (int i = 0; i <= topCopy; i++) {
                //将temp指向栈顶的数据
                temp = temp.getNext();
            }
            System.out.printf("stack[%d] = %d\n", topCopy, temp.getNo());
            //topCopy-1
            topCopy--;
        }

    }

    //判断栈是否满
    private boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈是否为空
    private boolean isEmpty() {
        return top == -1;
    }
}

//定义节点
class Node {
    private int no;
    private Node next;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}