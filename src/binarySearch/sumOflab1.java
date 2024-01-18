package binarySearch;

import java.util.Scanner;

public class sumOflab1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            long ans=sum(sc.nextInt());
            System.out.println(ans);
        }
    }
    static long sum(int n){
        return (n+2)*(n+1)*n/6;
    }


}
