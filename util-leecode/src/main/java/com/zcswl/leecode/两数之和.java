package com.zcswl.leecode;

import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和  leecode
 *  https://leetcode-cn.com/problems/two-sum/
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * @author zhoucg
 * @date 2021-04-14 9:34
 */
public class 两数之和 {

    public int[] twoSum(int[] nums, int target) {
        // 3  2  4  target:6
        // 1  2
        // 暴力破解，肯定扯淡

        return null;
    }

    // 暴力破解，有点扯淡
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i <  nums.length;i++) {
            for (int j = i+1 ;j < nums.length ; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }

    // 使用hash表的策略
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // O(n) 的时间复杂度
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target-nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
