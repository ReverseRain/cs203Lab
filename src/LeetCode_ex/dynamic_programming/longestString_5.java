package LeetCode_ex.dynamic_programming;

import java.util.Scanner;

public class longestString_5 {
    //important
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        System.out.println(dp(s));
        System.out.println(search(s));
    }
    /*
    怎么将回文串的问题转换为动态规划的问题
    对于一个长度大于2的回文串，那么将其头尾去掉依旧是一个回文串
    根据这样的思路,我们可以得到以下的结论：
    1.获得表达式
    s(i,j)=s(i+1,j-1)^(s[i]==s[j])
    2.获得动态规划的边界条件
    s(i,i)=true;
    s(i,i+1)=(s[i]==s[i+1]);
    3.根据状态转移方程获得规划顺序

     */
    public static String dp(String s){
        if (s.length()<2){
            return s;
        }
        int len=s.length();
        boolean[][] dp=new boolean[len][len];
        int maxLen=1,start=0;
        char[] array=s.toCharArray();
        for (int L = 2; L <=len ; L++) {
            for (int i = 0; i < len; i++) {
                int j=L+i-1;
                if (j>=len){
                    break;
                }
                if (array[i]!=array[j]){
                    dp[i][j]=false;
                }else {
                    if (j-i<3){
                    dp[i][j]=true;
                    }else {
                    dp[i][j]=dp[i+1][j-1];
                    }
                }
                if (dp[i][j]&&j-i+1>maxLen){
                    maxLen=j-i+1;
                    start=i;
                }
            }
        }
        return s.substring(start,start+maxLen);
    }
    /*
    通过边缘扩散的方法来遍历检测最长的子串
     */
    public static String search(String s){
        int maxLen=1;
        int start=0;
        for (int i = 0; i < s.length(); i++) {
            int len1=expand(s,i,i);
            int len2=0;
            if (i+1<s.length()){
            len2=expand(s,i,i+1);
            }
            int len=Math.max(len1,len2);
            if (len>=maxLen){
                start=i-(len-1)/2;
                maxLen=len;
            }
        }
        return s.substring(start,start+maxLen);
    }
    public static int expand(String s,int start,int end){
        if (end-start!=0
                &&s.charAt(end)!=s.charAt(start)){
            return 0;
        }
        int ans=end-start+1;
        while (start-1>=0&&end+1<s.length()){
            if (s.charAt(start-1)==s.charAt(end+1)){
                ans=ans+2;
            }else {
                break;
            }
            start=start-1;
            end=end+1;
        }
        return ans;
    }
/*
Manacher 算法
定义一个新的概念为臂长，表示中心扩展算法向外扩展的长度。
一个回文串的长度为2*length+1，则其对应的臂长是length



 */
    public static String mana(String s){
        return null;
    }
}
