package LeetCode_ex.dynamic_programming;

import java.util.Scanner;

public class dp_small_path_64 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextInt();
        long m=sc.nextInt();
        long[][] map=new long[(int)n][(int)m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j]=sc.nextInt();
            }
        }
        System.out.println(dp(map));
    }
    public static long dp(long[][]map){
        long[] f=new long[map[0].length];
        f[0]=map[0][0];
        for (int i = 0; i < map[0].length; i++) {
            f[0]=map[i][0]+f[0];
        }
        for (int i = 1; i <map[0].length ; i++) {
            f[i]=f[i-1]+map[0][i];
        }
        for (int i = 1; i < map.length ; i++) {
            for (int j = 0; j <map[0].length ; j++) {
                if (j==0){
                    f[0]=f[0]+map[i][0];
                }else {
                f[j]=map[i][j]+Math.min(f[j-1],f[j]);}
            }
        }
        return f[f.length-1];
    }
}
