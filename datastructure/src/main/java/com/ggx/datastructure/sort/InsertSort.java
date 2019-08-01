package com.ggx.datastructure.sort;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 插入排序
 * Created by Administrator on 2017/7/27 0027.
 */
public class InsertSort {

    public List<Integer> sort(int[][] array){
        List<Integer> result = new LinkedList<Integer>();
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                int a = array[i][j];
                if(result.size() == 0){
                    result.add(a);
                }else{
                    for(ListIterator<Integer> it = result.listIterator(); it.hasNext();){
                        if(a < it.next()){
                            it.set(a);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[][] array = new int[][]{{1,2,3},{3,4,5},{5,6,7}};
        System.out.println(array[1].length);
    }
}
