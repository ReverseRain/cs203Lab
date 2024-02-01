package LeetCode_ex.dynamic_programming;

import java.util.Scanner;

public class countFib_509 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(Fib1(n));
        int[][] a={{1,1},{1,0}};
        int[][] tem=qPower2(a,n-1);
        System.out.println(tem[1][0]+tem[1][1]);
    }
    /*
    动态规划--滚动数组
     */
    public static int Fib1(int n){
        int q=0,p=0,r=1;
        if (n==0){
            return 0;
        }
        for (int i = 1; i <n ; i++) {
            q=p;
            p=r;
            r=q+p;
        }
        return r;
    }
    /*
    矩阵快速幂
     */
    public static int[][] qPower2(int[][] a,int n){
        if (n<0){
            int[][]tem={{0,0},{0,0}};
            return tem;
        }
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
