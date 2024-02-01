package DSAA_Lab.linkList;



import java.io.*;

import java.util.StringTokenizer;

public class Minimum_Difference {
    public static void main(String[] args) {
        QReaderI sc=new QReaderI();
        QWriterI out=new QWriterI();
        long n = sc.nextInt();
        nodeE[] num=new nodeE[(int) n],unchange=new nodeE[(int)n];
        for (int i = 0; i < n; i++) {
            num[i]=new nodeE(sc.nextInt());
            unchange[i]=num[i];
        }
        nodeE[] num2=new nodeE[num.length];
        Sort(num,num2,0, num.length-1);
        nodeE clo=num[0];
        int m=0;
        while (clo!=num[num.length-1]){
            num[m+1].ex=clo;
            clo.next=num[m+1];
            clo=clo.next;
            m++;
        }
        long diff1=0,diff2=0;
        for (int i = 0; i < n-1; i++) {
            if (unchange[i].next!=null){
                diff1=Math.abs(unchange[i].next.val-unchange[i].val);
            }
            if (unchange[i].ex!=null){
                diff2=Math.abs(unchange[i].val-unchange[i].ex.val);
            }
            if (unchange[i].next!=null&&unchange[i].ex!=null){
                out.print(Math.min(diff1,diff2)+" ");
            } else if (unchange[i].next!=null) {
                out.print(diff1+" ");
            } else if (unchange[i].ex!=null) {
                out.print(diff2+" ");
            }
            Remove(unchange[i]);
        }
        out.close();
    }
    public static void Remove(nodeE node){
        if (node.ex!=null){
            node.ex.next=node.next;
        }
        if (node.next!=null){
            node.next.ex=node.ex;
        }
    }
    public static void Sort(nodeE[]num,nodeE[]num2,long left,long right){
        if (left<right){
            long mid=(left+right)/2;
            Sort(num,num2,left,mid);
            Sort(num,num2,mid+1,right);
            Merge(num,num2,left,right,mid);
        }
    }
    public static void Merge(nodeE[]num,nodeE[]num2,long left,long right,long mid){
        int i=(int)left,j=(int)mid+1,m=(int) left;
        while (i<=mid&&j<=right){
            num2[m++]=num[i].val<num[j].val?num[i++]:num[j++];
        }
        while (i<=mid){
            num2[m++]=num[i++];
        }
        while (j<=right){
            num2[m++]=num[j++];
        }
        for (int k = (int) left; k <=right ; k++) {
            num[k]=num2[k];
        }
    }
}
class nodeE{
    long val;
    nodeE next;
    nodeE ex;
    public nodeE(long val){
        this.val=val;
    }
}
class QReaderI {
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
class QWriterI implements Closeable {
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

