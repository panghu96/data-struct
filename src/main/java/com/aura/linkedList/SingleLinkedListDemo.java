package com.aura.linkedList;

import java.awt.*;
import java.time.temporal.Temporal;
import java.util.Stack;

/**
 * Author:panghu
 * Date:2021-06-29
 * Description:
 * 链表模拟实现
 * =>链表是以节点的形式进行存储，是链式存储
 * =>每个节点包含data域和next域，next域指向下一个节点
 * =>节点在内存中不一定是连续存储
 * =>链表分为带头节点的链表和不带头节点的链表
 * =>链表的头节点不能动，所以我们需要借助一个辅助变量（指针）来遍历整个链表，增删改查的操作都需要借助辅助变量
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "孙悟空", "孙行者");
        HeroNode hero2 = new HeroNode(2, "猪八戒", "悟能");
        HeroNode hero3 = new HeroNode(3, "沙和尚", "悟净");
        HeroNode hero4 = new HeroNode(4, "唐玄奘", "唐三藏");
        HeroNode hero8 = new HeroNode(8, "牛魔王", "老牛");
        SingleLinkedList linkedList = new SingleLinkedList();
        //TODO:测试按序号排序
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero2);
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero8);
        //TODO:测试删除
        linkedList.delete(2);
        //遍历
        linkedList.list();
        //TODO:获取有效节点个数
        int node = getSize(linkedList);
        System.out.println("单链表中有效节点的个数为：" + node);
        //TODO:测试取倒数第n个节点
        int index = 2;
        HeroNode indexNode = getIndexNode(linkedList, index);
        System.out.printf("倒数第%d个节点为：%s\n", index, indexNode);
        //TODO:链表反转，结构改变
        // reverse(linkedList);
        // System.out.println("反转后的链表为：");
        // linkedList.list();
        //TODO:链表倒序打印，结构不变
        System.out.println("链表倒序打印，结构不变：");
        reverseList(linkedList);

        HeroNode hero5 = new HeroNode(5, "宋江", "及时雨");
        HeroNode hero6 = new HeroNode(6, "吴用", "智多星");
        HeroNode hero7 = new HeroNode(7, "李逵", "黑旋风");

        SingleLinkedList list2 = new SingleLinkedList();
        list2.addByOrder(hero6);
        list2.addByOrder(hero7);
        list2.addByOrder(hero5);

        SingleLinkedList combineList = combine(linkedList, list2);
        System.out.println("合并后的链表为：");
        combineList.list();
    }

    /**
     * 求单链表中有效节点的个数
     * 遍历统计
     *
     * @param linkedList 链表
     * @return 有效节点的个数
     */
    public static int getSize(SingleLinkedList linkedList) {
        //获取头节点
        HeroNode head = linkedList.getHead();
        //定义辅助变量
        HeroNode temp = head;
        int count = 0;
        while (true) {
            if (temp.next == null) {
                break;
            }
            count++;
            //节点后移
            temp = temp.next;
        }
        return count;
    }

    /**
     * 查找单链表中的倒数第k个结点【新浪面试题】
     * 1.遍历单链表，获取单链表的长度size
     * 2.从第一个开始，遍历size-index个，即倒数第k个
     *
     * @param linkedList 链表
     * @param index      倒数第k个
     * @return
     */
    public static HeroNode getIndexNode(SingleLinkedList linkedList, int index) {
        int size = getSize(linkedList);
        HeroNode head = linkedList.getHead();
        if (head.next == null) {//空链表
            return null;
        }
        //判断index是否符合规范
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量，从第一个元素开始
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 单链表的反转【腾讯面试题】
     * 1.创建一个新的单链表头节点
     * 2.使用辅助变量遍历当前链表，依次将当前链表的节点放到新链表头节点的位置
     * ->2.1使用辅助变量记录当前节点的next节点
     * ->2.2当前节点的next指向新链表头节点的next位置
     * ->2.3新链表头节点的next位置指向当前节点
     * ->2.4当前节点后移
     * 3.当前链表的next指向新链表头节点的next位置
     *
     * @param linkedList
     * @return
     */
    public static void reverse(SingleLinkedList linkedList) {
        HeroNode curLinkedHead = linkedList.getHead();
        if (curLinkedHead.next == null || curLinkedHead.next.next == null) {
            //空链表或者只有一个节点的链表，直接返回
            return;
        }
        //创建新的单链表头节点
        HeroNode newLinkHead = new HeroNode(0, "", "");
        //使用辅助变量遍历
        HeroNode cur = curLinkedHead.next;
        HeroNode next = null;   //指向当前节点的下一节点
        while (cur != null) {
            //记录下一节点
            next = cur.next;
            //当前节点的next指向新链表头节点的next
            cur.next = newLinkHead.next;
            //新链表头节点指向当前节点
            newLinkHead.next = cur;
            //节点后移
            cur = next;
        }
        //当前链表头节点的next指向新链表头节点的next位置
        curLinkedHead.next = newLinkHead.next;
    }

    /**
     * 从尾到头打印单链表 【百度面试题】
     * 利用栈先进后出的特点，先将链表中的元素依次入栈，再依次出栈
     *
     * @param linkedList
     */
    public static void reverseList(SingleLinkedList linkedList) {
        HeroNode head = linkedList.getHead();
        //创建辅助变量
        HeroNode temp = head.next;
        //创建栈
        Stack<HeroNode> stack = new Stack<>();
        while (true) {
            if (temp == null) {//遍历到最后一个节点
                break;
            }
            //入栈
            stack.add(temp);
            //节点后移
            temp = temp.next;
        }
        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * TODO:
     * 合并两个有序的单链表，合并之后的链表依然有序(待完成)
     *
     * @param list1
     * @param list2
     * @return
     */
    public static SingleLinkedList combine(SingleLinkedList list1, SingleLinkedList list2) {
        //创建一个新的链表头节点
        HeroNode newHead = new HeroNode(0, "", "");
        //合并两个链表（不排序）
        HeroNode list1Head = list1.getHead();
        HeroNode list2Head = list2.getHead();
        HeroNode temp1 = list1Head;
        if (list1Head.next == null) {
            return list2;
        }
        if (list2Head.next == null) {
            return list1;
        }

        while (true) {
            if (temp1.next == null) {
                //连接
                temp1.next = list2Head.next;
                break;
            }
            //节点后移
            temp1 = temp1.next;
        }


        return list1;
    }
}

class SingleLinkedList {
    //定义头节点，头节点不动，不存储数据
    private HeroNode head = new HeroNode(0, "", "");

    //获取头节点
    public HeroNode getHead() {
        return head;
    }

    //直接添加数据，不考虑编号
    public void add(HeroNode hero) {
        //1.头节点不能动，利用辅助变量（指针），遍历链表，找到next为空的节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {//找到next为空的节点
                //2.将为空的next赋值为要添加的元素
                temp.next = hero;
                break;
            }
            //节点后移
            temp = temp.next;
        }
    }

    //按编号排名添加数据，如果编号有重复，不能添加
    public void addByOrder(HeroNode hero) {
        //头节点不能移动，借助辅助变量遍历链表
        HeroNode temp = head;
        //标记是否可以添加
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//遍历完毕
                flag = true;
                break;
            }
            if (temp.next.no > hero.no) {
                //插入到当前位置
                flag = true;
                break;
            }
            if (temp.next.no == hero.no) {
                System.out.println("该编号已存在，不可插入！");
                break;
            }
            //节点向后移
            temp = temp.next;
        }
        if (flag) {
            //当前节点的next设为要插入的数据
            hero.next = temp.next;
            temp.next = hero;
        }
    }

    //修改链表
    public void update(HeroNode hero) {
        //借助辅助变量，对链表进行遍历
        HeroNode temp = head;
        //标记是否可以修改
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//遍历到了最后
                break;
            }
            if (temp.next.no == hero.no) {
                flag = true;
                break;
            }
            //节点后移
            temp = temp.next;
        }
        if (flag) {
            temp.next.name = hero.name;
            temp.next.nickName = hero.nickName;
        } else {
            System.out.println("数据不存在，不可修改！");
        }
    }

    //删除链表
    public void delete(int n) {
        //借助辅助变量，对链表进行遍历
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//链表遍历完毕
                break;
            }
            if (temp.next.no == n) {
                flag = true;
                break;
            }
            //节点后移
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("数据不存在！");
        }
    }

    //遍历链表
    public void list() {
        //头部不保存数据
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.printf("%s\n", temp);
            //节点后移
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;    //指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}