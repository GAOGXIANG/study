package com.ggx.leetcode.easy.array;

/**
 * https://leetcode.com/problems/first-bad-version/
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Example:
 *
 * Given n = 5, and version = 4 is the first bad version.
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 *
 * premise:bad version always exist;
 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {
        int left = 0, right = n;
        //解题思路:left=right时，left的位置就是第一个错误版本，这是在前提错误版本一定存在下才正确
        while(left < right){
            int mid = left + (right - left) /2;
            if(isBadVersion(mid)) {left = mid + 1;}
            else { right = mid;}
        }
        return left;
    }

    /**
     * 模拟判断是否是错误版本
     */
    private boolean isBadVersion(int mid) {
        return false;
    }
}
