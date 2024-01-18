package graph;

import java.util.Scanner;

public class AdjacencyMatrix {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n=sc.nextInt();
            int m=sc.nextInt();
            int[][]graph=new int[n][n];
            for (int j = 0; j < m; j++) {
                graph[sc.nextInt()-1][sc.nextInt()-1]=1;
            }
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.print(graph[j][k]+" ");
                }
                System.out.println("");
            }
        }
    }
}
