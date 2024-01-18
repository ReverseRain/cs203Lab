package stack_queue;

import java.util.ArrayList;
import java.util.Scanner;

public class Magic_Sequences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int a;
        downE down=new downE();
        int x=0;long ans;
        for (int i = 0; i < K; i++) {
            x=sc.nextInt();
            down.enQueue(x,i);
        }
        ans=down.getStart();
        int i=K-1;
        while ((x=sc.nextInt())!=-1){
            i++;
            down.enQueue(x,i);
            while (down.getStartIndex()<=i-K||down.getStartIndex()>i){
                down.deQueue();
            }
            ans=ans^ down.getStart();
        }
        System.out.println(ans);
    }
}
//单调双端队列
class downE{
    long[] num=new long[2000001];
    long[] index=new long[2000001];
    int start=0;
    int tail=0;
    void enQueue(int x,int dex){
        if (tail>0){
            while (tail-1>=0&&num[tail-1]<x){
                tail--;
            }
        }
        num[tail]=x;
        index[tail]=dex;
        tail++;
        start=0;
    }
    void deQueue(){
        start=start+1;
    }
    long getStart(){
        if (start<tail){
            return num[start];
        }else return -1;
    }
    long getStartIndex(){
        if (start<tail){
            return index[start];
        }else return -1;
    }
}
