package linkList;

import java.util.Scanner;

public class Black_era {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long T=sc.nextInt();
        while (T>0){
            long n=sc.nextInt(),m=sc.nextInt();
            nodeC head=new nodeC(100000);
            nodeC clo=head;
            nodeC[] num=new nodeC[(int)n];
            for (int i = 0; i < n; i++) {
                nodeC tem=new nodeC(sc.nextInt());
                num[(int)tem.val]=tem;
                tem.ex=clo;
                clo.next=tem;
                clo=clo.next;
            }
            nodeC tail=new nodeC(-100000);
            clo.next=tail;
            int x1=0,y1=0,x2=0,y2=0;
            nodeC first,last;
            for (int i = 0; i < m; i++) {
                x1=sc.nextInt();y1=sc.nextInt();
                x2=sc.nextInt();y2=sc.nextInt();
                first=num[x1].ex;last=num[y1].next;
                if (first!=num[y2]&&last!=num[x2]){
                    num[x1].ex=num[x2].ex;num[x2].ex.next=num[x1];
                    num[y1].next=num[y2].next;num[y2].next.ex=num[y1];
                    num[x2].ex=first;first.next=num[x2];
                    num[y2].next=last;last.ex=num[y2];
                }else if (first==num[y2]){
                    num[x1].ex=num[x2].ex;num[x2].ex.next=num[x1];
                    num[y1].next=num[x2];num[x2].ex=num[y1];
                    num[y2].next=last;last.ex=num[y2];
                } else if (last==num[x2]) {
                    num[y1].next=num[y2].next;num[y2].next.ex=num[y1];
                    num[x1].ex=num[y2];num[y2].next=num[x1];
                    num[x2].ex=first;first.next=num[x2];
                }
            }
            clo=head;
            while (clo.next!=tail){
                System.out.print(clo.next.val+" ");
                clo=clo.next;
            }
            System.out.println("");
        T--;
        }
    }
}
class nodeC{
    nodeC ex;
    nodeC next;
    long val;
    public nodeC(long val){
        this.val=val;
    }
}
