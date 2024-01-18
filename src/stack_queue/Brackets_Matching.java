package stack_queue;

import java.util.Scanner;

public class Brackets_Matching {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            String str = sc.next();
            char[] chars = str.toCharArray();
            stack1 mystack = new stack1();
            for (int j = 0; j < chars.length; j++) {
                switch (chars[j]) {
                    case '(':
                        mystack.push(1);
                        break;
                    case ')':
                        mystack.pop(1);
                        break;
                    case '{':
                        mystack.push(2);
                        break;
                    case '}':
                        mystack.pop(2);
                        break;
                    case '[':
                        mystack.push(3);
                        break;
                    case ']':
                        mystack.pop(3);
                        break;
                }
                if (!mystack.match && mystack.is) {
                    break;
                }
            }
            if (mystack.match) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}

class stack1 {
    int top = 0;
    int[] num = new int[300000];
    Boolean match = false;
    Boolean is = false;

    void push(int x) {
        num[top++] = x;
        is = false;
        match=false;
    }

    void pop(int x) {
        is = true;
        if (top > 0) {
            top--;
            if (num[top] != x) {
                match = false;
            } else {
                match = true;
            }
        } else {
            match = false;
        }
    }
}

