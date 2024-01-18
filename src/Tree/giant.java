package Tree;

import java.util.ArrayList;
import java.util.Scanner;

public class giant {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        NodeTreeE[] list=new NodeTreeE[n+1];
        for (int i = 0; i < n-1; i++) {
            int u=sc.nextInt();
            int v=sc.nextInt();
            if (list[u]==null){
                list[u]=new NodeTreeE();
            }
            if (list[v]==null){
                list[v]=new NodeTreeE();
            }
            list[u].children.add(list[v]);
            list[v].children.add(list[u]);
        }
        int numG=sc.nextInt();
        for (int i = 0; i < numG; i++) {
            list[sc.nextInt()].hasGiant=true;
        }
        System.out.println(BFSearch(list));
    }
    public static int BFSearch(NodeTreeE[] list){
        int ans=0;list[1].isVisited=true;
        for (int j = 0; j < list[1].children.size(); j++) {
        NodeTreeE[] queue=new NodeTreeE[list.length];
        int front=0,rear=0;
        queue[rear++]=list[1].children.get(j);
        list[1].children.get(j).isVisited=true;
        list[1].children.get(j).path=1;
        ArrayList<Integer> ansList=new ArrayList<>();
        while (front<rear){
            for (int i = 0; i <
                    queue[front].children.size(); i++) {
                if (!queue[front].children.get(i).isVisited){
                    queue[rear++]=queue[front].children.get(i);
                    queue[front].children.get(i).isVisited=true;
                    queue[front].children.get(i).path=queue[front].path+1;
                    if (queue[front].children.get(i).hasGiant){
                        ansList.add(queue[front].children.get(i).path);
                    }
                }
            }
            front++;
        }
        if (ansList.size()!=0){
        for (int i = 1; i <ansList.size() ; i++) {
            if (ansList.get(i)!=1&&ansList.get(i)<=ansList.get(i-1)){
                ansList.set(i,ansList.get(i-1)+1);
            }
        }
        ans=Math.max(ans,ansList.get(ansList.size()-1));
        }
    }
        return ans;
    }

}
class NodeTreeE{
    boolean isVisited=false;
    ArrayList<NodeTreeE> children=new ArrayList<>();
    boolean hasGiant=false;
    int path=0;
}
