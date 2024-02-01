package DSAA_Lab.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sum {
    public static void main(String[] args) {
        QReaderE sc=new QReaderE();
        QWriterE out=new QWriterE();
        long t= sc.nextLong();
        for (int i = 0; i < t; i++) {
            n=sc.nextInt();m=sc.nextInt();
            int[] num=new int[n*m];
            for (int j = 0; j < num.length; j++) {
                num[j]=sc.nextInt();
            }
            boolean[] adjacent=new boolean[n*m];
            dfs(0,0,num,adjacent);
            out.println(max);
            max=0;
        }
        out.close();
    }
    static long max=0;
    static int n=0,m=0;
    public static void dfs(int cur_index,long ans,int[] num,boolean[] adjacent){
        if (ans>max){
            max=ans;
        }
        if (cur_index>=num.length){
            return;
        }
         if (!adjacent[cur_index]) {
            ans+=num[cur_index];
            ArrayList<Integer>list=new ArrayList<>();
            list=isAdjacent(cur_index,adjacent,list);
            dfs(cur_index+1,ans,num,adjacent);
             for (int i = 0; i < list.size(); i++) {
                 adjacent[list.get(i)]=false;
             }
            ans-=num[cur_index];
        }
        dfs(cur_index+1,ans,num,adjacent);
    }
    public static ArrayList<Integer> isAdjacent(int cur_index,boolean[]adjacent,ArrayList<Integer> list){
        adjacent[cur_index]=true;
        list.add(cur_index);
        if ((cur_index)%m!=0){
            if (cur_index-1>0&&!adjacent[cur_index-1]){
                adjacent[cur_index-1]=true;
                list.add(cur_index-1);
            }
            if (cur_index-1+m<adjacent.length&&!adjacent[cur_index-1+m]){
                adjacent[cur_index-1+m]=true;
                list.add(cur_index-1+m);
            }
            if (cur_index-1-m>0&&!adjacent[cur_index-1-m]){
                adjacent[cur_index-1-m]=true;
                list.add(cur_index-1-m);
            }
        }
        if ((cur_index+1)%m!=0){
            if (cur_index+1<adjacent.length&&!adjacent[cur_index+1]){
                adjacent[cur_index+1]=true;
                list.add(cur_index+1);
            }
            if (cur_index+1+m<adjacent.length&&!adjacent[cur_index+1+m]){
                adjacent[cur_index+1+m]=true;
                list.add(cur_index+1+m);
            }
            if (cur_index+1-m>0&&!adjacent[cur_index+1-m]){
                adjacent[cur_index+1-m]=true;
                list.add(cur_index+1-m);
            }
        }
        if (cur_index+m<adjacent.length&&!adjacent[cur_index+m]){
            adjacent[cur_index+m]=true;
            list.add(cur_index+m);
        }
        if (cur_index-m>0&&!adjacent[cur_index-m]){
            adjacent[cur_index-m]=true;
            list.add(cur_index-m);
        }
        return list;
    }
}
class QReaderE {
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
class QWriterE implements Closeable {
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

