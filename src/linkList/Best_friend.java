package linkList;


import java.util.Scanner;

public class Best_friend {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            long n=sc.nextInt();
            nodeD[] nodeDS=new nodeD[(int) n];
            nodeD[] unchange=new nodeD[(int)n];
            for (int j = 0; j <n ; j++) {
                nodeDS[j]=new nodeD(sc.nextInt());
                unchange[j]=nodeDS[j];
            }
            nodeD[] ans=new nodeD[(int)n];
            Sort(nodeDS,ans,0,n-1);
            nodeD clo=nodeDS[0];int m=0;
            while (clo!=nodeDS[nodeDS.length-1]){
                nodeDS[m+1].ex=clo;
                clo.next=nodeDS[m+1];
                clo=clo.next;
                m++;
            }
            long[] ansList=new long[(int) (n/2+n%2)];
            nodeD median=nodeDS[(int) (n/2+n%2)-1];
            if (n%2!=1){
                Remove(unchange[(int) n-1]);
                if (unchange[(int) n-1].val<=median.val){
                    median=median.next;
                }
            }
            ansList[ansList.length-1]= median.val;
            for (int j = ansList.length-2; j >=0 ;j--) {
                int a=unchange.length-2*(ansList.length-1-j);
                Remove(unchange[a]);Remove(unchange[a+1]);
                if (unchange[a].val>=median.val&&unchange[a+1].val>=median.val){
                    median=median.ex;
                } else if (unchange[a].val<=median.val&&unchange[a+1].val<=median.val) {
                    median=median.next;
                } else if (unchange[a].val==median.val&&unchange[a+1].val==median.val) {
                    median=median;
                }
                ansList[j]= median.val;
            }
            for (int j = 0; j < ansList.length; j++) {
                System.out.print(ansList[j]+" ");
            }
            System.out.println("");
        }
    }
    public static void Remove(nodeD node){
        if (node.ex!=null){
            node.ex.next=node.next;
        }
        if (node.next!=null){
            node.next.ex=node.ex;
        }
    }
    public static void Sort(nodeD[]num, nodeD[]num2, long left, long right){
        if (left<right){
            long mid=(left+right)/2;
            Sort(num,num2,left,mid);
            Sort(num,num2,mid+1,right);
            Merge(num,num2,left,right,mid);
        }
    }
    public static void Merge(nodeD[]num,nodeD[]num2,long left,long right,long mid){
        int i=(int)left,j=(int)mid+1,m=(int) left;
        while (i<=mid&&j<=right){
            num2[m++]=num[i].val<num[j].val?num[i++]:num[j++];
        }
        while (i<=mid){
            num2[m++]=num[i++];
        }
        while (j<=right){
            num2[m++]=num[j++];
        }
        for (int k = (int) left; k <=right ; k++) {
            num[k]=num2[k];
        }
    }
}
class nodeD{
    long val;
    nodeD ex;
    nodeD next;
    public nodeD(long val){
        this.val=val;
    }
}
