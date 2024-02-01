package DSAA_Lab.string;

import java.util.Scanner;

public class numOfSubstring {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            String test=sc.next();
            int l=test.toCharArray().length;
            long ans=(l*l+l)/2;
            System.out.println(ans);
        }
    }
}
