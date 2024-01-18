package fall_lab0;

import java.util.Scanner;

public class Maximum_difference {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n=sc.nextInt();
            int[] list=new int[n];
            int maxn=0;
            int result=0,tem;
            for (int j = 0; j < n; j++) {
                list[j]=sc.nextInt();
                if (j!=0&&j!=1){
                    tem=maxn-list[j];
                    if (tem>result){
                        result=tem;
                    }
                    if (list[j]>maxn){
                        maxn=list[j];
                    }
                }else if (j==1){
                    result=maxn-list[j];
                    if (list[j]>maxn){
                        maxn=list[j];
                    }
                }else {
                    maxn=list[j];
                }

            }
            System.out.println(result);
        }
    }
}
