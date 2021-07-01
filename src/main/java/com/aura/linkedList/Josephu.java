package com.aura.linkedList;

/**
 * Author:panghu
 * Date:2021-06-30
 * Description: 环形单链表解决约瑟夫问题
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.add(115);
        list.list();
        list.popCircle(10,20, 115);
    }
}

class CircleSingleLinkedList {
    //创建first节点
    private Boy first = null;

    //节点出圈
    /*
    需要借助first节点和helper节点，每次数m，first和helper就向前移动m-1个位置，此时，first指向的节点即为
    要出圈的节点。
    1.定义辅助变量helper，初始位置指向最后的节点。
    2.指定开始报数的节点编号startNum，first和helper同时移动startNum-1个位置，first恰好指向开始报数的节点
    3.指定出圈的数字countNum，first和helper同时移动countNum-1，first指向的位置就是要出圈的节点
    4.节点出圈，直到圈中只剩一个节点（helper==first）
        first = first.next;
        helper.next = first;
     */

    /**
     *
     * @param startNum  开始位置
     * @param countNum  出圈的数字
     * @param nodeNum   节点数量
     */
    public void popCircle(int startNum, int countNum, int nodeNum) {
        //判断输入是否合规
        if (startNum <= 0 || countNum <=0 || countNum > nodeNum || nodeNum <= 0) {
            System.out.println("输入的参数不规范！");
        }
        //定义辅助变量
        Boy helper = first;
        //辅助变量指向最后一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            //节点后移
            helper = helper.getNext();
        }
        //first和辅助节点同时向后移动
        for (int i = 0; i < startNum - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始报数
        while (true) {
            //循环停止规则
            if (helper == first) {
                break;
            }
            //first和辅助节点同时向后移动
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时first指向的节点就是出圈的节点
            System.out.printf("节点%d出圈\n",first.getNo());
            //节点出圈，重新定向指针
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("圈中最后的节点为"+helper.getNo());
    }

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