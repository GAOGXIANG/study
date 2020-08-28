package com.ggx.bytedance.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 */
public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new LinkedList<>();
        backTrack(s, 0, 4, new StringBuilder(), result);
        return result;
    }

    private void backTrack(String s, int position, int surplus, StringBuilder sb, List<String> result) {

        int surplusLength = s.length() - position;
        if(surplusLength > surplus * 3 || surplusLength < surplus * 1) return;
        String subString; Integer value;
        if(surplus == 1){
            subString = s.substring(position + 1);
            value = Integer.parseInt(subString);
            if((value == 0 && subString.length() == 1)
                || (value > 0 && value <= 255)){
                sb.append(".").append(subString);
                result.add(sb.substring(1));
//                sb.delete()
            }
        }else {
            if (s.charAt(position) == '0') {
                sb.append(".").append("0");
                backTrack(s, position + 1, surplus - 1, sb, result);
            }
            int count = Math.min(surplusLength, 3);
            while(count >= 0){

            }
        }

    }
}
