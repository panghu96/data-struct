package com.aura.stack;

/**
 * Author:panghu
 * Date:2021-07-01
 * Description: 利用栈实现计算器（中缀表达式）
 * 1.创建数字栈和符号栈，前者存放表达式中的数字，后者存放表达式中的运算符
 * 2.定义辅助变量，从表达式的头依次扫描到尾
 * 3.判断扫描到的字符类型，如果是数字，直接入数字栈；如果是符号，判断符号栈中有没有符号
 * == 3.1 如果没有符号，符号直接入栈
 * == 3.2 如果有符号，比较当前符号和栈中栈顶符号的优先级。如果当前符号的优先级小于等于符号栈顶的符号，
 * 在数字栈中pop出两个数字，在符号栈中pop出栈顶符号，做计算，并将结果保存到数字栈中。如果当前符号的
 * 优先级大于栈顶符号，直接入栈。
 * 4.依次pop数字栈和符号栈，做计算，计算结果存到数字栈，直到符号栈为空，此时数字栈中的数字即为最终结果。
 */
public class Caculator {
    public static void main(String[] args) {
        String expres = "300+6/2-1";
        //定义数字栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 opeStack = new ArrayStack2(10);
        //定义辅助变量
        int index = 0; //用于扫描表达式
        int num1 = 0;
        int num2 = 0;
        int ope = 0;
        int res = 0;
        char ch = ' '; //用于记录当前扫描到的字符
        String keepNum = ""; //用于记录多位数
        //开始扫描
        while (true) {
            ch = expres.substring(index, index + 1).charAt(0);
            //判断当前字符的类型
            if (opeStack.isOperator(ch)) {//是运算符
                //判断符号栈中有没有元素
                if (!opeStack.isEmpty()) {//有元素
                    //判断当前运算符和栈中的优先级
                    //如果优先级小于等于栈中，取出数字栈中的栈顶数字和符号栈中的符号做计算
                    int curLevel = opeStack.opeLevel(ch);
                    int stackLevel = opeStack.opeLevel(opeStack.peek());
                    if (curLevel <= stackLevel) {
                        //取出栈中的数字和符号运算
                        num1 = numStack.pop();//后面扫描到的数字
                        num2 = numStack.pop();//前面扫描到的数字
                        ope = opeStack.pop();
                        res = numStack.caculate(num1, num2, ope);
                        //计算结果保存到数字栈
                        numStack.push(res);
                        //当前的运算符入符号栈
                        opeStack.push(ch);
                    } else {//优先级大的运算符入栈
                        opeStack.push(ch);
                    }
                } else {//没有元素，入符号栈
                    opeStack.push(ch);
                }
            } else {//是数字，入数字栈
                //注意次数的ch是char类型，需要转为int类型，即 '1'->1
                //numStack.push(ch - 48);

                //如果是数字，需要判断是否是多位数
                keepNum += ch;
                //判断扫描位置的下一位置是否为数字，同时需要判断当前位置是否为表达式最后一位
                if (index == expres.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //如果下一位是运算符，就可以入数字栈
                    if (opeStack.isOperator(expres.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //一定要将keepNum清空，否则持续累加
                        keepNum = "";
                    }
                }

            }
            index++;
            if (index == expres.length()) {//扫描到了最后
                break;
            }
        }

        //依次取出数字栈和符号栈中的元素，做计算
        while (true) {
            //如果符号栈为空，计算结束
            if (opeStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            ope = opeStack.pop();
            res = numStack.caculate(num1, num2, ope);
            //结果存入数字栈
            numStack.push(res);
        }

        System.out.printf("%s = %d\n", expres, res);
    }
}

class ArrayStack2 {
    private int maxSize;    //初始长度
    private int[] stack;    //数组代表栈
    private int top = -1;   //栈顶，默认-1

    //判断字符类型    int类型和char类型之间可以相互比较
    public boolean isOperator(int ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    //获取栈顶的元素，非出栈
    public int peek() {
        return stack[top];
    }

    //运算符优先级 返回的数字越大，优先级越高
    public int opeLevel(int ch) {
        if (ch == '*' || ch == '/') {
            return 1;
        } else if (ch == '+' || ch == '-') {
            return 0;
        } else {//假设只有加减乘除运算
            return -1;
        }
    }

    //计算
    public int caculate(int num1, int num2, int ope) {
        int res = 0;
        switch (ope) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                System.out.println("运算符不规范！");
                break;
        }
        return res;
    }

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
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

}