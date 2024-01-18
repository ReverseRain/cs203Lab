package string;

import java.io.*;
import java.util.StringTokenizer;

public class necklace {
    public static void main(String[] args) {
        QReaderD reader=new QReaderD();
        QWriterD out=new QWriterD();
        int n=reader.nextInt();
        for (int i = 0; i < n; i++) {
            String test= reader.next();
            long[]nextList=new long[test.length()];
            getNext(test,nextList);
            long min=test.length()-nextList[test.length()-1];
            if (min==test.length()&&min!=1){
                out.println(min);//最小的循环子串？
            }else if (test.length()%min==0){
                out.println(0);
            }else {
                out.println(min-test.length()%min);
            }

        }
        out.close();
    }
    public static void getNext(String test, long[] nextList) {
        nextList[0] = 0;
        for (long i = 1, j = 0; i < test.length(); i++) {
            while (j != 0 && test.charAt((int) i) != test.charAt((int) j)) {
                j=nextList[(int) j-1];//这是什么意思？
            }
            if (test.charAt((int)i)==test.charAt((int)j)) j++;
            nextList[(int)i]=j;
        }
    }
}
class QWriterD implements Closeable {
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

class QReaderD {
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
