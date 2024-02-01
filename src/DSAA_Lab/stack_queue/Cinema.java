package DSAA_Lab.stack_queue;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), p = sc.nextInt(), q = sc.nextInt();
        queueB queue1 = new queueB(p), queue2 = new queueB(q);
        for (int i = 0; i < p; i++) {
            queue1.enQueue(sc.nextInt());

        }
        for (int i = 0; i < q; i++) {
            queue2.enQueue(sc.nextInt());
        }
        int[] ans = new int[n + 1];
        while (true) {
            if (queue1.getStart() != -1) {
                while (ans[queue1.getStart()] != 0) {
                    queue1.deQueue();
                    if (queue1.getStart() == -1) {
                        break;
                    }
                }
            }
            if (queue2.getStart() != -1) {
                while (ans[queue2.getStart()] != 0) {
                    queue2.deQueue();
                    if (queue2.getStart() == -1) {
                        break;
                    }
                }
            }
            if (queue1.getStart() == queue2.getStart()) {
                if (queue1.getStart() != -1 && queue2.getStart() != -1){
                    ans[queue1.getStart()] = ++queue1.ans;
                ++queue2.ans;
                queue1.deQueue();
                queue2.deQueue();
            }
        } else{
            if (queue1.getStart() != -1) {
                ans[queue1.getStart()] = ++queue1.ans;
                queue1.deQueue();
            }
            if (queue2.getStart() != -1) {
                ans[queue2.getStart()] = ++queue2.ans;
                queue2.deQueue();
            }
        }
        if (queue1.getStart() == -1 && queue2.getStart() == -1) {
            break;
        }
    }
        for(
    int i = 1;
    i<n +1;i++)

    {
        System.out.print(ans[i] + " ");
    }
}
}

class queueB {
    int start = 0;
    int tail = 0;
    int[] num;
    int ans = 0;

    public queueB(int n) {
        this.num = new int[n];
    }

    void deQueue() {
        if (start != tail) {
            start++;
        }
    }

    int getStart() {
        if (start != tail) {
            return num[start];
        } else return -1;
    }

    void enQueue(int x) {
        num[tail++] = x;
    }
}
