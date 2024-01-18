package string;

import java.util.Scanner;

public class proF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] list = new String[26];
        for (int i = 0; i < 26; i++) {
            list[i] = sc.next();
        }
        String test = sc.next();
        long mid = test.length() / 2 + test.length() % 2;
        String lestString =test.substring(0,(int)mid);
        String rightString=test.substring((int)mid,test.length());
        long ans=kmpMatching(lestString,rightString,list);
        ans=mid+ans;
        System.out.println(ans);
    }

    public static long kmpMatching(String left, String right, String[] list) {
        long n = left.length(), m = right.length();
        long[] nextList = new long[(int) n];
        getNext(left, nextList);
        long q = 0;
        for (int i = 0; i < m; i++) {
            while (q != 0 && !list[right.charAt(i) - 'a']
                    .equals(String.valueOf(left.charAt((int) q)))) {
                q = nextList[(int) q - 1];
            }
            if (list[right.charAt(i) - 'a']
                    .equals(String.valueOf(left.charAt((int) q)))) {
                q++;
            }
            if (q == n) {
                break;
            }
        }
        return m-q;
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
