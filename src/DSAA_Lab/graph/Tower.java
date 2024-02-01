package DSAA_Lab.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tower {
    public static void main(String[] args) {
        QReaderC sc=new QReaderC();
        int t=sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n=sc.nextInt(),m=sc.nextInt();
            nodeGraphC[] num=new nodeGraphC[n];
            for (int j = 0; j <n ; j++) {
                num[j] = new nodeGraphC(j+1);
            }
            for (int j = 0; j < m; j++) {
                int x=sc.nextInt(),y=sc.nextInt();
                num[x-1].neighbor.add(num[y-1]);
                num[y-1].neighbor.add(num[x-1]);
            }
//            find(max,n);
//            nodeGraphC[] tem=new nodeGraphC[n];
//            Sort(num,tem,0,n-1);
//            for (int j = n-1; j >=0; j--) {
//                if (dfs(num[j],n,num)){
//                    break;
//                }else {
//                    num[j].isFind=false;
//                    for (int k = 0; k < num[j].protect.size(); k++) {
//                        num[j].protect.get(k).isVisited=false;
//                        cnt--;
//                    }
//                    num[j].protect=new ArrayList<>();
//                    ans.remove(num[j]);
//                }
//            }
            nodeGraphC top=num[0];
            nodeGraphC[] queue=new nodeGraphC[(int)n];
            long head=0,end=0;
            queue[(int)end++]=top;
            top.isVisited=true;
            top.path=0;
            while (head<end){
                for (int j = 0; j < queue[(int)head].neighbor.size(); j++) {
                    if (!queue[(int)head].neighbor.get(j).isVisited){
                        queue[(int)end++]=queue[(int)head].neighbor.get(j);
                        queue[(int)head].neighbor.get(j).isVisited=true;
                        queue[(int)head].neighbor.get(j).path=queue[(int)head].path+1;
                    }
                }
                head++;
            }
            ArrayList<nodeGraphC>ans1=new ArrayList<>();
            ArrayList<nodeGraphC>ans2=new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if(num[j].path%2==0){
                    ans1.add(num[j]);
                }else {
                    ans2.add(num[j]);
                }
            }
//            System.out.println(ans1.size()+" "+ans2.size()+" why ");
//            for (int j = 0; j < ans1.size(); j++) {
//                System.out.print(ans1.get(j).path+" ");
//            }
            if(ans1.size()<ans2.size()){
                out.println(ans1.size());
                for (int j = 0; j < ans1.size(); j++) {
                    out.print(ans1.get(j).index+" ");
                }
                out.println("");
            }else {
                out.println(ans2.size());
                for (int j = 0; j < ans2.size(); j++) {
                    out.print(ans2.get(j).index+" ");
                }
                out.println("");
            }
        }
        out.close();
    }
    static QWriterC out =new QWriterC();
    static ArrayList<nodeGraphC> ans=new ArrayList<>();
    static int cnt=0;

//    public static boolean find(nodeGraphC max,int target){
//        if (max==null){
//            return false;
//        }
//        if (cnt==target){
//            out.println(ans.size());
//            for (int i = 0; i < ans.size(); i++) {
//                out.print(ans.get(i).index+" ");
//            }
//            out.println("");
//            return true;
//        }
//        max.isFind=true;
//        int proNum=0;
//        for (int i = 0; i < max.neighbor.size(); i++) {
//            if (!max.neighbor.get(i).isVisited){
//                max.neighbor.get(i).isVisited=true;
//                proNum++;
//                cnt++;
//            }
//        }
//        if (proNum>0){
//            ans.add(max);
//            if (!max.isVisited){
//                max.isVisited=true;
//                cnt++;
//            }
//        }
//        for (int i = 0; i < max.neighbor.size(); i++) {
//            if (!max.neighbor.get(i).isFind){
//                if (find(max.neighbor.get(i),target)){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//    public static void Merge(nodeGraphC[]num,nodeGraphC[]ans,long left,long right,long mid){
//        int i=(int)left,j=(int)mid+1,m=(int)left;
//        while (i<=mid&&j<=right){
//            ans[m++]=num[i].neighbor.size()<=num[j].neighbor.size()?num[i++]:num[j++];
//        }
//
//        while (i<=mid){
//            ans[m++]=num[i++];
//        }
//        while (j<=right){
//            ans[m++]=num[j++];
//        }
//        for (int k = (int)left; k <=right ; k++) {
//            num[k]=ans[k];
//        }
//    }
//    static void Sort(nodeGraphC[]num,nodeGraphC[]ans,long left,long right){
//        if (left<right){
//            long mid=(left+right)/2;
//            Sort(num,ans,left,mid);
//            Sort(num,ans,mid+1,right);
//            Merge(num,ans,left,right,mid);
//        }
//    }
//    public static boolean dfs(nodeGraphC max,int target,nodeGraphC[] num){
//        if (ans.size()>target/2){
//            return false;
//        }
//        if (cnt==target){
//            out.println(ans.size());
//            for (int i = 0; i < ans.size(); i++) {
//                out.print(ans.get(i).index+" ");
//            }
//            out.println("");
//            return true;
//        }
//
//        if (!max.isVisited){
//            max.protect.add(max);
//            max.isVisited=true;
//            cnt++;
//        }
//        max.isFind=true;
//        for (int i = 0; i < max.neighbor.size(); i++) {
//            if (!max.neighbor.get(i).isVisited){
//                max.protect.add(max.neighbor.get(i));
//                max.neighbor.get(i).isVisited=true;
//                cnt++;
//            }
//        }
//        ans.add(max);
//        for (int i = num.length-1; i >=0 ; i--) {
//            if (!num[i].isFind){
//                if (dfs(num[i],target,num)){
//                    return true;
//                }else {
//                    num[i].isFind=false;
//                    for (int j = 0; j < num[i].protect.size(); j++) {
//                        num[i].protect.get(j).isVisited=false;
//                        cnt--;
//                    }
//                    num[i].protect=new ArrayList<>();
//                    ans.remove(num[i]);
//                }
//            }
//        }
//        return false;
//    }
}
class nodeGraphC{
    boolean isVisited=false;
    boolean isFind=false;
    ArrayList<nodeGraphC> neighbor=new ArrayList<>();
    ArrayList<nodeGraphC> protect=new ArrayList<>();
    int index;
    long path=0;
    public nodeGraphC(int index) {
        this.index = index;
    }
}
class QReaderC {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
class QWriterC implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
