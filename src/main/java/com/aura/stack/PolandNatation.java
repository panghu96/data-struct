package com.aura.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author:panghu
 * Date:2021-07-02
 * Description: 逆波兰计算器
 * 逆波兰即后缀表达式
 * 从左向右扫描表达式，遇到运算符就将栈顶和次顶数字取出，做运算，将计算结果再存储到栈中，直到最后
 */
public class PolandNatation {
    public static void main(String[] args) {
        //定义逆波兰表达式
        /*String suffixExpression = "3 4 + 5 * 6 -";  //(3+4)×5-6
        //拆分元素加到集合中
        List<String> list = getList(suffixExpression);
        //System.out.println(list);

        int res = calculate(list);
        System.out.printf("%s = %d\n", suffixExpression, res);*/

        //定义中缀表达式
        // String infixExpression = "( 3 + 4 ) * 5 - 6";
        String infixExpression = "1 + ( ( 20 + 3 ) * 4 ) - 5";
        //拆分中缀表达式存储到List中
        List<String> infixList = getList(infixExpression);
        System.out.println("infixList=" + infixList);
        //中缀表达式转为后缀表达式
        List<String> suffixList = toSuffixExp(infixList);
        System.out.println("suffixList=" + suffixList);
        //计算
        int res = calculate(suffixList);
        System.out.printf("%s = %d",infixExpression, res);
    }

    /**
     * 中缀表达式转后缀表达式
     * 1.定义两个栈，s1存放运算符，s2存储数字和运算符（只入栈不出栈，最后还要倒序输出，可以使用ArrayList）
     * 2.定义辅助变量，扫描表达式字符。如果是数字，直接入s2栈。如果是运算符：
     * ==> 2.1如果s1为空或者为(，直接入栈。如果s1非空，比较当前运算符和栈中运算符的优先级。
     * ==> 2.2如果当前运算符优先级高，将当前运算符压入s1.
     * ==> 2.3如果当前运算符优先级低，将s1栈顶运算符弹出，放入s2，直到s1栈顶元素优先级小于当前运算符为止
     * 3.如果当前字符是(，直接加入s1.
     * 4.如果当前字符是)，弹出s1中运算符，加入到s2，直到遇到第一个(为止。
     * 5.将s1中的运算符依次弹出加入到s2中，s2保存的结果即为后缀表达式。
     * @param infixList
     * @return
     */
    public static List<String> toSuffixExp(List<String> infixList) {
        //定义两个栈，s1存放运算符，s2存放数字和运算符
        Stack<String> s1 = new Stack<String>();
        //s2只入栈，不出栈，可以使用ArrayList代替
        List<String> s2 = new ArrayList<String>();
        //定义辅助变量，扫描表达式
        int index = 0;
        while (true) {
            if (index == infixList.size()) {
                break;
            }
            //获取当前字符
            String ch = infixList.get(index);
            if (ch.matches("\\d+")) {//匹配数字
                //加入到s2
                s2.add(ch);
            } else if ("(".equals(ch)) {
                s1.push(ch);
            } else if (")".equals(ch)) {
                //弹出s1中的运算符并加入到s2，直到遇到第一个(为止
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                //消除s1中的(
                s1.pop();
            } else {//匹配运算符
                //如果s1为空或者s1的栈顶为(，那么直接入栈
                if (s1.isEmpty() || "(".equals(s1.peek())) {
                    s1.push(ch);
                } else {//比较运算符的优先级
                    //当前运算符优先级低，弹出s1中优先级高的运算符，存入s2，
                    //直到s1中的运算符为空或者s1中运算符的优先级低于当前运算符为止。
                    while (s1.size() != 0 && Operators.getLevel(ch) <= Operators.getLevel(s1.peek())) {
                        s2.add(s1.pop());
                    }
                    //当前运算符优先级高，直接压入s1
                    s1.push(ch);
                }
            }
            //索引后移
            index++;
        }

        //将s1中其他运算符依次弹出放入s2
        if (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 根据后缀(逆波兰)表达式计算结果
     *
     * @param suffixExpressionList
     * @return
     */
    public static int calculate(List<String> suffixExpressionList) {
        //声明栈，存放数字
        Stack<String> stack = new Stack<>();
        int res = 0;
        for (String elem : suffixExpressionList) {
            //判断元素类型
            if (elem.matches("\\d+")) {//正则表达式匹配一个或多个数字
                stack.push(elem);
            } else {//取出栈顶和次顶的数据进行运算，并将结果存到栈
                int num1 = Integer.parseInt(stack.pop()); //表达式靠后的数字
                int num2 = Integer.parseInt(stack.pop()); //表达式考前的数字
                switch (elem) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        System.out.println("运算符有误！");
                        break;
                }
                //结果入栈
                stack.push(res + "");
            }
        }
        return res;
    }

    /**
     * 将表达式拆分存储到List
     *
     * @param expression
     * @return
     */
    public static List<String> getList(String expression) {
        List<String> list = new ArrayList<>();
        String[] split = expression.split(" ");
        for (String elem : split) {
            list.add(elem);
        }
        return list;
    }

}

//定义类，声明运算符的优先级
class Operators {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getLevel(String ch) {
        int level = 0;
        switch (ch) {
            case "+":
                level = ADD;
                break;
            case "-":
                level = SUB;
                break;
            case "*":
                level = MUL;
                break;
            case "/":
                level = DIV;
                break;
            default:
                System.out.println("运算符输入有误！");
                break;
        }
        return level;
    }
}