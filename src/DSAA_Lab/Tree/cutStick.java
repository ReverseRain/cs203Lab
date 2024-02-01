package DSAA_Lab.Tree;

import java.util.Scanner;

public class cutStick {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n=sc.nextInt();
            NodeTreeD[] list=new NodeTreeD[n];
            for (int j = 0; j < n; j++) {
                list[j]=new NodeTreeD(sc.nextInt());
            }
            NodeTreeD[] tem=new NodeTreeD[n];
            long ans=0;
            for (int j = 0; j < n-1; j++) {
                Sort(list,tem,j,n-1);
                NodeTreeD te=new NodeTreeD(list[j].length+list[j+1].length);
                list[j+1]=te;
                ans=ans+te.length;
            }
            System.out.println(ans);
        }
    }
    public static void Merge(NodeTreeD[]num,NodeTreeD[]ans,long left,long right,long mid){
        int i=(int)left,j=(int)mid+1,m=(int)left;
        while (i<=mid&&j<=right){
            ans[m++]=num[i].length<=num[j].length?num[i++]:num[j++];
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
    static void Sort(NodeTreeD[]num,NodeTreeD[]ans,long left,long right){
        if (left<right){
            long mid=(left+right)/2;
            Sort(num,ans,left,mid);
            Sort(num,ans,mid+1,right);
            Merge(num,ans,left,right,mid);
        }
    }
}
class NodeTreeD{
    int length;

    public NodeTreeD(int length) {
        this.length = length;
    }
}
