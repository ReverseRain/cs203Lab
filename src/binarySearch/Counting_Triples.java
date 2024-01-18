package binarySearch;

import java.util.ArrayList;
import java.util.Scanner;

public class Counting_Triples {
    static long ans=0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextInt(),S=sc.nextInt();
        long[] num=new long[(int)n];
        for (int i = 0; i < n; i++) {
            num[i]=sc.nextInt();
        }
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j <n-1 ; j++) {
                long front=binarysearchFront(num,j+1,S-num[i]-num[j]);
                long back=binarysearchBack(num,j+1,S-num[i]-num[j]);
                if (front!=-1&&back!=-1){ans=ans+back-front+1;}
            }
        }
        System.out.println(ans);
    }
    static long binarysearchFront(long[] num,int start,long target){
        int left=start,right=num.length-1;
        while (left<right){
            int mid=(left+right)/2;
        if (num[mid]==target){
            right=mid;
        } else if (num[mid]>target) {
            right=mid-1;
        } else if (num[mid]<target) {
            left=mid+1;
        }
          }
        if (num[right]==target){
            return right;
        }else return -1;
    }
    static long binarysearchBack(long[] num,int start,long target){
        int left=start,right=num.length-1;
        while (left<right){
            int mid=(left+right+1)/2;
            if (num[mid]>target){
                right=mid-1;
            }else left=mid;
        }
        if (num[left]==target){
            return left;
        }else return -1;
    }
}
