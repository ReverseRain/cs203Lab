package stack_queue;

import java.io.*;
import java.util.StringTokenizer;

public class ExcitingSpider {
    public static void main(String[] args) {
        QReader3 sc=new QReader3();
        QWriterII out=new QWriterII();
        int T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n=sc.nextInt();
            QueueD stack=new QueueD(out);
            QueueD answer=new QueueD(out);
            int x=0;
            booleanStack booleanStack=new booleanStack(n);
            for (int j = 0; j < n; j++) {
                if (stack.getTail() != -1) {
                    while (booleanStack.getStart() >= stack.getTail() - 1) {
                        answer.enQueue(stack.getTail());
                        stack.deQueue2();
                        if (stack.getTail()==-1){
                            break;
                        }
                    }
                }
                x=sc.nextInt();
                stack.enQueue(x);
                booleanStack.push(x-1);
            }
            while (stack.getTail()!=-1){
                answer.enQueue(stack.getTail());
                stack.deQueue2();
            }
            while (answer.tail!=answer.start){
                answer.deQueue();
            }

            out.println("");
        }
        out.close();
    }
    public static boolean isFirst(Boolean[] num,int x){
        for (int i = x-1; i >=0 ; i--) {
            if (num[i]==null){
                return false;
            }
        }
        return true;
    }
}
class QueueD{
    int[] num=new int[300001];
    int start=0;
    int tail=0;
    QWriterII out;
    public QueueD(QWriterII out){
        this.out=out;
    }
    void enQueue(int x){
        num[tail++]=x;
    }
    void deQueue(){
        if (start!=tail){
        out.print(num[start++]+" ");
        }
    }
    void deQueue2(){
        if (tail!=0){
            tail--;
        }
    }
    int getTail(){
        if (tail!=0){
            return num[tail-1];
        }else return -1;
    }
}
class booleanStack{
    Boolean[] num;
    int cur=0;
    public booleanStack(int n){
        this.num=new Boolean[n];
    }
    void push(int x){
        num[x]=true;
        while (num[cur]!=null){
            cur++;
            if (cur>=num.length){
                break;
            }
        }
    }
    int getStart(){
        return cur;
    }

}
class QWriterII implements Closeable {
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
