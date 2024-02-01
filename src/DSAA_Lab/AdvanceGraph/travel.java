package DSAA_Lab.AdvanceGraph;


import java.util.ArrayList;
import java.util.Scanner;

public class travel {
    static int n,m;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
         n=sc.nextInt();m=sc.nextInt();
        nodeAdGraphA[] num=new nodeAdGraphA[n+1];
        for (int i = 0; i < n+1; i++) {
            num[i]=new nodeAdGraphA(i);
        }
        for (int i = 0; i < m; i++) {
            int u=sc.nextInt(),v=sc.nextInt();
            long w=sc.nextInt();
            num[u].list.add(num[v]);
            num[u].intPath.add(w);
        }
        long size=0;
        nodeAdGraphA[] heap=new nodeAdGraphA[n+1];

        num[1].dist=0;
        size=insert(heap,size,num[1]);
        dij(heap[1],heap,size);
    }
    public static void dij(nodeAdGraphA start,nodeAdGraphA[] heap,long size){
        if (start==null){
            System.out.println(-1);
            return;
        }

        start.isVisited=true;
        if (start.v==n){
            if (start.dist!=Long.MAX_VALUE){
            System.out.println(start.dist);}
            else System.out.println(-1);
            return;
        }
        for (int i = 0; i < start.list.size(); i++) {
            nodeAdGraphA tem=start.list.get(i);
            if (!tem.isVisited&&tem.dist>=(start.intPath.get(i)+start.dist)){
                if (tem.dist==Long.MAX_VALUE){
                    size=insert(heap,size,tem);
                }
                tem.dist=start.intPath.get(i)+start.dist;
                update(heap,tem);
            }
        }
        size=delete(heap,size);
        dij(heap[1],heap,size);
    }
    public static long insert(nodeAdGraphA[] heap, long size, nodeAdGraphA value){
        size++;
        heap[(int)size]=value;
        value.index=size;
        long t=size;
        while (t>1){
            if (heap[(int)t].dist<heap[(int)t/2].dist){
                nodeAdGraphA tem=heap[(int)t/2];
                heap[(int)t/2]=heap[(int)t];
                heap[(int)t]=tem;
                heap[(int)t/2].index=t/2;
                heap[(int)t].index=t;
                t=t/2;
            }else {
                break;
            }
        }
        return size;
    }
    public static long delete(nodeAdGraphA[]heap,long size){
        long t=1;
        heap[1]=heap[(int)size];size--;
        if (heap[1]==null){
            return size;
        }
        heap[1].index=1;

        while (2*t<=size){
            if (2*t+1<=size&&heap[(int) t].dist > heap[(int) (2 * t + 1)].dist&&heap[(int) (2 * t)].dist > heap[(int) (2 * t + 1)].dist){
                nodeAdGraphA tem = heap[(int) (2 * t + 1)];
                heap[(int) (2 * t + 1)] = heap[(int) t];
                heap[(int) t] = tem;
                heap[(int)t].index=t;
                heap[(int)(2*t+1)].index=2*t+1;
                t = 2 * t + 1;
            } else if (heap[(int) t].dist > heap[(int) (2 * t)].dist) {
                nodeAdGraphA tem = heap[(int) (2 * t)];
                heap[(int) (2 * t)] = heap[(int) t];
                heap[(int) t] = tem;
                heap[(int)t].index=t;
                heap[(int)(2*t)].index=2*t;
                t = 2 * t;
            }else {
                break;
            }
        }
        return size;
    }
    public static void update(nodeAdGraphA[]heap,nodeAdGraphA value){
        long t=value.index;
        while (t>1){
            if (heap[(int)t].dist<heap[(int)t/2].dist){
                nodeAdGraphA tem=heap[(int)t/2];
                heap[(int)t/2]=heap[(int)t];
                heap[(int)t]=tem;
                heap[(int)t/2].index=t/2;
                heap[(int)t].index=t;
                t=t/2;
            }else {
                break;
            }
        }
    }
}
class nodeAdGraphA{
    ArrayList<nodeAdGraphA> list=new ArrayList<>();
    ArrayList<Long> intPath=new ArrayList<>();
    long dist= Long.MAX_VALUE;
    boolean isVisited=false;
    long index=0;
    int v=0;

    public nodeAdGraphA(int v) {
        this.v = v;
    }
}
