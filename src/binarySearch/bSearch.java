package binarySearch;

import java.util.Scanner;

public class bSearch {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),Q=sc.nextInt();
        int[]list=new int[N];
        for (int i = 0; i < N; i++) {
            list[i]=sc.nextInt();
        }
        for (int i = 0; i < Q; i++) {
            int x=sc.nextInt(),y=sc.nextInt();
            int left=binarySearchI(list,x);
            int right=binarySearchII(list,y);
            if (left!=-1&&right!=-1&&right>=left){
                int ans=right-left+1;
                System.out.println("YES "+ans);
            }else {
                System.out.println("NO");
            }

        }
    }
    static int binarySearchI(int[] list, int target){
        int left=0;
        int right=list.length;//[left,right)right的位置是取不得到的
        while (left<right){
            int mid=(left+right)/2;
            if (list[mid]>target){
                if (mid==0||(mid-1>=0&&list[mid-1]<=target)){
                    return mid;
                }else {
                    right=mid;
                }
            } else {
                left=mid+1;
            }
        }
        return -1;
    }
    static int binarySearchII(int[] num, int target){
        int left=0;int right=num.length;
        while (left<right){
            int mid=(right+left)/2;
            if (num[mid]<target){
                if (mid== num.length-1||(mid<num.length-1&&num[mid+1]>=target)){
                    return mid;
                }else {
                    left=mid+1;
            }
            }else {
                right=mid;
            }
        }
    return -1;}
}
