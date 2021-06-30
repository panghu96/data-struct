package com.aura.linkedList;

/**
 * Author:panghu
 * Date:2021-06-30
 * Description: 双向链表
 * 双向链表可以双向查找，既可以向前查找，也可以向后查找，单链表只能向后查找。
 * 双向链表可以自我删除，单链表的删除需要借助辅助节点
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleNode node1 = new DoubleNode(1, "孙悟空", "孙行者");
        DoubleNode node2 = new DoubleNode(2, "猪八戒", "悟能");
        DoubleNode node3 = new DoubleNode(3, "沙僧", "悟净");
        DoubleNode node4 = new DoubleNode(4, "唐三藏", "唐僧");
        DoubleLinkedList linkedList = new DoubleLinkedList();
        //TODO:添加节点
        linkedList.add(node1);
        linkedList.add(node3);
        linkedList.add(node2);
        linkedList.add(node4);
        //遍历
        linkedList.list();
        //TODO:修改节点
        DoubleNode newNode3 = new DoubleNode(3, "沙和尚", "老沙");
        // DoubleNode newNode3 = new DoubleNode(4, "沙和尚", "老沙");
        linkedList.update(newNode3);
        System.out.println("修改后的节点为：");
        linkedList.list();
        //TODO:删除节点
        linkedList.delete(4);
        System.out.println("删除后的节点为：");
        linkedList.list();
        //TODO:排序插入
        DoubleLinkedList linkedList1 = new DoubleLinkedList();
        linkedList1.addByOrder(node1);
        linkedList1.addByOrder(node3);
        linkedList1.addByOrder(node2);
        linkedList1.addByOrder(node4);
        System.out.println("排序后的节点为：");
        linkedList1.list();
    }
}

class DoubleLinkedList {
    //定义头节点
    private DoubleNode head = new DoubleNode(0, "", "");

    //获取头节点
    public DoubleNode head() {
        return head;
    }

    //TODO:按照编号顺序添加
    public void addByOrder(DoubleNode node) {
        DoubleNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                flag = true;
                break;
            }
            if (temp.next.no > node.no) {
                flag = true;
                break;
            }
            //节点后移
            temp = temp.next;

        }

        if (flag) {
            //定义变量保存当前节点的下一节点
            DoubleNode next = temp.next;
            temp.next = node;
            node.pre = temp;
            node.next = next;
            if (next != null) {
                next.pre = node;
            }

        } else {
            System.out.println("编号已存在，无法插入！");
        }
    }


    /**
     * 删除节点
     * 双向链表可以自身删除
     * 1.找到想要删除的节点temp
     * 2.temp前一节点的next指向temp下一节点，即temp.pre.next=temp.next
     * 3.temp下一节点的pre指向temp的前一节点，即temp.next.pre=temp.pre
     *
     * @param n
     */
    public void delete(int n) {
        DoubleNode temp = head.next;
        //标记是否可以删除
        boolean flag = false;
        if (temp == null) {
            System.out.println("链表为空，不可删除节点！");
            return;
        }
        while (true) {
            if (temp == null) {//遍历到了最后
                break;
            }
            if (temp.no == n) {
                flag = true;
                break;
            }
            //节点后移
            temp = temp.next;
        }

        if (flag) {
            temp.pre.next = temp.next;
            //如果temp是最后一个节点，会出现空指针问题
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("没有匹配项！无法删除！");
        }
    }

    /**
     * 修改双链表
     *
     * @param node
     */
    public void update(DoubleNode node) {
        DoubleNode temp = head;
        //标记是否可以修改
        boolean flag = false;
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        while (true) {
            if (temp.next == null) {
                //没有匹配项
                break;
            }
            if (temp.next.no == node.no) {//找到匹配项
                flag = true;
                break;
            }
            //节点后移
            temp = temp.next;
        }
        if (flag) {
            temp.next.name = node.name;
            temp.next.nickName = node.nickName;
        } else {
            System.out.println("没有匹配项！无法修改！");
        }
    }

    /**
     * 在双向链表尾部插入数据
     *
     * @param node
     */
    public void add(DoubleNode node) {
        DoubleNode temp = head;
        while (true) {
            if (temp.next == null) {//遍历到了最后
                temp.next = node;
                node.pre = temp;
                break;
            }
            //节点后移
            temp = temp.next;
        }
    }

    /**
     * 遍历双链表
     */
    public void list() {
        DoubleNode temp = head.next;
        if (temp == null) {
            System.out.println("链表为空！");
            return;
        }
        while (true) {
            if (temp == null) {//遍历到了最后
                break;
            }
            System.out.println(temp);
            //节点后移
            temp = temp.next;
        }
    }
}

class DoubleNode {
    public int no;
    public String name;
    public String nickName;
    public DoubleNode pre;  //指向前一个节点
    public DoubleNode next; //指向后一个节点

    public DoubleNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}