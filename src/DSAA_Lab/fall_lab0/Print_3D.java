package DSAA_Lab.fall_lab0;

import java.util.Scanner;

public class Print_3D {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            int a=sc.nextInt(),b=sc.nextInt(),c= sc.nextInt();
            char[][]map=new char[2*b+2*c+1][2*b+2*a+1];
            for (int j = 0; j < map.length; j++) {
                for (int k = 0; k < map[0].length ; k++) {
                    map[j][k]='.';
                }
            }
            char[]al=new char[2*a+1],bl=new char[2*b+1],cl=new char[2*c+1];
            int[] shift=new int[bl.length];
            for (int j = 0; j < bl.length; j++) {
                shift[j]=-j;
            }
            for (int j = 0; j < al.length; j+=2) {
                al[j]='+';
                if (j+1<al.length){
                    al[j+1]='-';
                }
            }
            for (int j = 0; j < bl.length; j+=2) {
                bl[j]='+';
                if (j+1< bl.length){
                    bl[j+1]='/';
                }
            }
            for (int j = 0; j < cl.length; j+=2) {
                cl[j]='+';
                if (j+1<cl.length){
                    cl[j+1]='|';
                }
            }
            for (int j = 0; j < al.length; j+=2) {
                for (int k = 0; k < bl.length; k++) {
                    map[-shift[k]][shift[k]+j+2*b]=bl[k];
                }
                for (int k = 0; k < cl.length; k++) {
                    map[2*b+k][j]=cl[k];
                }
            }
            for (int j = 0; j < bl.length; j+=2) {
                for (int k = 0; k < al.length; k++) {
                    map[-shift[j]][2*b+shift[j]+k]=al[k];
                }
                for (int k = 0; k < cl.length; k++) {
                    map[k-shift[j]][map[0].length-1+shift[j]]=cl[k];
                }
            }
            for (int j = 0; j < cl.length; j+=2) {
                for (int k = 0; k < bl.length; k++) {
                    map[j-shift[k]][map[0].length-1+shift[k]]=bl[k];
                }
                for (int k = 0; k < al.length; k++) {
                    map[2*b+j][k]=al[k];
                }
            }
            for (int j = 0; j < map.length ; j++) {
                for (int k = 0; k < map[0].length ; k++) {
                    System.out.print(map[j][k]);
                }
                System.out.println(" ");
            }
        }
    }
}
