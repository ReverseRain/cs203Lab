package LeetCode_ex.dynamic_programming;

import java.util.Scanner;

public class robber_198 {
    //important DP problem
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        long[] list=new long[t];
        for (int i = 0; i < t; i++) {
            list[i]=sc.nextInt();
        }
        System.out.println(recursion(list,t));
        System.out.println(dp(list,t));
    }
/*
动态规划的的四个解题步骤是：

  1.定义子问题
  2.写出子问题的递推关系
  3.确定 DP 数组的计算顺序
  4.空间优化（可选）

  假设Hk为偷了第k间的房子，f（k）为偷了k间房子的最大值
  可以获得表达式：{f（k）=max[f（k-1）,f（k-2）+Hk]}

 */
    public static long recursion(long[] list,int n){
        if (n<=0){
            return 0;
        }
        return Math.max(recursion(list,n-1),recursion(list,n-2)+list[n-1]);
    }
    //上述方法使用递归来求解子问题，时间复杂度极大。
    public static long dp(long[] list,int n){
        if (n<0){
            return 0;
        }
        long q=0,p=0,r=0;
        for (int i = 0; i < n; i++) {
            q=p;
            p=r;
            r=Math.max(q+list[i],p);
        }
        return r;
    }
}

