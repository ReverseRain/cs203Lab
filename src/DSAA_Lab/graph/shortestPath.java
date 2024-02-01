package DSAA_Lab.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class shortestPath {
    public static void main(String[] args) {
        QReaderB sc=new QReaderB();
        QWriterB out=new QWriterB();
        int t=sc.nextInt();
        for (int i = 0; i < t; i++) {
            long n=sc.nextInt(),m=sc.nextInt(),s=sc.nextInt();
            nodeGraphB[] num=new nodeGraphB[(int)n+1];
            for (int j = 0; j < n+1; j++) {
                num[j]=new nodeGraphB();
            }
            for (int j = 0; j < m; j++) {
                long x=sc.nextInt(),y=sc.nextInt();

                num[(int) x].neighbor.add(num[(int)y]);
                num[(int)y].neighbor.add(num[(int)x]);
            }
            nodeGraphB top=num[(int)s];
            nodeGraphB[] queue=new nodeGraphB[(int)n];
            long head=0,end=0;
            queue[(int)end++]=top;
            top.isVisited=true;
            top.path=0;
            while (head<end){
                for (int j = 0; j < queue[(int)head].neighbor.size(); j++) {
                    if (!queue[(int)head].neighbor.get(j).isVisited){
                        queue[(int)end++]=queue[(int)head].neighbor.get(j);
                        queue[(int)head].neighbor.get(j).isVisited=true;
                        queue[(int)head].neighbor.get(j).path=queue[(int)head].path+1;
                    }
                }
                head++;
            }
            for (int j = 1; j < n+1; j++) {
                out.print(num[j].path+" ");
            }
            out.println("");
        }
        out.close();
    }
}
class nodeGraphB{
    ArrayList<nodeGraphB> neighbor=new ArrayList<>();
    boolean isVisited=false;
    long path=-1;

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
