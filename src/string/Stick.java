package string;

import java.io.*;
import java.util.StringTokenizer;

public class Stick {
    public static int d=139;
    public static void main(String[] args) {
        QReaderE reader=new QReaderE();
        QWriterE out =new QWriterE();
        String s1= reader.next(),s2= reader.next();
        long n;
        if (s1.length()>s2.length()){
            n=s1.length();
        }else {
            n=s2.length();
        }
        long right=n,left=0,mid=0;
        while (left<right){
            mid=(left+right)/2+1;
            if (check(s1,s2,mid)){
                left=mid;
            }else {
                right=mid-1;
            }
        }
        System.out.println(left);

    }
    static Boolean check(String s1,String s2,long k){
        if (s1.length()-k+1<=0||s2.length()-k+1<=0){
            return false;
        }
        long[] map1=getChart(s1,k);
        long[] tem=new long[map1.length];
        long[] map2=getChart(s2,k);
        Sort(map1,tem,0,map1.length-1);
        long right=map1.length-1,left=0,mid=0;
        for (int i = 0; i < map2.length; i++) {
            while (left<right){
                mid=(left+right)/2+1;
                if (map1[(int)mid]==map2[i]){
                    return true;
                } else if (map1[(int)mid]>map2[i]) {
                    right=mid-1;
                }else {
                    left=mid;
                }
            }
            if (map1[(int)right]==map2[i]){
                return true;
            }
            left=0;right=map1.length-1;
        }
        return false;
    }

    static long[] getChart(String s,long k){
        long[] ans=new long[s.length()-(int)k+1];
        for (int i = 0; i < k; i++) {
            ans[0]=ans[0]*d+s.charAt(i);
        }
        long m=1;
        for (int i = 0; i < k-1; i++) {
            m=m*d;
        }
        for (int i = 1; i < ans.length ; i++) {
            ans[i]=(ans[i-1]-m*s.charAt(i-1))*d+s.charAt(i+(int)k-1);
        }
        return ans;
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

