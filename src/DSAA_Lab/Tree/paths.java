package DSAA_Lab.Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class paths {
    public static void main(String[] args) {
        QReaderB sc = new QReaderB();
        QWriterB out = new QWriterB();
        long n = sc.nextInt(), num = sc.nextInt();
        NodeTree1[] list = new NodeTree1[(int) n + 1];
        long u, v;
        int w;
        list[1] = new NodeTree1(1);
        for (int i = 0; i < n - 1; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            w=sc.nextInt();
            if (list[(int) u] == null) {
                list[(int) u] = new NodeTree1(u);
            }
            if (list[(int) v] == null) {
                list[(int) v] = new NodeTree1(list[(int) u], v);

            }
            list[(int) u].children.add(list[(int) v]);
            list[(int) v].children.add(list[(int) u]);
            list[(int)u].length.add(w);
            list[(int)v].length.add(w);
        }
        NodeTree1[] queue=new NodeTree1[(int)n];
        int front=0,rear=0;
        queue[rear++]=list[1];
        list[1].isVisited=true;
        while (front<n){
            for (int i = 0; i < queue[front].children.size(); i++) {
                if (queue[front].children.get(i).isVisited==false){
                queue[rear++]=queue[front].children.get(i);
                queue[front].children.get(i).isVisited=true;
                }else {
                    queue[front].parent=queue[front].children.get(i);
                    queue[front].weight=queue[front].length.get(i);
                    queue[front].children.remove(queue[front].children.get(i));
                    i--;
                }
            }
            front++;
        }
        long ans = search(list[1], 0, num);
        if (n == 1) {
            out.println(0);
        } else {
            out.println(ans);
        }
        out.close();
    }

    public static long search(NodeTree1 root, long ans, long target) {
        if (root.parent != null) {
            root.path = root.parent.path + root.weight;
        }
        if (root.children.size() == 0 && root.path == target) {
            ans++;
            return ans;
        } else {
            for (int i = 0; i < root.children.size(); i++) {
                ans = search(root.children.get(i), ans, target);
            }
        }
        return ans;
    }
}

class QReaderB {
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

class QWriterB implements Closeable {
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

class NodeTree1 {
    ArrayList<NodeTree1> children = new ArrayList<>();
    NodeTree1 parent;
    long val;
    long path = 0;
    ArrayList<Integer> length=new ArrayList<>();
    long weight = 0;
    boolean isVisited=false;

    public NodeTree1(NodeTree1 parent, long val) {
        this.parent = parent;
        this.val = val;
    }

    public NodeTree1(long val) {
        this.val = val;
    }
}
