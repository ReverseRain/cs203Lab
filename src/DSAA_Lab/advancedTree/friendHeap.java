package DSAA_Lab.advancedTree;

import java.util.Scanner;

public class friendHeap {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] heap=new int[n+1];
        int[] ans=new int[n+1];
        int size=0;
        for (int i = 1; i <n+1 ; i++) {
            size++;
            heap[size] = sc.nextInt();
            int t = size;
            while (t > 1) {
                if (heap[t] > heap[t / 2]) {
                    int tem = heap[t / 2];
                    heap[t / 2] = heap[t];
                    heap[t] = tem;
                    t=t/2;
                    ans[i]++;
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i <n+1 ; i++) {
            System.out.print(ans[i]+" ");
        }
    }
}
