package sorting;

import java.util.Scanner;
import java.util.logging.LogManager;

public class PlantsZombies {//many problem
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextInt(),p=sc.nextInt(),q=sc.nextInt();
        long[][]plant=new long[(int)n][2];
        long magic= (long) Math.pow(2,p);
        long temp=0,local=0;
        for (int i = 0; i < n; i++) {
            plant[i][0]=sc.nextInt();
            plant[i][1]= sc.nextInt();
            if (q>1){
                long a=0,b=0;
                if (plant[i][0]>plant[i][1]){
                    a=plant[i][0];
                }else a=plant[i][1];
                if (plant[(int)local][0]>plant[(int)local][1]){
                    b=plant[(int)local][0];
                }else b=plant[(int)local][1];
                if (magic*plant[i][0]+b>magic*plant[(int)local][0]+a){
                    temp=magic*plant[i][0]-plant[i][1];
                    local=i;
                }
                else if (magic*plant[i][0]+b==magic*plant[(int)local][0]+a&&magic*plant[i][0]-plant[i][1]>=temp) {
                    temp=magic*plant[i][0]-plant[i][1];
                    local=i;
                }
            }else if (magic*plant[i][0]-plant[i][1]>=temp){
                temp=magic*plant[i][0]-plant[i][1];
                local=i;
            }
        }
        for (int i = 0; i < p; i++) {
            plant[(int)local][0]=plant[(int)local][0]*2;
        }
        if (q>0&&plant[(int)local][0]>plant[(int)local][1]){
            plant[(int)local][1]=plant[(int) local][0];
            q--;
        }
        plant=MergeSort(plant,0, plant.length-1);
        long k=0;
        while (q>0&&k< plant.length){
            if (plant[(int)k][1]<plant[(int) k][0]){
                plant[(int)k][1]=plant[(int) k][0];
                q--;
            }
            k++;
        }
        long ans=0;
        for (int i = 0; i < plant.length; i++) {
            ans+=plant[i][1];
        }
        System.out.println(ans);
    }
    public static long[][]MergeSort(long[][]plant,long l,long r){
        if (l==r){
            return new long[][]{plant[(int)l]};
        }
        long mid=(l+r)/2;
        long[][]leftPlant=MergeSort(plant,l,mid);
        long[][]rightPlant=MergeSort(plant,mid+1,r);
        long[][] finalPlant=new long[(int)(r-l+1)][2];
        long i=0,j=0,m=0;
        while (i<leftPlant.length&&j< rightPlant.length){
            if ((leftPlant[(int)i][0]-leftPlant[(int)i][1])>=(rightPlant[(int)j][0]-rightPlant[(int)j][1])){//大的排在前面
                finalPlant[(int)m++]=leftPlant[(int)i++];
            }else finalPlant[(int)m++]=rightPlant[(int)j++];
        }
        while (i< leftPlant.length){
            finalPlant[(int)m++]=leftPlant[(int)i++];
        }
        while (j<rightPlant.length){
            finalPlant[(int)m++]=rightPlant[(int)j++];
        }
        return finalPlant;
    }
}
