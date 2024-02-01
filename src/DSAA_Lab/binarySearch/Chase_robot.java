package DSAA_Lab.binarySearch;

import java.util.Scanner;

public class Chase_robot { //二分时间！！！
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long xc=sc.nextInt(),yc=sc.nextInt(),xr=sc.nextInt(),yr=sc.nextInt();
        long period=sc.nextInt();
        String code=sc.next();
        int[][]move=new int[(int)period][2];
        for (int i = 0; i < period; i++) {
            switch (code.substring(i,i+1)){
                case"R":
                    move[i][0]=1;
                    break;
                case"L":
                    move[i][0]=-1;
                    break;
                case"D":
                    move[i][1]=-1;
                    break;
                case"U":
                    move[i][1]=1;
                    break;
            }
            if (i-1>=0){
                move[i][0]=move[i][0]+move[i-1][0];
                move[i][1]=move[i][1]+move[i-1][1];
            }
        }
        long right= (long) 1E14,left=0,mid=0;
        while (left<right){
            mid=(left+right)/2;
            if (checkWin(move,period,xc,yc,xr,yr,mid)){
                right=mid;
            }else {
                left=mid+1;
            }
//            System.out.println(left+" "+right+" "+mid);
        }
        if (right==1E14){
            System.out.println("-1");
        }else {
            System.out.println(right);
        }
    }
    static Boolean checkWin(int[][]move,long period,long xc,
                            long yc,long xr,long yr,long mid){
        long remainder=mid%period,division=mid/period;
        long step0=0,step1=0;
        if (remainder-1>=0){
            step0=move[(int)remainder-1][0];
            step1=move[(int)remainder-1][1];
        }
        step0+=division*move[(int) period-1][0];
        step1+=division*move[(int)period-1][1];
        if (Math.abs(step0+xr-xc)+Math.abs(step1+yr-yc)<=mid){
            return true;
        }return false;
    }
}
