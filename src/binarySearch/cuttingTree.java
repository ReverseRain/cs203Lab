package binarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class cuttingTree {  //有些点应该开long
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt();
        int[] trees=new int[N];
        for (int i = 0; i < N; i++) {
            trees[i]=sc.nextInt();
        }
        Arrays.sort(trees);
        long left=0,right=trees[N-1], mid=0;
        while (left<right){
            mid=(left+right+1)/2;
            if (check(mid,M,trees)){
                left=mid;
            }else right=mid-1;
        }
        if (check(right,M,trees)){
            System.out.println(right);
        }
    }
    static boolean check(long mid,int M,int[]trees){
        long s=0;
        for (int i = 0; i <trees.length ; i++) {
            if (mid<=trees[i]){
                s=s+trees[i]-mid;
            }
        }
        if (s>=M){
            return true;
        }else return false;
    }
}
