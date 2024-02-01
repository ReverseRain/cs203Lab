package DSAA_Lab.string;

import java.io.*;
import java.util.StringTokenizer;

public class FSA {
    public static void main(String[] args) {
        QReader4 reader = new QReader4();
        QWriter4 out = new QWriter4();
        String test = reader.next();
        int[][] ans = new int[test.length()][26];
        char[] num = test.toCharArray();
        long[] next = new long[test.length()];
        getNext(test, next);
        for (int i = 0; i < test.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (j + 'a' == num[i]) {
                    ans[i][j] = i + 1;
                } else {
                    if (i - 1 >= 0) {
                        ans[i][j] = ans[(int) next[i - 1]][j];
                    } else ans[i][j] = ans[(int) next[0]][j];
                }
            }
        }
        for (int i = 0; i < test.length(); i++) {
            for (int j = 0; j < 26; j++) {
                out.print(ans[i][j] + " ");
            }
            out.println("");
        }
    }

    public static void getNext(String test, long[] nextList) {
        nextList[0] = 0;
        for (long i = 1, j = 0; i < test.length(); i++) {
            while (j != 0 && test.charAt((int) i) != test.charAt((int) j)) {
                j = nextList[(int) j - 1];
            }
            if (test.charAt((int) i) == test.charAt((int) j)) j++;
            nextList[(int) i] = j;
        }
    }


}

class QWriter4 implements Closeable {
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

class QReader4 {
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
