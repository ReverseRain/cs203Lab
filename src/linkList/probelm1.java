package linkList;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class probelm1 {//排列是有序的吗
    //自己再写一个？
    public static void main(String[] args) {
        QReader sc=new QReader();
        QWriter out=new QWriter();
        long n=sc.nextInt(),m=sc.nextInt();
        node head =new node(1000000,100000000);
        node cur=head;
        for (int i = 0; i < n; i++) {
            node temp=new node(sc.nextInt(),sc.nextInt());
            cur.next=temp;
            cur=cur.next;
        }
        node tail=new node(-10000000,-10000000);
        cur.next=tail;
        cur=head;
        long coe2=0,exp2=0;
        for (int i = 0; i < m; i++) {
            coe2=sc.nextInt();exp2= sc.nextInt();
            while (true){
                if (exp2>cur.next.exp)break;
                cur=cur.next;
            }
            if (exp2== cur.exp){
                cur.coe= cur.coe+coe2;
            }else {
                //insert node
                node tem=new node(coe2,exp2);
                tem.next=cur.next;
                cur.next=tem;
            }
        }
        cur=head;
        long ans=0;
        while (cur.next!=tail){
            if (cur.next.coe!=0){
                ans=ans+1;
            }
            cur=cur.next;
        }
        out.println(ans);
        cur=head;
        while (cur.next!=tail){
            if (cur.next.coe!=0){
                out.println(cur.next.coe+" "+cur.next.exp);
            }
            cur=cur.next;
        }
        out.close();
    }
}
class node{
    long coe;long exp;
    node next;
    public node(long coe,long exp){
        this.coe=coe;
        this.exp=exp;
    }
}

class QReader {
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
class QWriter implements Closeable {
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
