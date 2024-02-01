package DSAA_Lab.sorting;

import DSAA_Lab.fall_lab0.QReader;
import DSAA_Lab.fall_lab0.QWriter;

public class SumProduct {//merge sort 归并排序
    public static void main(String[] args) {
        QReader sc=new QReader();
        QWriter out=new QWriter();
        long n=sc.nextInt();
        long[]num=new long[(int)n];
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }
        num=MergeSort(num,0, num.length-1);
        long ans=0;
        for (int i = 0; i < num.length/2; i++) {
            ans=ans+num[i]+num[num.length-1-i];
        }
        out.println(ans);
        out.close();
    }
    public static long[] MergeSort(long[] num,long left,long right){
        if (left==right){
            return new long[]{num[(int)left]};//为什么这里要再new一个新的int【】？？？数组传入的是地址
        }
        long mid=(left+right)/2;
        long[] leftArray=MergeSort(num,left,mid);
        long[] rigtArray=MergeSort(num,mid+1,right);
        long[] newArray=new long[(int)right-(int)left+1];
        int n=0,m=0,i=0;
        //超级简便的书写方法
        while (n<leftArray.length&&m<rigtArray.length){
            newArray[i++]=leftArray[n]<=rigtArray[m]?leftArray[n++]:rigtArray[m++];//!!哪儿有++！！
        }
        while (n<leftArray.length){
            newArray[i++]=leftArray[n++];
        }
        while (m<rigtArray.length){
            newArray[i++]=rigtArray[m++];
        }
        return newArray;
    }
}
