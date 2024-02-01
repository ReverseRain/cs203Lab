package LeetCode_ex.dynamic_programming;

import java.util.Scanner;

public class differentPath_62 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextInt(),m=sc.nextInt();
        System.out.println(count(n+m-2,m-1));
        System.out.println(dp(n,m));
    }
    /*
    法一：排列组合公式Cnm
    法二：动态规划--dp[i][j]=dp[i-1][j]+dp[i][j-1];
    通过滚动数组进行简化可以发现，dp[i][j]仅与dp[i]与dp[i-1]有关
     */
    public static long count(long n,long m){
        long ans=1;
        for (int i = 1; i <=m ; i++) {
            ans=ans*(n-i+1)/i;
        }
        return ans;
    }
    public static long dp(long m,long n){
        long[] f=new long[(int)n];
        for (int i = 0; i < n; i++) {
            f[i]=1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[j]+=f[j-1];
            }
        }
        return f[(int)n-1];
    }
}
