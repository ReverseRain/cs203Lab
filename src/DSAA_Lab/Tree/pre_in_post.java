package DSAA_Lab.Tree;

import java.util.ArrayList;
import java.util.Scanner;

public class pre_in_post {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n=sc.nextInt();
            int[] pre =new int[n];
            int[] in=new int[n];
            for (int j = 0; j < n; j++) {
                pre[j]=sc.nextInt();
            }
            for (int j = 0; j < n; j++) {
                in[j]=sc.nextInt();
            }
            ArrayList<Integer> ans=new ArrayList<>();
            search(pre,in,0,0,n-1,ans);
            for (int j = 0; j < ans.size(); j++) {
                System.out.print(ans.get(j)+" ");
            }
            System.out.println("");
        }
    }
    public static void search(int[] pre, int[] in, int start,
                              int left, int right, ArrayList<Integer> ans){
        if (left>right){
            return;
        }
        int i=left;
        while (i<=right){
            if (in[i]==pre[start]){
                search(pre,in,start+1,left,i-1,ans);
                search(pre,in,start+1+i-left,i+1,right,ans);
                ans.add(pre[start]);
                return;
            }
            i++;
        }
    }
}
