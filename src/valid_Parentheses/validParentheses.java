package valid_Parentheses;

import java.util.ArrayDeque;
import java.util.Deque;

public class validParentheses {
    /**
     * LeetCode 20
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * @param s
     * @return
     */
    public boolean isValid(String s){
        Deque<Character> sta=new ArrayDeque<>();
        for(char c:s.toCharArray()){
            if(c=='('||c=='['||c=='{')
                sta.push(c);
            else{
                if(!sta.isEmpty()&&resver(c)==sta.peek())
                    sta.pop();
                else return false;
            }
        }
        return sta.isEmpty();
    }
    private char resver(char c){
        if(c==')') return '(';
        else if(c==']') return '[';
        else return '{';
    }

    /**
     * LeetCode 921
     * 只有满足下面几点之一，括号字符串才是有效的：
     *
     * 它是一个空字符串，或者
     * 它可以被写成AB（A与B连接）, 其中A 和B都是有效字符串，或者
     * 它可以被写作(A)，其中A是有效字符串。
     * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
     *
     * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
     * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
     * @param s
     * @return
     */
    /*核心思路是以左括号为基准，通过维护对右括号的需求数 need，来计算最小的插入次数*/
    public int minAddToMakeValid(String s){
        // left 变量记录左括号的插入量
        int left=0;
        // right 变量记录右括号的需求量
        int right=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(')
                //对右括号的需求+1
                right++;
            else{
                //对右括号的需求-1
                right--;
                if(right==-1){
                    right=0;
                    //需要插入一个左括号
                    left++;
                }
            }
        }
        return left+right;
    }

    /**
     * LeetCode 1541
     * 给你一个括号字符串s，它只包含字符'(' 和')'。一个括号字符串被称为平衡的当它满足：
     *
     * 任何左括号'('必须对应两个连续的右括号'))'。
     * 左括号'('必须在对应的连续两个右括号'))'之前。
     * 比方说"())"，"())(())))" 和"(())())))"都是平衡的，")()"，"()))" 和"(()))"都是不平衡的。
     * 你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
     * 请你返回让 s平衡的最少插入次数。
     * @param s
     * @return
     */
    public int minInsertions(String s){
        int left=0,right=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                right+=2;
                if(right%2==1){
                    left++;
                    right--;
                }
            }
            else{
                right--;
                if(right==-1){
                    left++;
                    right=1;
                }
            }
        }
        return left+right;
    }
}
