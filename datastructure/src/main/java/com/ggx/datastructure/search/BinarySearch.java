package com.ggx.datastructure.search;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * @author ggx
 * @version 1.0
 * @date 9/5/2017
 */
public class BinarySearch {

    /**
     * 实现模板1
     * @param key 需要查找的值
     * @param a 数组必须是有序的
     * @return
     */
    public static int search(int key, int[] a){
        int low = 0;
        int high = a.length - 1;
        while(low <= high){
            int mid = low + ((high - low) >>> 1);
            if(key < a[mid]) high = mid -1;
            if(key > a[mid]) low = mid+1;
            if(key ==  a[mid]) return mid;
        }
        return -1;
    }
    public static void main(String[] args) throws NoSuchMethodException {
        List<String> foo = new ArrayList<>();
        // 在类的外部这样获取
        Type[] types = foo.getClass().getTypeParameters();
        for(Type type : types) {
            System.out.println(type);
        }
        System.out.println(foo.getClass().getTypeName());
    }

}
