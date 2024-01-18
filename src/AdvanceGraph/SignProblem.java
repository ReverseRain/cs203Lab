package AdvanceGraph;

import java.util.ArrayList;
import java.util.Scanner;

public class SignProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        nodeAdGraphB[] num = new nodeAdGraphB[n + 1];
        for (int i = 0; i < n + 1; i++) {
            num[i] = new nodeAdGraphB();
        }
        long ans2 = 0;
        long temW = Long.MAX_VALUE, temU = 0;
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            long w = sc.nextInt();
            num[u].near.add(num[v]);
            num[v].near.add(num[u]);
            num[u].weight.add(w);
            num[v].weight.add(w);
            if (w >= 0) {
                ans2 += w;
                if (temW > w) {
                    temW = w;
                    temU = u;
                }
            }
        }
        long size = 0;
        nodeAdGraphB[] heap = new nodeAdGraphB[n + 1];
        num[(int) temU].bestW = 0;
        size = insert(heap, size, num[(int) temU]);
        prim(num[(int) temU], heap, size);
        System.out.println(ans2 - ans);
    }

    static long ans = 0;

    public static void prim(nodeAdGraphB start, nodeAdGraphB[] heap, long size) {
        if (start == null) {
            return;
        }
        if (start.bestW > 0) {
            ans = ans + start.bestW;
        }
        start.isVisited = true;
        size = delete(heap, size);
        for (int i = 0; i < start.near.size(); i++) {
            nodeAdGraphB tem = start.near.get(i);
            if (!tem.isVisited && tem.bestW > start.weight.get(i)) {
                if (tem.bestW == Long.MAX_VALUE) {
                    size = insert(heap, size, tem);
                }
                tem.bestW = start.weight.get(i);
                update(heap, tem);
            }
        }
        prim(heap[1], heap, size);
    }

    public static long insert(nodeAdGraphB[] heap, long size, nodeAdGraphB value) {
        size++;
        heap[(int) size] = value;
        value.index = size;
        long t = size;
        while (t > 1) {
            if (heap[(int) t].bestW < heap[(int) t / 2].bestW) {
                nodeAdGraphB tem = heap[(int) t / 2];
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

    public static long delete(nodeAdGraphB[] heap, long size) {
        long t = 1;
        if (size == 1) {
            heap[1] = null;
        } else {
            heap[1] = heap[(int) size];
        }
        size--;
        if (heap[1] == null) {
            return size;
        }
        heap[1].index = 1;

        while (2 * t <= size) {
            if (2 * t + 1 <= size && heap[(int) t].bestW > heap[(int) (2 * t + 1)].bestW && heap[(int) (2 * t)].bestW > heap[(int) (2 * t + 1)].bestW) {
                nodeAdGraphB tem = heap[(int) (2 * t + 1)];
                heap[(int) (2 * t + 1)] = heap[(int) t];
                heap[(int) t] = tem;
                heap[(int) t].index = t;
                heap[(int) (2 * t + 1)].index = 2 * t + 1;
                t = 2 * t + 1;
            } else if (heap[(int) t].bestW > heap[(int) (2 * t)].bestW) {
                nodeAdGraphB tem = heap[(int) (2 * t)];
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

    public static void update(nodeAdGraphB[] heap, nodeAdGraphB value) {
        long t = value.index;
        while (t > 1) {
            if (heap[(int) t].bestW < heap[(int) t / 2].bestW) {
                nodeAdGraphB tem = heap[(int) t / 2];
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

class nodeAdGraphB {
    ArrayList<nodeAdGraphB> near = new ArrayList<>();
    ArrayList<Long> weight = new ArrayList<>();
    boolean isVisited = false;
    long bestW = Long.MAX_VALUE;
    long index = 0;
//    ArrayList<nodeAdGraphB>
}