package binarySearch;

import java.util.Scanner;

public class bSearchA {
    static boolean good=false;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[]num=new int[n];
        for (int i = 0; i < n; i++) {
            num[i]=sc.nextInt();
        }
        int T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            binarySearch(sc.nextInt(),num);
            if (good){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
            good=false;
        }
    }
    static void binarySearch(int x,int[] num){
        int left=0,right= num.length;
        while (left<right){
            int mid=(left+right)/2;
            if (num[mid]==x){
                good=true;
                return;
            } else if (num[mid]>x) {
                right=mid;
            }else {
                left=mid+1;
            }
        }
    }
    static int binarySearch2(int x,int[]num){
        int l=0,r=num.length-1,mid;
        while (l<=r){
            mid=(l+r)/2;
            if (num[mid]==x){
                r=mid-1;//寻找最小，从从右边不断逼近
            }else l=mid+1;
        }
        return l;
    }
//    static void binarySearchII(int x,long[]num) {
//        int l=0,r= num.length;
//        while (l<r){
//            int mid=(l+r)/2;
//            if (num[mid]==x){
//                System.out.println("wa");
//                return;
//            } else if (num[mid]<x) {
//                l=mid+1;
//            }else {
//                r=mid;
//            }
//        }
//    }

}
