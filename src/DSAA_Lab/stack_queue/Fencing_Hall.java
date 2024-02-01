package DSAA_Lab.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fencing_Hall {
    public static void main(String[] args) {
        QReaderF sc=new QReaderF();
        long k = sc.nextInt(), n = sc.nextInt();
        long[] num = new long[(int) n];
        long ans = 0;
        downF down = new downF();
        upF up = new upF();
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            down.enQueue(num[i],i);
            up.enQueue(num[i],i);
            if (down.getStart()-up.getStart()<=k){
                ans++;
            }else break;
        }
        for (int i = 1; i <n ; i++) {
            while (true){
                if (ans+i>=n){
                    break;
                }
                down.enQueue(num[i+(int)ans],i+ans);
                up.enQueue(num[i+(int)ans],i+ans);
                while (down.getStartIndex()<i||down.getStartIndex()>i+ans){
                    down.deQueue();
                }
                while (up.getStartIndex()<i||up.getStartIndex()>i+ans){
                    up.deQueue();
                }

                if (down.getStart()- up.getStart()<=k){
                    ans++;
                }else {
                    break;
                }
                if (i+ans>=n){
                    break;
                }
            }
            if (i+1+ans>=n){
                break;
            }
        }

        System.out.println(ans);
    }
}

class downF {
    long[] num = new long[3000001];
    long[] index = new long[3000001];
    long start = 0;
    long tail = 0;

    void enQueue(long x, long dex) {
        if (tail > 0) {
            while (tail - 1 >= start && num[(int) tail - 1] < x) {
                tail--;
            }
        }
        num[(int) tail] = x;
        index[(int) tail] = dex;
        tail++;
    }

    void deQueue() {
        start++;
    }

    long getStart() {
        if (start != tail) {
            return num[(int) start];
        } else return -1;
    }

    long getStartIndex() {
        if (start != tail) {
            return index[(int) start];
        } else return -1;
    }
}

class upF {
    long[] num = new long[3000001];
    long[] index = new long[3000001];
    long start = 0;
    long tail = 0;

    void enQueue(long x, long dex) {
        if (tail > 0) {
            while (tail - 1 >= start && num[(int) tail - 1] > x) {
                tail--;
            }
        }
        num[(int) tail] = x;
        index[(int) tail] = dex;
        tail++;
    }

    void deQueue() {
        start++;
    }

    long getStart() {
        if (start != tail) {
            return num[(int) start];
        } else return -1;
    }

    long getStartIndex() {
        if (start != tail) {
            return index[(int) start];
        } else return -1;
    }
}
class QReaderF {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
