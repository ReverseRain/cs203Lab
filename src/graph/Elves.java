package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Elves {
    public static void main(String[] args) {
        QReaderF sc=new QReaderF();
        QWriterF out=new QWriterF();
        int t=sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n=sc.nextInt(),m=sc.nextInt();
            nodeGraphF[] num=new nodeGraphF[n+1];
            for (int j = 1; j < n+1; j++) {
                num[j]=new nodeGraphF();
                num[j].a=sc.nextInt();
                num[j].b= sc.nextInt();
            }
            for (int j = 0; j <m ; j++) {
                int u= sc.nextInt();
                int v=sc.nextInt();
                num[u].children.add(num[v]);
                num[v].father.add(num[u]);
                num[v].u++;
            }
            nodeGraphF[] queue=new nodeGraphF[n];
            int start=0,end=0;
            for (int j = 1; j < n+1; j++) {
                if (num[j].u==0&&!num[j].isVisited){
                    queue[end++]=num[j];
                    num[j].isVisited=true;
                    for (int k = 0;
                         k < num[j].children.size(); k++) {
                        num[j].children.get(k).u--;
                    }
                }
            }
            while (start<end){
//                System.out.println(start);
                for (int j = 0; j < queue[start].children.size(); j++) {
                    if (queue[start].children.get(j).u==0
                            &&!queue[start].children.get(j).isVisited){
                        queue[end++]=queue[start].children.get(j);
                        queue[start].children.get(j).isVisited=true;
                        for (int k = 0; k < queue[start].children.get(j).children.size(); k++) {
                            queue[start].children.get(j).children.get(k).u--;
                        }
                    }
                }
                start++;
            }
            start=0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < queue[j].father.size(); k++) {
                    queue[j].f=queue[j].f+(long)((queue[j].father.get(k).f+queue[j].father.get(k).a)%(1E9+7));
                }
            }
            long ans=0;
            for (int j = 0; j < n; j++) {
                ans=(long)((ans+queue[j].f*queue[j].b%(1E9+7))%(1E9+7));
            }
            out.println(ans);
        }
        out.close();
    }
}
class nodeGraphF{
    long a=0;
    long b=0;
    long u=0;
    long f=0;
    boolean isVisited=false;
    ArrayList<nodeGraphF>father=new ArrayList<>();
    ArrayList<nodeGraphF> children=new ArrayList<>();
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
class QWriterF implements Closeable {
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