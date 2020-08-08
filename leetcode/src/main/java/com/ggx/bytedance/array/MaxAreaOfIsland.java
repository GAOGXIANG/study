package com.ggx.bytedance.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 *
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 *
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 *
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 */
public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int rowLength = grid.length;
        int columnLength = grid[0].length;
        int[][] visited = new int[rowLength][columnLength];
        int maxArea = 0;
        for(int i = 0;i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 0 || visited[i][j] == 1)continue;
                int area = getIslandArea(grid, i, j, visited, rowLength, columnLength);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }

    /**
     * 深度优先遍历获取岛屿面积
     */
    private int getIslandArea(int[][] grid, int i, int j, int[][] visited, int rowLength, int columnLength) {
        visited[i][j] = 1;
        List<Position> list = new LinkedList<>();
        list.add(new Position(i, j));
        int area = 0, length = list.size(); Position currentPosition;
        for(int k = 0; k < length; k++){
            area++;
            currentPosition = list.get(k);
            //将四周的1加入到列表里面
            if(currentPosition.row > 0 && grid[currentPosition.row-1][currentPosition.column] == 1
                    && visited[currentPosition.row-1][currentPosition.column] == 0){
                list.add(new Position(currentPosition.row-1, currentPosition.column));
                visited[currentPosition.row-1][currentPosition.column] = 1;
                length++;
            }
            if(currentPosition.row < rowLength - 1 && grid[currentPosition.row+1][currentPosition.column] == 1
                    && visited[currentPosition.row+1][currentPosition.column] == 0){
                list.add(new Position(currentPosition.row+1, currentPosition.column));
                visited[currentPosition.row+1][currentPosition.column] = 1;
                length++;
            }
            if(currentPosition.column > 0 && grid[currentPosition.row][currentPosition.column-1] == 1
                    && visited[currentPosition.row][currentPosition.column-1] == 0){
                list.add(new Position(currentPosition.row, currentPosition.column-1));
                visited[currentPosition.row][currentPosition.column-1] = 1;
                length++;
            }
            if(currentPosition.column < columnLength-1 && grid[currentPosition.row][currentPosition.column+1] == 1
                    && visited[currentPosition.row][currentPosition.column+1] == 0){
                list.add(new Position(currentPosition.row, currentPosition.column+1));
                visited[currentPosition.row][currentPosition.column+1] = 1;
                length++;
            }
        }
        return area;
    }

     class Position{
        public int row;
        public int column;

        public Position(int row, int column){
            this.row = row;
            this.column = column;
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[4][5];
        grid[0] = new int[]{1,1,0,0,0};
        grid[1] = new int[]{1,1,0,0,0};
        grid[2] = new int[]{0,0,0,1,1};
        grid[3] = new int[]{0,0,0,1,1};
//        System.out.println(maxAreaOfIsland(grid));
    }
}
