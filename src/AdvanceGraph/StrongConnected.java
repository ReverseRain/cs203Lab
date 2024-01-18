package AdvanceGraph;

import java.util.ArrayList;
import java.util.Scanner;

public class StrongConnected {
    static long cnt=0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        int s=sc.nextInt();
        nodeAdGraphE[] num=new nodeAdGraphE[n+1];
        for (int i = 1; i <n+1 ; i++) {
            num[i]=new nodeAdGraphE(i);
        }
        for (int i = 0; i < m; i++) {
            int u=sc.nextInt(),v=sc.nextInt();
            num[u].near.add(num[v]);
            num[v].inear.add(num[u]);
        }
        nodeAdGraphE[]stack1=new nodeAdGraphE[n];
        int top=0;
        for (int i = 1; i < n+1; i++) {
            if (!num[i].isVisited){
                top=dfs(stack1,num[i],top,true);
            }
        }
        for (int i = 1; i <n+1 ; i++) {
            num[i].isVisited=false;
        }
        nodeAdGraphE[]stack2=new nodeAdGraphE[n];
        top=0;
        for (int i = n-1; i >=0; i--) {
            if (!stack1[i].isVisited){
                cnt++;
                stack1[i].label=cnt;
                top=dfs(stack2,stack1[i],top,false);
            }
        }
        int[] list=new int[(int)(cnt+1)];
        for (int i = 1; i < n+1; i++) {
            for (int j = 0; j < num[i].near.size(); j++) {
                if (num[i].label!=num[i].near.get(j).label){
                    list[(int)(num[i].near.get(j).label)]++;
                }
            }
        }
        long ans=0;
        for (int i = 1; i <cnt+1 ; i++) {
            if (i!=num[s].label&&list[i]==0){
                ans++;
            }
        }
        System.out.println(ans);
    }
    public static int dfs(nodeAdGraphE[]stack,
                           nodeAdGraphE start,int top,boolean inverse){
        start.isVisited=true;
        if (!inverse){
        for (int i = 0; i < start.near.size(); i++) {
            if (!start.near.get(i).isVisited){
                start.inear.get(i).label=cnt;
                top=dfs(stack,start.near.get(i),top,inverse);
            }
        }
        }else{
            for (int i = 0; i < start.inear.size(); i++) {
                if (!start.inear.get(i).isVisited){
                    top=dfs(stack,start.inear.get(i),top,inverse);
                }
            }
        }
        stack[top++]=start;
        return top;
    }
}
class nodeAdGraphE{
    long value;
    boolean isVisited=false;
    long label=0;
    ArrayList<nodeAdGraphE> near=new ArrayList<>();
    ArrayList<nodeAdGraphE> inear=new ArrayList<>();
    public nodeAdGraphE(long value) {
        this.value = value;
    }
}
