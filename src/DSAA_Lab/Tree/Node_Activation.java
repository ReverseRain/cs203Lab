package DSAA_Lab.Tree;

import java.util.ArrayList;
import java.util.Scanner;

public class Node_Activation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        NodeTreeF[] list = new NodeTreeF[n + 1];
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (list[u] == null) {
                list[u] = new NodeTreeF();
            }
            if (list[v] == null) {
                list[v] = new NodeTreeF();
            }
            list[u].children.add(list[v]);
            list[v].children.add(list[u]);
        }
        long max = 0;
        NodeTreeF root = null;
        for (int i = 1; i <= n; i++) {
            list[i].p = sc.nextInt();
            if (max < list[i].p) {
                max = list[i].p;
                root = list[i];
            }
        }
        long ans = 0;
        root.isVisited = true;
        root.getMaxValue(root);
        if (root.children.size() == 1) {
            ans = root.p;
            ans=activate(ans,root.children.get(0),true, root.p);
        } else {
            NodeTreeF first=new NodeTreeF(),second=new NodeTreeF();
            first.maxValue=0;second.maxValue=0;
            for (int i = 0; i < root.children.size(); i++) {
                if (root.children.get(i).maxValue>first.maxValue){
                    second=first;
                    first=root.children.get(i);
                } else if (root.children.get(i).maxValue>second.maxValue) {
                    second=root.children.get(i);
                }
            }
            for (int i = 0; i < root.children.size(); i++) {
                if (root.children.get(i)==first||root.children.get(i)==second){
                    ans=activate(ans,root.children.get(i),true, root.p);
                }else {
                    ans=activate(ans,root.children.get(i),false, root.p);
                }
            }
        }
        System.out.println(ans);
    }
    public static long activate(long ans, NodeTreeF root, boolean isMax,long trueMax) {
        if (isMax==false) {
            ans = ans + root.maxValue;
        }else {
            ans=ans+trueMax;
        }
        while (root.children.size() != 0) {
            while (root.children.size() == 1) {
                root = root.max;
            }
            for (int i = 0; i < root.children.size(); i++) {
                if(root.children.get(i)!=root.max){
                ans = activate(ans, root.children.get(i), false,trueMax);
                }
            }
            if (root.max==null){
                break;
            }else {
                root=root.max;
            }
        }
        return ans;
    }
}

class NodeTreeF {
    ArrayList<NodeTreeF> children = new ArrayList<>();
    NodeTreeF parent = null;
    long p;
    boolean isVisited = false;
    NodeTreeF max = null;
    long maxValue = 0;

    public long getMaxValue(NodeTreeF root) {
        long tem = 0, max = 0;
        for (int i = 0; i < root.children.size(); i++) {
            if (root.children.get(i).isVisited == false) {
                root.children.get(i).isVisited = true;
                tem = getMaxValue(root.children.get(i));
                if (max <= tem) {
                    max = tem;
                    root.max = root.children.get(i);
                }
            } else {
                root.parent = root.children.get(i);
                root.children.remove(root.children.get(i));
                i--;
            }
        }
        root.maxValue = Math.max(max, root.p);
        return root.maxValue;
    }
}
