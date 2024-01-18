package advancedTree;

import java.util.Scanner;

public class FluffyFunnyTuzi {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextInt();
        nodeTreeD[] num=new nodeTreeD[(int)n];
        for (int i = 0; i < n; i++) {
            num[i]=new nodeTreeD(sc.nextInt());
            num[i].arrayLocal=i;
            if (i-1>=0){
                num[i].left=num[i-1];
                num[i-1].right=num[i];
            }
        }
        nodeTreeD[]heap=new nodeTreeD[(int)n+1];
        long size=0;
        for (int i = 0; i < n; i++) {
            size=insert(heap,size,num[i]);
        }
        nodeTreeD mergeTem=null;nodeTreeD target=null;
        for (int i = 0; i < n-1; i++) {
            if (heap[1].right==null){
                mergeTem=heap[1].left;
            } else if (heap[1].left==null) {
                mergeTem=heap[1].right;
            } else  {
                long l=(heap[1].left.value^heap[1].value)+1;
                long r=(heap[1].right.value^heap[1].value)+1;
                if (l>=r){
                    mergeTem=heap[1].left;
                }else {
                    mergeTem=heap[1].right;
                }
            }
            target=new nodeTreeD((mergeTem.value^heap[1].value)+1);
            target.arrayLocal=heap[1].arrayLocal;
            if (mergeTem==heap[1].left){
                Link(mergeTem.left,heap[1].right,target);
            }else {
                Link(heap[1].left,mergeTem.right,target);
            }

            size=delete(heap,size,mergeTem.heapLocal);
            size=delete(heap,size,heap[1].heapLocal);
            size=insert(heap,size,target);
        }
        System.out.println(heap[1].value);
    }
    public static void Link(nodeTreeD left,nodeTreeD right,nodeTreeD target){
        target.left=left;target.right=right;
        if (right!=null){
            right.left=target;
        }
        if (left!=null){
            left.right=target;
        }
    }

    public static long insert(nodeTreeD[]heap,long size,nodeTreeD target){
        size++;
        heap[(int)size]=target;
        heap[(int)size].heapLocal=size;
        long t=size;
        while (t>1){
            if ((heap[(int)t].value<heap[(int)t/2].value)||
                    (heap[(int)t].value==heap[(int)t/2].value&&heap[(int)t].arrayLocal<heap[(int)t/2].arrayLocal)){
                nodeTreeD tem=heap[(int)t];
                heap[(int)t]=heap[(int)t/2];
                heap[(int)t/2]=tem;
                heap[(int)t/2].heapLocal=t/2;
                heap[(int)t].heapLocal=t;
                t=t/2;
            } else {
                break;
            }
        }
        return size;
    }
    public static long delete(nodeTreeD[]heap,long size,long target){
        heap[(int)target]=heap[(int)size];
        heap[(int)target].heapLocal=target;
        long t=target;
        size--;
        while (2*t<=size){
            if (2*t+1<=size&&isDown(heap,t,2*t+1)
                    &&!isDown(heap,2*t+1,2*t)){
                nodeTreeD tem=heap[(int)t];
                heap[(int)t]=heap[(int)(2*t+1)];
                heap[(int)(2*t+1)]=tem;
                heap[(int)(2*t+1)].heapLocal=2*t+1;
                heap[(int)t].heapLocal=t;
                t=2*t+1;
            } else if (isDown(heap,t,2*t)) {
                nodeTreeD tem=heap[(int)t];
                heap[(int)t]=heap[(int)(2*t)];
                heap[(int)(2*t)]=tem;
                heap[(int)(2*t)].heapLocal=2*t;
                heap[(int)t].heapLocal=t;
                t=2*t;
            }else {
                break;
            }
        }
        while (t>1){
            if ((heap[(int)t].value<heap[(int)t/2].value)||
                    (heap[(int)t].value==heap[(int)t/2].value&&heap[(int)t].arrayLocal<heap[(int)t/2].arrayLocal)){
                nodeTreeD tem=heap[(int)t];
                heap[(int)t]=heap[(int)t/2];
                heap[(int)t/2]=tem;
                heap[(int)t/2].heapLocal=t/2;
                heap[(int)t].heapLocal=t;
                t=t/2;
            } else {
                break;
            }
        }
        return size;
    }
    public static boolean isDown(nodeTreeD[] heap,long start,long target){
        if (heap[(int)start].value>heap[(int)(target)].value){
            return true;
        } else if (heap[(int)start].value==heap[(int)(target)].value&&heap[(int)start].arrayLocal>heap[(int)(target)].arrayLocal) {
            return true;
        }else {
            return false;
        }
    }
}
class nodeTreeD{
    long heapLocal=-1;
    long value=0;
    long arrayLocal=-1;
    nodeTreeD left=null;
    nodeTreeD right=null;
    public nodeTreeD(long value) {
        this.value = value;
    }
}
