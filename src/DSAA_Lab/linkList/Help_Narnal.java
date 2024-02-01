package DSAA_Lab.linkList;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Help_Narnal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QWriterII out=new QWriterII();
        int T = sc.nextInt();
        while (T > 0) {
            long n = sc.nextInt();
            nodeB head = new nodeB('S');
            nodeB tail = new nodeB('E');
            tail.ex = head;
            head.next = tail;
            nodeB clo = tail;
            String line = sc.next();
            char[] array = line.toCharArray();
            int i = 0;
            while (i < n) {
                switch (array[i]) {
                    case 'H':
                        if (clo.ex != head) {
                            clo = clo.ex;
                        }
                        break;
                    case 'r':
                        if (i + 1 < n) {
                            if (clo!=tail){
                                clo.n=array[++i];
                            }else {
                                nodeB tem=new nodeB(array[++i]);
                                tail.ex.next=tem;
                                tem.ex=tail.ex;
                                tem.next=tail;
                                tail.ex=tem;
                                clo=tem;
                            }
                        }
                        break;
                    case 'I':
                        clo=head.next;
                        break;
                    case 'L':
                        if (clo.next != null) {
                            clo = clo.next;
                        }
                        break;
                    case 'x':
                        if (clo != tail) {
                            if (clo.ex != null) {
                                clo.ex.next = clo.next;
                            }
                            if (clo.next != null) {
                                clo.next.ex = clo.ex;
                            }
                            clo = clo.next;
                        }
                        break;
                    default:
                        nodeB tem2 = new nodeB(array[i]);
                        if (clo.ex != null) {
                            tem2.ex = clo.ex;
                            clo.ex.next = tem2;
                        }
                        clo.ex = tem2;
                        tem2.next = clo;
                        break;
                }
                ++i;
            }
            clo = head;
            while (clo.next != tail) {
                out.print(clo.next.n);
                clo = clo.next;
            }
            out.println("");
            T--;
        }
        out.close();
    }
}

class nodeB {
    char n;
    nodeB ex;
    nodeB next;

    public nodeB(char n) {
        this.n = n;
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


