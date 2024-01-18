package AdvanceGraph;

import java.util.ArrayList;
import java.util.Scanner;

public class NaiveStone {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        int k=sc.nextInt();
        int c=sc.nextInt();
        nodeAdGraphD[]num=new nodeAdGraphD[n+1];
        for (int i = 1; i <n+1 ; i++) {
            num[i]=new nodeAdGraphD(sc.nextInt(),k);
        }
        for (int i = 0; i < m; i++) {
            int u=sc.nextInt(),v=sc.nextInt();
            num[u].near.add(num[v]);
            num[v].near.add(num[u]);
        }
        int[]colors=new int[k+1];
        for (int i = 1; i < k+1; i++) {
            colors[i]=i;
        }
        for (int i = 1; i <k+1 ; i++) {
            nodeAdGraphD[] queue=new nodeAdGraphD[n];
            int head=0,rear=0;
            for (int j = 1; j < n+1; j++) {
                if (num[j].color==i){
                    queue[rear++]=num[j];
                    num[j].isVisited=true;
                }
            }
            while (head<rear){
                for (int j = 0; j < queue[head].near.size(); j++) {
                    nodeAdGraphD tar=queue[head].near.get(j);
                    if (!tar.isVisited){
                        queue[rear++]=tar;
                        tar.isVisited=true;
                        tar.colorNum[i-1]=queue[head].colorNum[i-1]+1;
                    }
                }
                head++;
            }
            for (int j = 0; j < n; j++) {
                queue[j].isVisited=false;
            }
        }
        for (int i = 1; i < num.length ; i++) {
            System.out.print(num[i].getMin(c)+" ");
        }

    }

}
class nodeAdGraphD{
    ArrayList<nodeAdGraphD> near=new ArrayList<>();
    long color=0;
    long[]colorNum;
    boolean isVisited=false;

    public nodeAdGraphD(long color,long k) {
        this.color = color;
        this.colorNum=new long[(int)k];
    }
    public long getMin(int c){
        long[] tem=new long[colorNum.length];
        Sort(colorNum,tem,0,colorNum.length-1);
        long ans=0;
        for (int i = 0; i < c; i++) {
            ans+=colorNum[i];
        }
        return ans;
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