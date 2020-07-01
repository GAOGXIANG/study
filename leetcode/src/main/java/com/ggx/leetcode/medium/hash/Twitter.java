package com.ggx.leetcode.medium.hash;

import java.util.*;
import java.util.stream.Collectors;

public class Twitter {

    private Map<Integer, LinkedList<Tweet>> userTweetMap;
    private Map<Integer, HashSet<Integer>> followMap;
    private Long timestamp = 0L;

    /** Initialize your data structure here. */
    public Twitter() {
        userTweetMap = new HashMap<>();
        followMap = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId, timestamp++);
        if(userTweetMap.containsKey(userId)){
            userTweetMap.get(userId).addFirst(tweet);
        }else{
            LinkedList<Tweet> tweetLinkedList = new LinkedList<>();
            tweetLinkedList.addFirst(tweet);
            userTweetMap.put(userId, tweetLinkedList);
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> userList = followMap.getOrDefault(userId, new HashSet());
        List<Tweet> tempList = new ArrayList(userTweetMap.getOrDefault(userId, new LinkedList<>()));
        tempList = tempList.subList(0, tempList.size() > 10 ? 10 : tempList.size());
        for(Integer user : userList){
            getNewTenTweet(tempList, userTweetMap.get(user));
        }
        return tempList.stream().map(Tweet::getTweetId).collect(Collectors.toList());
    }

    private void getNewTenTweet(List<Tweet> tempList, LinkedList<Tweet> tweets) {
        if(tweets == null || tweets.isEmpty()) return;
        List<Tweet> temp = new ArrayList<>(10);
        int i = 0, j = 0;
        Tweet temp1, temp2;
        while(i < tempList.size() && j < tweets.size()){
            temp1 = tempList.get(i);
            temp2 = tweets.get(j);
            if(temp1.getTimestamp() > temp2.getTimestamp()){
                temp.add(temp1);
                i++;
            }else{
                temp.add(temp2);
                j++;
            }
            if(temp.size() == 10){
                tempList.clear();
                tempList.addAll(temp);
                return;
            }
        }
        if(i < tempList.size()){
            for(; i < tempList.size() && temp.size() < 10; i++){
                temp.add(tempList.get(i));
            }
        }
        if(j < tweets.size()){
            ListIterator<Tweet> it = tweets.listIterator(j);
            for(; it.hasNext() && tempList.size() < 10;){
                temp.add(it.next());
            }
        }
        tempList.clear();
        tempList.addAll(temp);
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followeeId == followerId) return;
        if(followMap.containsKey(followerId)){
            followMap.get(followerId).add(followeeId);
        }else{
            HashSet<Integer> followSet = new HashSet<>();
            followSet.add(followeeId);
            followMap.put(followerId, followSet);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followeeId == followerId) return;
        if(followMap.containsKey(followerId)){
            followMap.get(followerId).remove(followeeId);
        }
        return;
    }
    class Tweet{
        private Integer tweetId;
        private Long timestamp;

        public Tweet(){}

        public Tweet(Integer tweetId, Long timestamp){
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }

        public Integer getTweetId() {
            return tweetId;
        }

        public void setTweetId(Integer tweetId) {
            this.tweetId = tweetId;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.follow(1,2);
        twitter.postTweet(1,5);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1).size());
        twitter.unfollow(1,2);
        twitter.postTweet(2, 7);
        twitter.postTweet(2, 8);
        twitter.postTweet(2, 9);
        twitter.postTweet(2, 10);
        twitter.postTweet(2, 11);
        twitter.postTweet(2, 12);
        twitter.postTweet(2, 13);
        twitter.postTweet(2, 14);
        twitter.postTweet(2, 15);
        twitter.postTweet(2, 16);
        twitter.postTweet(2, 17);
        twitter.postTweet(2, 18);
        twitter.postTweet(2, 19);
        twitter.postTweet(2, 20);
        System.out.println(twitter.getNewsFeed(1).size());
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

