package com.aura.linkedList;

/**
 * Author:panghu
 * Date:2021-06-30
 * Description: 环形单链表解决约瑟夫问题
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.add(5);
        list.list();
    }
}

class CircleSingleLinkedList {
    //创建first节点
    private Boy first = null;

    //添加节点
    public void add(int num) {
        if (num < 1) {
            System.out.println("输入的节点数不规范！");
            return;
        }
        //辅助变量，帮助构建环形链表
        Boy cur = null;
        //循环添加节点
        for (int i = 1; i<= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {//首次添加节点
                first = boy;
                //形成环状
                first.setNext(boy);
                //辅助指针指向当前节点
                cur = first;
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                //辅助指针指向当前节点
                cur = boy;
            }

        }
    }

    //遍历节点
    public void list() {
        if (first == null) {
            System.out.println("链表为空！");
            return;
        }
        //定义辅助变量
        Boy temp = first;
        while (true) {
            System.out.printf("节点%d\n",temp.getNo());
            //节点后移
            temp = temp.getNext();
            if (temp == first) {//遍历完毕
                return;
            }
        }
    }
}


//定义节点类
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}