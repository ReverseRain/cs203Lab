package DSAA_Lab.AdvanceGraph;

import java.util.ArrayList;
import java.util.Scanner;

public class portal {
    static int T;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt(), k = sc.nextInt();
        nodeAdGraphF[][] num = new nodeAdGraphF[(n + 1)][(k + 1)];
        for (int i = 0; i < k + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                num[j][i] = new nodeAdGraphF(j, i);
            }
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            for (int j = 0; j < k + 1; j++) {
                num[u][j].near.add(num[v][j]);
                num[u][j].intPath.add(w);
            }
        }
        for (int i = 0; i < p; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            for (int j = 0; j < k; j++) {
                num[u][j].near.add(num[v][j + 1]);
                num[u][j].intPath.add(0);
            }
        }
        int S = sc.nextInt();
        T = sc.nextInt();
        long size = 0;
        nodeAdGraphF[] heap = new nodeAdGraphF[n * (k + 1) + 1];
        num[S][0].dist = 0;
        size = insert(heap, size, num[S][0]);
        dij(heap[1], heap, size);
    }

    public static void dij(nodeAdGraphF start, nodeAdGraphF[] heap, long size) {
        if (start == null) {
            return;
        }
        start.isVisited = true;
        if (start.value == T) {
            System.out.println(start.dist);
            return;
        }
        size = delete(heap, size);
        for (int i = 0; i < start.near.size(); i++) {
            nodeAdGraphF tem = start.near.get(i);
            if (!tem.isVisited && tem.dist > (start.intPath.get(i) + start.dist)) {
                if (tem.dist == Long.MAX_VALUE) {
                    size = insert(heap, size, tem);
                }
                tem.dist = start.intPath.get(i) + start.dist;
                update(heap, tem);
            }
        }
        dij(heap[1], heap, size);
    }
    public static long insert(nodeAdGraphF[] heap, long size, nodeAdGraphF value) {
        size++;
        heap[(int) size] = value;
        value.index = size;
        long t = size;
        while (t > 1) {
            if (heap[(int) t].dist < heap[(int) t / 2].dist) {
                nodeAdGraphF tem = heap[(int) t / 2];
                heap[(int) t / 2] = heap[(int) t];
                heap[(int) t] = tem;
                heap[(int) t / 2].index = t / 2;
                heap[(int) t].index = t;
                t = t / 2;
            } else {
                break;
            }
        }
        return size;
    }

    public static long delete(nodeAdGraphF[] heap, long size) {
        long t = 1;
        heap[1] = heap[(int) size];
        size--;
        if (heap[1] == null) {
            return size;
        }
        heap[1].index = 1;
        while (2 * t <= size) {
            if (2 * t + 1 <= size && heap[(int) t].dist > heap[(int) (2 * t + 1)].dist && heap[(int) (2 * t)].dist > heap[(int) (2 * t + 1)].dist) {
                nodeAdGraphF tem = heap[(int) (2 * t + 1)];
                heap[(int) (2 * t + 1)] = heap[(int) t];
                heap[(int) t] = tem;
                heap[(int) t].index = t;
                heap[(int) (2 * t + 1)].index = 2 * t + 1;
                t = 2 * t + 1;
            } else if (heap[(int) t].dist > heap[(int) (2 * t)].dist) {
                nodeAdGraphF tem = heap[(int) (2 * t)];
                heap[(int) (2 * t)] = heap[(int) t];
                heap[(int) t] = tem;
                heap[(int) t].index = t;
                heap[(int) (2 * t)].index = 2 * t;
                t = 2 * t;
            } else {
                break;
            }
        }
        return size;
    }

    public static void update(nodeAdGraphF[] heap, nodeAdGraphF value) {
        long t = value.index;
        while (t > 1) {
            if (heap[(int) t].dist < heap[(int) t / 2].dist) {
                nodeAdGraphF tem = heap[(int) t / 2];
                heap[(int) t / 2] = heap[(int) t];
                heap[(int) t] = tem;
                heap[(int) t / 2].index = t / 2;
                heap[(int) t].index = t;
                t = t / 2;
            } else {
                break;
            }
        }
    }

}

class nodeAdGraphF {
    long value;
    long usedTime;
    long dist = Long.MAX_VALUE;
    long index;
    boolean isVisited = false;

    public nodeAdGraphF(long value, long usedTime) {
        this.value = value;
        this.usedTime = usedTime;
    }

    ArrayList<nodeAdGraphF> near = new ArrayList<>();
    ArrayList<Integer> intPath = new ArrayList<>();
}