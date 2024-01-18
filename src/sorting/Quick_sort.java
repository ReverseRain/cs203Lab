package sorting;

import java.util.Scanner;

public class Quick_sort {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long N=sc.nextInt();
        long[]num=new long[(int)N];
        for (int i = 0; i < N; i++) {
            num[i]=sc.nextInt();
        }
        sort(num,0,N-1);
        for (int i = 0; i < N; i++) {
            System.out.print(num[i]+" ");
        }
    }
    static void sort(long[] num,long left,long right){
        if (left>=right){
            return;
        }
        long pivit=num[(int)(left+right)/2];
        long temp,i=left-1,j=right+1;
        while (i<j){
            do{
                i++;
            }while (num[(int) i]<pivit);
            do {
                j--;
            }while (num[(int) j]>pivit);
            if (i<j){
                temp=num[(int)i];
                num[(int)i]=num[(int)j];
                num[(int)j]=temp;
            }
            System.out.println(i+"haha"+j);
        }
        System.out.println(i+" "+j);
        sort(num,left,j);sort(num,j+1,right);//j与i是否是相等的？？？不相等！因为do while 中做一次会提前让i+1
    }
}
