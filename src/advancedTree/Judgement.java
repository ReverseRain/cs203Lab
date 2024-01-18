package advancedTree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Judgement {
    public static void main(String[] args) {
        QReaderA sc=new QReaderA();
        QWriterA out=new QWriterA();
        int t=sc.nextInt();
        for (int i = 1; i < t+1; i++) {
            int n=sc.nextInt();
            nodeTreeA[] list=new nodeTreeA[n+1];
            for (int j = 1; j < n+1; j++) {
                list[j]=new nodeTreeA(sc.nextInt());
            }
            for (int j = 0; j < n-1; j++) {
                int x=sc.nextInt(),y=sc.nextInt();
                list[x].children.add(list[y]);
                list[y].isRoot=false;
            }
            nodeTreeA root=null;
            for (int j = 1; j < n+1; j++) {
                if (list[j].isRoot){
                    root=list[j];
                }
            }
            boolean isHeap=true,isBig=false;
            nodeTreeA[] queue=new nodeTreeA[n];
            int front=0,rear=0;
            queue[rear++]=root;
            if (root.children.size()!=0&&root.children.get(0).value>root.value){
                isBig=true;
            }
            while (front<rear){
                if (queue[front].children.size()==1&&rear!=n-1){
                    isHeap=false;
                    break;
                }else if (queue[front].children.size()==0&&rear<n){
                    isHeap=false;
                    break;
                }else if (queue[front].children.size()>2){
                    isHeap=false;
                    break;
                }else {
                    for (int j = 0; j < queue[front].children.size(); j++) {
                        if (isBig){
                            if (queue[front].children.get(j).value<queue[front].value){
                                isHeap=false;
                            }
                        }else {
                            if (queue[front].children.get(j).value>queue[front].value){
                                isHeap=false;
                            }
                        }
                        queue[rear++]=queue[front].children.get(j);
                    }
                    front++;
                }
            }
            if (isHeap){
                System.out.println("Case #"+i+": YES");
            }else {
                System.out.println("Case #"+i+": NO");
            }
        }
    }

}
class nodeTreeA{
    ArrayList<nodeTreeA> children =new ArrayList<>();
    int value=0;
    boolean isRoot=true;

    public nodeTreeA(int value) {
        this.value = value;
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
