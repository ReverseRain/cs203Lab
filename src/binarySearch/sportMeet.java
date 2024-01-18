package binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class sportMeet {   //二分答案
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int L=sc.nextInt(),n=sc.nextInt(),m=sc.nextInt();
            int[] num=new int[n+1],road=new int[L];
            int start=0;
            for (int i = 0; i < n; i++) {
                num[i]=sc.nextInt();
            }
            num[n]=L;
            Arrays.sort(num);
            for (int i = 0; i < num.length; i++) {
                for (int j = start; j < num[i]; j++) {
                    road[j]=i+1;
                }
                start=num[i];
            }
            int left=0,right=L,mid=0,point=0;
            while (left<right){
                mid=(left+right)/2;
                if (!checkWinII(mid,road,m,num)){
                    left=mid+1;
                }else {
                    right=mid;
                }
            }
            System.out.println(left);
        }
    }
    static boolean checkWinII(int mid,int[] road,int m,int[] num){
        int start=0;
        for (int i = 0; i < m; i++) {
            if(mid+start>=road.length){
                return true;
            }
            if (road[mid+start]<=road[start]){
                return false;
            }
            start=num[road[mid+start]-2];
        }
        return false;
    }
}
