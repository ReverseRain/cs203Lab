package LeetCode_ex.basicTree;

import java.util.Scanner;

public class judgeBST {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        int[] tree=new int[t+1];
        for (int i = 1; i <t+1 ; i++) {
            tree[i]=sc.nextInt();
        }
        list=new int[t];
        System.out.println(isBST(tree,1,Integer.MIN_VALUE,Integer.MAX_VALUE));
        System.out.println(isBST2(tree,1));
    }
    /*
    对于一个BST的左子树，其根节点一定满足于值小于其父节点。
    下述这个方法中，不断的缩小最小与最大值的范围，来达到检验的目的
     */
    public static boolean isBST(int[] tree,int root,int lower,int higher){
        if (root>=tree.length){
            return true;
        }
        if (tree[root]==-1){
            return true;
        }
        if (tree[root]<lower){
            return false;
        }
        if (tree[root]>higher){
            return false;
        }
        return isBST(tree,2*root,lower,tree[root])&&isBST(tree,2*root+1,tree[root],higher);
    }
    static int[] list;
    static int size=0;
    /*
    判断一个树是否为平衡二叉树，对该树进行中序遍历，若是平衡二叉树，则最终的结果应该是一个升序的数组
     */
    public static boolean isBST2(int[] tree,int root){
        if (root>=tree.length){
            return true;
        }
        if (tree[root]==-1){
            return true;
        }
        if (isBST2(tree,2*root)){
            if (size-1>=0&&list[size-1]>tree[root]){
                return false;
            }else {
                list[size++]=tree[root];
            }
        }else {
            return false;
        }
        return isBST2(tree,2*root+1);
    }
}
