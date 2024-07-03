package LeetCode_ex.dynamic_programming;

import java.util.Scanner;

public class wordBreak_139 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        int k=sc.nextInt();
        String[] dictionary=new String[k];
        for (int i = 0; i < k; i++) {
            dictionary[i]=sc.next();
        }
    }
//    public static boolean dp(String s,String[] dictionary){
//
//    }
}
