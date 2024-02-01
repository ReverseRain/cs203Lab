package DSAA_Lab.sorting;
import java.io.*;
import java.util.StringTokenizer;

public class Merge {
    public  static void main(String[] args) {
        QReader sc=new QReader();
        QWriter out=new QWriter();
        long t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            long n = sc.nextInt(), m = sc.nextInt();
            long[] num=new long[(int)(n+m)];
            for (int j = 0; j < n+m; j++) {
                num[j]=sc.nextInt();
            }
            long[] ans = new long[(int) (m + n)];
            ans=Merge_(num,ans,0,n+m-1,n-1);
            for (int k = 0; k < ans.length; k++) {
                out.print(ans[k]+" ");
            }
            out.println(" ");
        }
        out.close();
    }
    public static long[] Merge_(long[]num,long[]ans,long left,long right,long mid){

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
        return ans;
    }
    public static class QReader {
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
    public static class QWriter implements Closeable {
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
}
