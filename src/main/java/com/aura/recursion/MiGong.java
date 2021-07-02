package com.aura.recursion;


/**
 * Author:panghu
 * Date:2021-07-02
 * Description: 使用递归，找到出迷宫的路径
 * 迷宫中数字分别代表：
 * 0表示该路径暂未探索
 * 1表示障碍物
 * 2表示通路
 * 3表示此路已探索但是无法通路
 */
public class MiGong {
    public static void main(String[] args) {
        //使用二维数组定义迷宫地图
        int[][] map = new int[8][7];
        //将迷宫上下置为障碍
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //将迷宫左右置为障碍
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置其他障碍
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("迷宫地图为：");
        for (int[] row : map) {
            for (int elem : row) {
                System.out.print(elem + "  ");
            }
            System.out.println();
        }
        //调用方法
        getWay(map, 1, 1);
        System.out.println("探索后的迷宫地图为：");
        for (int[] row : map) {
            for (int elem : row) {
                System.out.print(elem + "  ");
            }
            System.out.println();
        }
    }


    /**
     * 在迷宫中找出路的方法
     *
     * @param map 迷宫地图
     * @param i   初始点的横坐标
     * @param j   初始点的纵坐标
     * @return
     */
    public static boolean getWay(int[][] map, int i, int j) {
        //递归的出口
        if (map[6][5] == 2) {
            return true;
        } else {
            //如果此点还没有探索过
            if (map[i][j] == 0) {
                //先将此点设置为可通过状态
                map[i][j] = 2;
                //寻路规则：下->右->上->左
                if (getWay(map, i + 1, j)) {
                    //此路通返回true
                    return true;
                } else if (getWay(map, i, j + 1)) {
                    return true;
                } else if (getWay(map, i - 1, j)) {
                    return true;
                } else if (getWay(map, i, j - 1)) {
                    return true;
                } else {//四个方向的路都不通
                    //标记此路走过，但是走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {//要么是障碍，要么是走过，走不通
                return false;
            }
        }

    }


}
