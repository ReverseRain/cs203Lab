package advancedTree;

import java.io.*;
import java.util.StringTokenizer;

public class smallest {
    public static void main(String[] args) {
        QReaderC sc=new QReaderC();
        QWriterC out =new QWriterC();
        long n=sc.nextInt(),m=sc.nextInt(),k=sc.nextInt();
        long[] aList=new long[(int)n],bList=new long[(int)m];
        for (int i = 0; i <n ; i++) {
            aList[i]=sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            bList[i]=sc.nextInt();
        }
        long[] ans=new long[(int)n];
        Sort(aList,ans,0,n-1);
        nodeTreeC[]heap=new nodeTreeC[(int)m+1];
        long size=0;
        for (int i = 0; i < m; i++) {
            insert(heap,size,new nodeTreeC(bList[i]*aList[0],0,i));
            size++;
        }
        for (int i = 0; i < k; i++) {
            long b=heap[1].b,a=heap[1].a;
            out.print(heap[1].value+" ");
            delete(heap,size);
            size--;
            if (a<n-1){
                insert(heap,size,new nodeTreeC(bList[(int)b]*aList[(int)a+1],a+1,b));
                size++;
            }
        }
        out.close();
    }
    public static void insert(nodeTreeC[] heap,long size,nodeTreeC value){
        size++;
        heap[(int)size]=value;
        long t=size;
        while (t>1){
            if (heap[(int)t].value<heap[(int)t/2].value){
                nodeTreeC tem=heap[(int)t/2];
                heap[(int)t/2]=heap[(int)t];
                heap[(int)t]=tem;
                t=t/2;
            }else {
                break;
            }
        }
    }
    public static void delete(nodeTreeC[]heap,long size){
        long t=1;
        heap[1]=heap[(int)size];
        size--;
        while (2*t<=size){
            if (2*t+1<=size&&heap[(int) t].value > heap[(int) (2 * t + 1)].value&&heap[(int) (2 * t)].value > heap[(int) (2 * t + 1)].value){
                nodeTreeC tem = heap[(int) (2 * t + 1)];
                heap[(int) (2 * t + 1)] = heap[(int) t];
                heap[(int) t] = tem;
                t = 2 * t + 1;
            } else if (heap[(int) t].value > heap[(int) (2 * t)].value) {
                nodeTreeC tem = heap[(int) (2 * t)];
                heap[(int) (2 * t)] = heap[(int) t];
                heap[(int) t] = tem;
                t = 2 * t;
            }else {
                break;
            }
        }
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
class nodeTreeC{
    long value=0;

    public nodeTreeC(long value, long a, long b) {
        this.value = value;
        this.a = a;
        this.b = b;
    }

    long a=0;
    long b=0;
}
class QReaderC {
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
class QWriterC implements Closeable {
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
