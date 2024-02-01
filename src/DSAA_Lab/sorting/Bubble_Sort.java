package DSAA_Lab.sorting;

import java.util.Scanner;

public class Bubble_Sort {//求逆序数！！！！
    static long ans=0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            long n=sc.nextInt();
            long[]array=new long[(int) n];
            for (int j = 0; j < n; j++) {
                array[j]=sc.nextInt();
            }
            array=MergeSort(array,0,array.length-1);
            System.out.println(ans);
            ans=0;
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
            if (leftArray[i]>rightArray[j]){
                newArray[m++]=rightArray[j++];
                ans+=mid-i+1-left;
            }else newArray[m++]=leftArray[i++];
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
