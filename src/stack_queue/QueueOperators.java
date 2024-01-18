package stack_queue;

import java.util.Scanner;

public class QueueOperators {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        queue pro2=new queue();
        for (int i = 0; i < n; i++) {
            String op=sc.next();int x=sc.nextInt();
            switch (op){
                case "E":
                    pro2.add(x);
                    break;
                case "D":
                    pro2.pop(x);
                    System.out.println(pro2.num[pro2.start]);
                    break;
            }
        }
    }

}
class queue{
    int[] num=new int[100000];
    int cur=0;
    int start=0;
    void add(int x){
        num[cur++]=x;
    }
    void pop(int x){
        start=start+x;
    }
}
