package DSAA_Lab.linkList;

import java.util.Scanner;

public class shopping {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextInt(),m=sc.nextInt();
        long ans=0,cnt=0;
        nodeB2 head=new nodeB2(sc.nextInt());
        nodeB2 col=head;
        for (int i = 0; i < n-1; i++) {
            col.next=new nodeB2(sc.nextInt());
            col=col.next;
        }
        col.next=head;
        col=col.next;
        while (cnt<n){
            if (m>=col.val){
                m-= col.val;
                ans++;
                cnt=0;
                col=col.next;
            }else {
                cnt++;
            }
        }
        System.out.println(ans);
    }
}
class nodeB2{
    nodeB2 next;int val;

    public nodeB2(int val) {
        this.val = val;
    }
}
