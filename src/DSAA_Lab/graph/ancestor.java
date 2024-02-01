package DSAA_Lab.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ancestor {
     static long cnt=0;
    public static void main(String[] args) {
        QReaderD sc=new QReaderD();
        QWriterD out=new QWriterD();
        int t=sc.nextInt();
        for (int i = 0; i <t ; i++) {
            long n=sc.nextInt(),m=sc.nextInt();
            nodeGraphD[] tree=new nodeGraphD[(int) n+1];
            for (int j = 1; j <n+1 ; j++) {
                tree[j]=new nodeGraphD();
            }
            for (int j = 0; j < n-1; j++) {
                int x=sc.nextInt(),y=sc.nextInt();
                tree[y].children.add(tree[x]);
                tree[x].parent=tree[y];
            }
            nodeGraphD root=null;
            for (int j = 1; j < n+1; j++) {
                if (tree[j].parent==null){
                    root=tree[j];
                    break;
                }
            }
            inOrder(root);
            for (int j = 0; j < m; j++) {
                long x=sc.nextInt(),y=sc.nextInt();
                if (tree[(int)y].first<=tree[(int)x].first&&tree[(int)x].second<=tree[(int)y].second){
                    System.out.println("Yes");
                }else {
                   System.out.println("No");
                }
            }
            cnt=0;
        }
        out.close();
    }
    public static void inOrder(nodeGraphD root){
        if (root==null){
            return ;
        }
        root.first=++cnt;
        for (int i = 0; i < root.children.size(); i++) {
            inOrder(root.children.get(i));
        }
        root.second=++cnt;
    }
    public static void findAncestor(nodeGraphD target,nodeGraphD node,QWriterD out){
        while (node.parent!=null){
            if (node.parent==target){
                out.println("Yes");
                return;
            }
            node=node.parent;
        }
        out.println("No");
    }
}
class nodeGraphD{
    nodeGraphD parent=null;
    long first;
    long second;
    ArrayList<nodeGraphD> children=new ArrayList<>();
}
class QReaderD {
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
class QWriterD implements Closeable {
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
