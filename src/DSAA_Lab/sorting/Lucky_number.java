package DSAA_Lab.sorting;

import java.util.Scanner;

public class Lucky_number {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextInt(),k=sc.nextInt();
        long[] num=new long[(int)n];
        for (int i = 0; i < n; i++) {
            num[i]=sc.nextInt();
        }
        long[]ansNum=new long[(int)n];
        Sort(num,ansNum,0,n-1);
        System.out.println(ansNum[(int)k-1]);
        for (int i = 0; i < n; i++) {
            System.out.print(ansNum[i]+" ");
        }
    }
    public static long[] MergeSort(long[]num,long left,long right){
        if (left==right){
            return new long[]{num[(int) left]};
        }
        long mid=(left+right)/2;
        long[] leftArray=MergeSort(num,left,mid);
        long[] rightArray=MergeSort(num,mid+1,right);
        long[] newArray=new long[(int) (right-left+1)];
        int i=0,j=0,m=0;
        while (i<leftArray.length&&j<rightArray.length){
            newArray[m++]=leftArray[i]<=rightArray[j]?leftArray[i++]:rightArray[j++];
        }
        while (i<leftArray.length){
            newArray[m++]=leftArray[i++];
        }
        while (j<rightArray.length){
            newArray[m++]=rightArray[j++];
        }
        return newArray;
}
    public static void Merge(long[]num,long[]ans,long left,long right,long mid){
        int i=(int)left,j=(int)mid+1,m=(int)left;
        while (i<=mid&&j<=right){
            ans[m++]=num[i]<=num[j]?num[i++]:num[j++];
        }

        while (i<=mid){
            ans[m++]=num[i++];
        }
        while (j<=right){
            ans[m++]=num[j++];
        }
        for (int k = (int)left; k <=right ; k++) {
            num[k]=ans[k];
        }
    }
     static void Sort(long[]num,long[]ans,long left,long right){
        if (left<right){
            long mid=(left+right)/2;
            Sort(num,ans,left,mid);
            Sort(num,ans,mid+1,right);
            Merge(num,ans,left,right,mid);
        }
    }

}
