package DSAA_Lab.fall_lab0;

import java.util.Scanner;

public class Majsoul {
    static String[] dictionary={"W1","W2","W3","W4","W5","W6","W7","W8","W9",
            "T1","T2","T3","T4","T5","T6","T7","T8","T9","Y1","Y2","Y3","Y4","Y5","Y6","Y7","Y8","Y9",
            "E","S","W","N","B","F","Z"};
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=Integer.valueOf(sc.nextLine());//!!!sc.nextLine是从第一行读取输入的部分开始读取！！！
        for (int i = 0; i <n; i++) {
            String line=sc.nextLine();
            int[] barrel=new int[40];
            int front=0,back=0;
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j)==' ') {
                    back = j;
                    for (int k = 0; k < dictionary.length; k++) {
                        if (dictionary[k].equals(line.substring(front, back))) {
                            barrel[k]++;
                            break;
                        }
                    }
                    front=back+1;
                    if (j==line.length()-2||j==line.length()-3){
                        for (int k = 0; k < dictionary.length; k++) {
                            if (dictionary[k].equals(line.substring(front))) {
                                barrel[k]++;
                                break;
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < dictionary.length; j++) {
                for (int k = 0; k < barrel[j]; k++) {
                    System.out.print(dictionary[j]+" ");
                }
            }
            System.out.println("");
        }
    }
}
