package LeetCode_ex.dynamic_programming;

import java.util.Scanner;

public class climb_70 {
    static int cnt=0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        divide(n);
//        System.out.println(cnt);
//        System.out.println(divide2(n));
        System.out.println(rolling2(n));
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = qPower2(q, n);
        System.out.println(res[1][0]+res[1][1]);
    }
    /*
    斐波那些数列--递归方法
    时间复杂度：O(2^n)
    当n十分的大时，时间复杂度将会很大
     */
    public static void divide(int n){
        if (n==0){
            cnt++;
            return;
        }
        if (n<0){
            return;
        }
        divide(n-1);
        divide(n-2);
    }
    public static int divide2(int n){
        if (n==1){
            return 1;
        } else if (n==2) {
            return 2;
        } else {
            return divide2(n-1)+divide2(n-2);
        }
    }
    /*
    表达式：f(x)=f(x-1)+f(x-2)x-2
    通过上式，我们可以推断出f(x)的值仅与f(x-1),f(x-2)有关
    使用滚动数组的方式进行优化
    时间复杂度：O(n)。空间复杂度：O(1)
     */
    public static int rolling2(int n){
        int q=0,p=0,r=1;
        for (int i = 1; i <=n ; i++) {
            q=p;
            p=r;
            r=q+p;
        }
        return r;
    }
    /*
    矩阵快速幂：通过线性代数的方式来化简动态规划的时间复杂度
    快速幂：应用到一个二分的思路
    a^n={a^(n-1)*a,当n为奇数时，将n-1转化为偶数
         a^(n/2)*a^(n/2),当n为偶数时利用递归来减少运算成本
         1，当n=0时}
     时间复杂度为O(logn)
     */
    public static int qPower(int a,int n){
        if (n==0){
            return 1;
        }else if (n%2==1){
            return qPower(a,n-1)*a;
            //当n==1时，结果为a，注意避免qPower(a,1)循环递归
        }else {
            int tem=qPower(a,n/2);
            return tem*tem;
        }
    }
    //转换为矩阵版本
    public static int[][] qPower2(int[][] a,int n){
        if (n==0){
            int[][] tem=new int[a.length][a[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    if (i==j){
                    tem[i][j]=1;}else {
                        tem[i][j]=0;
                    }
                }
            }
            return tem;
        }else if (n%2==1){
            return muti(qPower2(a,n-1),a);
            //当n==1时，结果为a，注意避免qPower(a,1)循环递归
        }else {
            int[][] tem=qPower2(a,n/2);
            return muti(tem,tem);
        }
    }
    public static int[][] muti(int[][]a,int[][]b){
        int[][]ans=new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    ans[i][j]=ans[i][j]+a[i][k]*b[k][j];
                }

            }
        }
        return ans;
    }
}
