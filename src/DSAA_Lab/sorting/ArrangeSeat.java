package DSAA_Lab.sorting;

import java.util.Scanner;

public class ArrangeSeat {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextInt();
        long[]num=new long[(int) n];
        for (int i = 0; i < n; i++) {
            num[i]=sc.nextInt();
        }
        num=MergeSort(num,0,n-1);
        long ans= num.length/3,shift=0;
        long[] ansArray=new long[num.length];
        for (int i = 0; i < n; i++) {
            ansArray[i]=-1;
        }
        long po=ans-BinarySearch(num,ans);int j=0;

        for (int i = 0; i < ans-po; i++) {
            ansArray[j]=num[i];
            j+=3;
        }
        for (int i = 0; i < n; i++) {
            if (ansArray[i]==-1){
                ansArray[i]=num[(int)(ans-po+shift)];
                shift++;
            }
        }
        System.out.println(num[(int)ans]);
        for (int i = 0; i < ansArray.length; i++) {
            System.out.print(ansArray[i]+" ");
        }

    }
    public static long BinarySearch(long[]num,long ans){
        long l=0,r=ans;
        while (l<=r){
            long mid=(l+r)/2;
            if (num[(int)mid]==num[(int)ans]){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return l;
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
}
