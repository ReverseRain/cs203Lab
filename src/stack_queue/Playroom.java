package stack_queue;

import java.util.Scanner;

public class Playroom {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String line=sc.nextLine();
        char[] chars=line.toCharArray();
        stack mystack=new stack();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]){
                case '(':
                    mystack.pop();
                    break;
                case ')':
                    mystack.match();
                    break;
            }
        }
        System.out.println(mystack.getAns());
    }
}
class stack{
    int mod=514329;
    int[] num=new int[100000];
    int top=0;
    long ans=0;
    void pop(){
        num[top++]=0;
    }
    void match(){
        top--;
        if (num[top]==0){
            if (top>0){
                num[top-1]=(num[top-1]+1)%mod;
            }else {
                ans=(ans+1)%mod;
            }
        }else {
            if (top>0){
                num[top-1]=(num[top-1]+2*num[top])%mod;
            }else {
                ans=(ans+2*num[top])%mod;
            }
        }
    }
    long getAns(){
        return ans;
    }
}
