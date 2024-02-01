package LeetCode_ex.dynamic_programming;

import org.w3c.dom.UserDataHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class delete_count_740 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        long []nums=new long[n];
        long[] list=new long[401];
        for (int i = 0; i < n; i++) {
            int tem=sc.nextInt();
            nums[i]=tem;
            list[tem]=list[tem]+tem;
        }
        System.out.println(dp(list,401));
        System.out.println(dp2(nums));
    }
    /*
    定义子问题：如何在这个过程中逐渐将子问题进行化简
    如果选择并删除了x，则以为这所有x+1和x-1都会被删除；
    因此我们可以获得重复的x*c，通过sum数组来存储对应x的x*c
    最终sum数组的性质与198题的数组相同
    时间复杂度：O(N+M)，其中M指的是数组中的最大值
     */
    public static long dp(long[] list,int n){
        long q=0,p=0,r=0;
        for (int i = 0; i < n; i++) {
            q=p;
            p=r;
            r=Math.max(p,q+list[i]);
        }
        return r;
    }
    /*
    加入排序算法
    若一个数组当中并不存在整数x，则所有小于x的数都不会影响到所有大于x的数的选择。
    因此可以在排序之后，将数组分成几个子数组，并对这几个子数组进行动态规划就可以实现。
     */
    public static long dp2(long[]nums){
        long ans=0;
        Arrays.sort(nums);
        ArrayList<Long> list=new ArrayList<Long>();
        for (int i = 0; i < nums.length; i++) {
            if (i==0){
                list.add(nums[i]);
            }else if (nums[i]==nums[i-1]){
                list.set(list.size()-1,nums[i-1]+list.get(list.size()-1));
            } else if (nums[i]==nums[i-1]+1) {
                list.add(nums[i]);
            } else {
                long[]list2=new long[list.size()];
                for (int j = 0; j < list.size(); j++) {
                    list2[j]=list.get(j);
                }
                ans += dp(list2, list.size());
                list=new ArrayList<>();
                list.add(nums[i]);
            }
        }
        long[]list2=new long[list.size()];
        for (int j = 0; j < list.size(); j++) {
            list2[j]=list.get(j);
        }
        ans += dp(list2, list.size());
        return ans;
    }
}
