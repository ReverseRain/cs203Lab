package linkList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Non_decreasing_sequence {
    public static void main(String[] args) {
        QReader3 sc = new QReader3();
        QWriter3 out = new QWriter3();
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            nodeF head = new nodeF(-1000000);
            nodeF clo = head;
            for (int j = 0; j < n; j++) {
                nodeF tem = new nodeF(sc.nextInt());
                tem.ex = clo;
                clo.next = tem;
                clo = clo.next;
            }
            nodeF tail = new nodeF(1000000001);
            tail.ex = clo;
            clo.next = tail;
            clo = head;
            ArrayList<nodeF> list = new ArrayList<>();
            Boolean decreasing = false;
            nodeF start = null, end = null;
            while (clo.next != null) {
                if (clo.next.val < clo.val) {
                    if (!decreasing) {
                        decreasing = true;
                        start = clo;
                        if (!list.contains(clo.ex)) {
                            list.add(clo.ex);
                        }
                    }
                } else if (decreasing) {
                    end = clo;
                    decreasing = false;
                }
                if (end != null && start != null) {
                    start.ex.next = end.next;
                    end.next.ex = start.ex;
                    end = null;
                    start = null;
                }
                clo = clo.next;
            }
            while (list.size() != 0) {
                if (list.get(0).next.val < list.get(0).val) {
                    clo=list.get(0).next;
                    start=list.get(0);end=list.get(0).next;
                    list.remove(list.get(0));
                    while (list.contains(clo)){
                        if (clo.next.val< clo.val){
                            end=clo.next;
                        }else {
                            break;
                        }
                        list.remove(clo);
                        clo=clo.next;
                    }
                    start.ex.next = end.next;
                    end.next.ex = start.ex;
                    if (!list.contains(start.ex)) {
                        list.add(start.ex);
                    }
                }else {
                    list.remove(list.get(0));
                }

            }
            clo = head;
            while (clo.next != tail) {
                System.out.print(clo.next.val + " ");
                clo = clo.next;
            }
            System.out.println("");
        }
    }
}

class nodeF {
    long val;
    nodeF ex;
    nodeF next;
    nodeF next2;

    public nodeF(long val) {
        this.val = val;
    }
}

class QWriter3 implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}

class QReader3 {
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
