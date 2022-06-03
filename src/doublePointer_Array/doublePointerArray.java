package doublePointer_Array;

public class doublePointerArray {
    /**
     * LeetCode 26
     * 给你一个 升序排列 的数组 nums ，请你原地删除重复出现的元素，
     * 使每个元素只出现一次 ，返回删除后数组的新长度。元素的相对顺序应该保持一致 。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums){
        if(nums.length==0) return 0;
        int slow=0,fast=0;
        while(fast<nums.length){
            if(nums[fast]!=nums[slow]){
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow+1;
    }

    /**
     * LeetCode 27
     * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val){
        int slow=0,fast=0;
        while(fast<nums.length){
            if(nums[fast]!=val){
                nums[slow]=nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * LeetCode 283
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * @param nums
     */
    public void moveZeroes(int[] nums){
        int slow=0,fast=0;
        while(fast<nums.length){
            if(nums[fast]!=0){
                nums[slow]=nums[fast];
                slow++;
            }
            fast++;
        }
        while(slow<nums.length)
            nums[++slow]=0;
    }    /**
     * LeetCode 167
     * 给你一个下标从 1 开始的整数数组numbers ，该数组已按 非递减顺序排列，请你从数组中找出满足相加之和等于目标数target 的两个数。
     * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，
     * 则 1 <= index1 < index2 <= numbers.length 。
     *
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     *
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target){
        int[] res=new int[2];
        int left=0,right=numbers.length-1;
        while (left<=right){
            if(numbers[left]+numbers[right]<target) {
                left++;
            }
            else if (numbers[left]+numbers[right]==target){
                //下标从1开始，所以需要+1
                res[0]=left+1;
                res[1]=right+1;
                break;
            }else if (numbers[left]+numbers[right]>target){
                right--;
            }
        }
        return res;
    }

    /**
     * LeetCode 344
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * @param s
     */
    public void reverseString(char[] s){
        int left=0,right=s.length-1;
        while(left<=right){
            char temp=s[left];
            s[left]=s[right];
            s[right]=temp;
            left++;
            right--;
        }
    }

    /**
     * LeetCode 5
     * 给你一个字符串 s，找到 s 中最长的回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s){
        String res="";
        for(int i=0;i<s.length();i++){
            String s1=palindrome(s,i,i);
            String s2=palindrome(s,i,i+1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }
    private String palindrome(String s, int l, int r){
        while (l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
            l--; r++;
        }
        return s.substring(l+1,r);
    }
}
