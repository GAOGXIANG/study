package com.ggx.leetcode.medium.design;

import java.util.HashMap;

public class LRUCache {


    private int capacity;
    private HashMap<Integer, Cache> cacheMap;
    private Cache head = new Cache();
    private Cache tail = new Cache();


    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        cacheMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        Cache cache = cacheMap.get(key);
        //如果不为空，放到链表头
        if(cache != null){
            cache.next.prev = cache.prev;
            cache.prev.next = cache.next;
            Cache temp = head.next;
            head.next = cache;
            cache.prev = head;
            cache.next = temp;
            temp.prev = cache;
            return cache.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Cache cache;
        if((cache = cacheMap.get(key)) != null){
            cache.value = value;
            cache.prev.next = cache.next;
            cache.next.prev = cache.prev;
        }else{
            if(cacheMap.size() == capacity){
                //淘汰表尾的节点
                Cache leastRecentlyCache = tail.prev;
                tail.prev = leastRecentlyCache.prev;
                leastRecentlyCache.prev.next = tail;
                cacheMap.remove(leastRecentlyCache.key);
            }
            cache = new Cache(key, value);
            cacheMap.put(key, cache);
        }
        cache.next = head.next;
        cache.next.prev = cache;
        head.next = cache;
        cache.prev = head;
    }

    private class Cache{
        Cache prev, next;
        int value;
        int key;

        public Cache(int key, int value) {
            this.value = value;
            this.key = key;
        }

        public Cache(){}
    }
}
