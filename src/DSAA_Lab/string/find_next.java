package DSAA_Lab.string;

import java.util.Scanner;

public class find_next {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String test = sc.next();
        long[] nextList = new long[test.length()];
        getNext(test, nextList);
        for (int i = 0; i < test.length(); i++) {
            System.out.println(nextList[i]);
        }
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
