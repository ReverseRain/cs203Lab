package AdvanceGraph;

import java.util.ArrayList;
import java.util.Scanner;

public class QueenGame {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        nodeGraphC[][] map=new nodeGraphC[n+1][m+1];
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j <m+1 ; j++) {
                map[i][j]=new nodeGraphC(sc.nextInt());
            }
        }
        build(map);
        long tem=0;
        nodeGraphC start=map[1][1];
        nodeGraphC[] heap=new nodeGraphC[n*m+1];
        long size=0;
        size=insert(heap,size,start);
        prim(start,heap,size);
        System.out.println(ans);
    }
    public static void build(nodeGraphC[][]map){
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                if (i-1>0){
                    map[i][j].list.add(map[i-1][j]);
                }
                if (j-1>0){
                    map[i][j].list.add(map[i][j-1]);
                }
                if (i+1<map.length){
                    map[i][j].list.add(map[i+1][j]);
                }
                if (j+1<map[0].length){
                    map[i][j].list.add(map[i][j+1]);
                }
            }
        }
    }
    static long ans=0;
    public static void prim(nodeGraphC start,nodeGraphC[]heap,long size){
        if (start==null){
            return;
        }
        if (start.bestW>0){
            ans=ans+start.bestW;}
        start.isVisited=true;
        size=delete(heap,size);
        for (int i = 0; i < start.list.size(); i++) {
            nodeGraphC tem=start.list.get(i);

            if (!tem.isVisited&&tem.bestW<start.value*tem.value){
                if (tem.bestW== 0){
                    size=insert(heap,size,tem);
                }
                tem.bestW=start.value*tem.value;
                update(heap,tem);
            }
        }
        prim(heap[1],heap,size);
    }
    public static long insert(nodeGraphC[] heap, long size, nodeGraphC value){
        size++;
        heap[(int)size]=value;
        value.index=size;
        long t=size;
        while (t>1){
            if (heap[(int)t].bestW>heap[(int)t/2].bestW){
                nodeGraphC tem=heap[(int)t/2];
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
    public static long delete(nodeGraphC[]heap,long size){
        long t=1;
        if (size==1){
            heap[1]=null;
        }else {
            heap[1]=heap[(int)size];}
        size--;
        if (heap[1]==null){
            return size;
        }
        heap[1].index=1;

        while (2*t<=size){
            if (2*t+1<=size&&heap[(int) t].bestW< heap[(int) (2 * t + 1)].bestW&&heap[(int) (2 * t)].bestW< heap[(int) (2 * t + 1)].bestW){
                nodeGraphC tem = heap[(int) (2 * t + 1)];
                heap[(int) (2 * t + 1)] = heap[(int) t];
                heap[(int) t] = tem;
                heap[(int)t].index=t;
                heap[(int)(2*t+1)].index=2*t+1;
                t = 2 * t + 1;
            } else if (heap[(int) t].bestW < heap[(int) (2 * t)].bestW) {
                nodeGraphC tem = heap[(int) (2 * t)];
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
    public static void update(nodeGraphC[]heap,nodeGraphC value){
        long t=value.index;
        while (t>1){
            if (heap[(int)t].bestW>heap[(int)t/2].bestW){
                nodeGraphC tem=heap[(int)t/2];
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
class nodeGraphC{
    ArrayList<nodeGraphC> list=new ArrayList<>();
    long value;
    long index=0;
    long bestW=0;
    boolean isVisited=false;
    public nodeGraphC(long value) {
        this.value = value;
    }
}
