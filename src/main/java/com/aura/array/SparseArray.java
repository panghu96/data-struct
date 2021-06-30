package com.aura.array;

import java.io.*;

/**
 * Author:panghu
 * Date:2021-06-28
 * Description: 稀疏数组
 * 以数组记录五子棋盘及黑白子的位置
 * <p>
 * 二维数组 => 稀疏数组
 * 1）稀疏数组第一行分别记录：二维数组的行数、列数、非0值的数量
 * 2）稀疏数组其他行分别记录：二维数组非0值的行索引、列索引、具体的值
 * 3）将稀疏数组存储到文件中
 * <p>
 * 稀疏数组 => 二维数组
 * 1）加载稀疏数组文件
 * 2）稀疏数组第一行是二维数组的行和列
 * 3）根据稀疏数组中记录的非0值向二维数组赋值
 */
public class SparseArray {
    public static void main(String[] args) {
        //初始化二维数组
        int[][] chessArr1 = new int[11][11];
        //1代表黑子，2代表白子
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][3] = 2;
        //输出二维数组
        System.out.println("二维数组为：");
        for (int[] row : chessArr1) {
            for (int elem : row) {
                System.out.printf("%d\t", elem);
            }
            System.out.println();
        }

        //记录二维数组中非0元素的数量
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                }
            }
        }
        System.out.println("count=" + count);

        //TODO:二维数组转为稀疏矩阵
        //定义稀疏矩阵
        int[][] sparseArr = new int[count + 1][3];
        //稀疏矩阵第一行第一列：二维矩阵行数
        sparseArr[0][0] = chessArr1.length;
        //稀疏矩阵第一行第二列：二维矩阵列数
        sparseArr[0][1] = chessArr1[0].length;
        //稀疏矩阵第一行第三列：非0元素的数量
        sparseArr[0][2] = count;
        //稀疏矩阵行索引
        int rowIndex = 0;
        //稀疏矩阵其他行和列：二维数组非0值的行索引、列索引、具体的值
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    rowIndex++;
                    sparseArr[rowIndex][0] = i;
                    sparseArr[rowIndex][1] = j;
                    sparseArr[rowIndex][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println();
        System.out.println("稀疏矩阵为：");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //TODO:稀疏矩阵存储到文件
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("D:\\tmp\\sparseArr.data"));
            for (int i = 0; i < sparseArr.length; i++) {
                for (int j = 0; j < sparseArr[i].length; j++) {
                    String res = sparseArr[i][j] + "\t";
                    bw.write(res);
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println();

        //TODO:加载文件中的稀疏矩阵数据
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\tmp\\sparseArr.data"));
            String line = null;
            //读取第一行，声明二维数组
            line = br.readLine();
            String[] split1 = line.split("\t");
            int rowNum = Integer.parseInt(split1[0].trim());
            int colNum = Integer.parseInt(split1[1].trim());
            int[][] chessArr2 = new int[rowNum][colNum];

            //TODO:稀疏矩阵转为二维数组
            while ((line = br.readLine()) != null) {
                String[] split2 = line.split("\t");
                int index = Integer.parseInt(split2[0].trim());
                int col = Integer.parseInt(split2[1].trim());
                int val = Integer.parseInt(split2[2].trim());
                chessArr2[index][col] = val;
            }

            System.out.println("二维数组为：");
            for (int[] row : chessArr2) {
                for (int elem : row) {
                    System.out.printf("%d\t", elem);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        /*int rowNum = sparseArr[0][0];
        int colNum = sparseArr[0][1];
        int[][] chessArr2 = new int[rowNum][colNum];
        //稀疏矩阵第二行记录数据的位置
        for (int i = 1; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
            }
        }
        //输出二维数组
        System.out.println();
        System.out.println("二维数组为：");
        for (int[] row : chessArr2) {
            for (int elem : row) {
                System.out.printf("%d\t", elem);
            }
            System.out.println();
        }*/

    }
}
