package com.ggx.forkjoin;

import java.util.Random;
import java.util.concurrent.*;

public class SortForkJoinDemo {

    private class SortTask extends RecursiveAction {

        private final int Threshold = 10;
        private int start;
        private int end;
        private int[] arr;

        SortTask(){}

        SortTask(int[] arr, int start, int end){
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if(end - start < Threshold){
                sortArr(arr, start, end);
            }else{
                int mid = splitArr(arr, start, end);
                invokeAll(new SortTask(arr, start, mid-1), new SortTask(arr, mid, end));
            }
        }

    }

    private static void sortArr(int[] arr, int start, int end) {
        int mid = splitArr(arr, start, end);
        if(start < mid){
            sortArr(arr, start, mid - 1);
        }
        if(mid < end){
            sortArr(arr, mid, end);
        }
    }

    private static int splitArr(int[] arr, int start, int end) {
        if(start >= end) return start;
        int pivot = arr[start + (end-start)/2];
        while(start <= end){
            while(arr[start] < pivot){
                start++;
            }
            while(arr[end] > pivot){
                end--;
            }
            if(start < end){
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }else if(start == end){
                start++;
            }
        }
        return start;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int length = 2000000;
        int[] arr = new int[length];
        Random random = new Random();
        for(int i = 0; i < length; i++){
            arr[i] = random.nextInt(2048000);
        }
        int[] copyArr = new int[2000000];
        System.arraycopy(arr, 0, copyArr, 0, 2000000);
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Void> submit = pool.submit(new SortForkJoinDemo().new SortTask(arr, 0, 1999999));
        submit.get();
//        pool.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("forkJoin运行耗时:" + (System.currentTimeMillis() - start));
//        for(int i = 0; i < length; i++){
//            System.out.print(arr[i] + "  ");
//        }
        pool.shutdown();
        start = System.currentTimeMillis();
        sortArr(copyArr, 0, 1999999);
        System.out.println("\n普通运行耗时:" + (System.currentTimeMillis() - start));
    }
}
