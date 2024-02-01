package DSAA_Lab.sorting;

import java.util.Scanner;

public class P1923 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextInt(),k=sc.nextInt();
        long[]num =new long[(int) n];
        for (int i = 0; i < n; i++) {
            num[i]=sc.nextInt();
        }
        num=MergeSort(num,0, num.length-1);
        System.out.println(num[(int)k]);
    }
    public static long[] MergeSort(long[] num,long l,long r){
        if (l==r){
            return new long[]{num[(int) l]};
        }
        long mid=(l+r)/2;
        long[]leftArray=MergeSort(num,l,mid);
        long[]rightArray=MergeSort(num,mid+1,r);
        long[]newNum=new long[(int) (r-l+1)];
        int i=0,j=0,n=0;
        while (i< leftArray.length&&j< rightArray.length){
            newNum[n++]=leftArray[i]>=rightArray[j]?rightArray[j++]:leftArray[i++];
        }
        while (i< leftArray.length){
            newNum[n++]=leftArray[i++];
        }
        while (j<rightArray.length){
            newNum[n++]=rightArray[j++];
        }
        return newNum;
    }
}
