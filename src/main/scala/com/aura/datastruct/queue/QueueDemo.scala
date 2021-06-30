package com.aura.datastruct.queue

import scala.reflect.ClassTag

/**
  * Author:panghu
  * Date:2021-06-21
  * Description: 自定义队列(先进先出)
  * 要素：
  * 队列长度 initLen
  * 元素数量 count
  * 队首 head
  * 队尾(下一元素插入的位置) tail
  * 入列方法
  * 出列方法
  */
object QueueDemo {
    def main(args: Array[String]): Unit = {
        val que = new MyQueue[Int](5)
        que.enQueue(10)
        que.enQueue(20)
        que.enQueue(30)
        que.enQueue(40)
        que.enQueue(50)
        que.printQueue()
        que.deQueue()
        que.deQueue()
        que.printQueue()
        que.enQueue(1)
        que.printQueue()
    }
}

//ClassTag是泛型上下文对象
class MyQueue[T:ClassTag](initLen:Int) {
    //初始化数组
    val queueArr = new Array[T](initLen)
    //初始元素数量
    var count = 0
    //初始队首位置
    var head = 0
    //初始队尾位置
    var tail = -1

    //判断队列是否已满
    def isFull = count == initLen
    //判断队列是否为空
    def isEmpty = count == 0

    //入队
    def enQueue(elem: T) = {
        if (isFull) throw new RuntimeException("队列已满，无法入队！")
        //队尾后移
        tail += 1
        //总数加1
        count += 1
        //如果队尾=数组长度，那么重置
        if (tail == initLen) tail = 0
        queueArr(tail) = elem
    }

    //出队
    def deQueue() = {
        if (isEmpty) throw new RuntimeException("队列已空，无法出队！")
        val result = queueArr(head)
        //总数-1
        count -= 1
        //队首向后移
        head += 1
        //如果队首=数组长度，那么重置
        if (head == initLen) head = 0
        result
    }

    //遍历
    def printQueue() = {
        var temp = head
        for (i <- 0 until count) {
            print(queueArr(temp) + ",")
            temp += 1
            //如果队首=数组长度，那么重置
            if (temp == initLen) temp = 0
        }
        println()
    }
}