package DSAA_Lab.fall_lab0;

import java.util.Scanner;

public class search_I {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] A=new int[n];
        for (int i = 0; i < n; i++) {
            A[i]=sc.nextInt();
        }
        int T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            int k=sc.nextInt();
            Boolean bo=false;
            for (int j = 0; j < n; j++) {
                if (k==A[j]){
                    bo=true;
                }
            }
            if (bo){
                System.out.println("yes");
            }else System.out.println("no");
        }
    }
}
