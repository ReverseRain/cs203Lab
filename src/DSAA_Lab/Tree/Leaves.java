package DSAA_Lab.Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Leaves {
    public static void main(String[] args) {
        QReaderA sc = new QReaderA();
        QWriterA out = new QWriterA();
        long n = sc.nextInt();
        long u, v;
        NodeTreeA[] list = new NodeTreeA[(int) n+1];
        for (int i = 0; i < n - 1; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            if (list[(int) u] == null) {
                list[(int) u] = new NodeTreeA(u);
            }
            list[(int) v]= new NodeTreeA(v);
            list[(int) u].children.add(list[(int) v]);
        }
        ArrayList<NodeTreeA>ans=new ArrayList<>();
        for (int i = 1; i < list.length; i++) {
            if (list[i].children.size()==0){
                ans.add(list[i]);
            }
        }
        long[] answer=new long[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i]=ans.get(i).key;
        }
        long[] te=new long[answer.length];
        Sort(answer,te,0,answer.length-1);
        for (int i = 0; i < answer.length; i++) {
            out.print(answer[i]+" ");
        }
        out.close();
    }
    public static void Merge(long[]num,long[]ans,long left,long right,long mid){
        int i=(int)left,j=(int)mid+1,m=(int)left;
        while (i<=mid&&j<=right){
            ans[m++]=num[i]<=num[j]?num[i++]:num[j++];
        }

        while (i<=mid){
            ans[m++]=num[i++];
        }
        while (j<=right){
            ans[m++]=num[j++];
        }
        for (int k = (int)left; k <=right ; k++) {
            num[k]=ans[k];
        }
    }
    static void Sort(long[]num,long[]ans,long left,long right){
        if (left<right){
            long mid=(left+right)/2;
            Sort(num,ans,left,mid);
            Sort(num,ans,mid+1,right);
            Merge(num,ans,left,right,mid);
        }
    }
}

class NodeTreeA {
    ArrayList<NodeTreeA> children = new ArrayList<>();
    long key;

    public NodeTreeA(long key) {
        this.key = key;
    }
}

class QReaderA {
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

class QWriterA implements Closeable {
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
