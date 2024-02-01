package LeetCode_ex.heap;


import java.util.*;

public class cutArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(isPossible(nums));
    }
    public static boolean isPossible2(int[] nums) {
        Map<Integer, heap> map = new HashMap<Integer, heap>();
        for (int x : nums) {
            System.out.println(map.get(x-1).size+" 1");
            if (!map.containsKey(x)) {
                System.out.println(map.get(x-1).size+" 2 "+map.get(x-1));
                map.put(x, new heap(nums.length));
                System.out.println(map.get(x-1).size+" 3 "+map.get(x-1));
            }
            System.out.println(map.get(x-1).size+" 4");
            if (map.containsKey(x - 1)) {
//                for (int i = 0; i < map.get(x-1).size; i++) {
//                    System.out.print(map.get(x-1).LeetCode_ex.basicTree.heap[i]+" ");
//                }
                System.out.println("");
                int len = map.get(x - 1).heap[1];
                System.out.println(len+" op ");
                map.get(x).insert(len + 1);
                map.get(x - 1).delete();
                if (map.get(x - 1).size == 0) {
                    map.remove(x - 1);
                }
            } else {
                map.get(x).insert(1);
                System.out.println(map.get(x).size+" opp ");
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (map.get(nums[i]).heap[1] < 3) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();
        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<Integer>());
            }
            if (map.containsKey(x - 1)) {
                int prevLength = map.get(x - 1).poll();
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                map.get(x).offer(prevLength + 1);
            } else {
                map.get(x).offer(1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (map.get(nums[i]).peek() < 3) {
                    return false;
                }
            }
        }
        return true;
    }

}
class heap {

    public static int[] heap;
    public static long size;

    public heap(int l) {
        this.heap = new int[l + 1];
        for (int i = 0; i < l + 1; i++) {
            this.heap[i] = Integer.MAX_VALUE;
        }
        size = 0;
    }

    public  void insert(int value) {
        size++;
        heap[(int) size] = value;
        long t = size;
        while (t > 1) {
            if (heap[(int) t] < heap[(int) t / 2]) {
                int tem = heap[(int) t / 2];
                heap[(int) t / 2] = heap[(int) t];
                heap[(int) t] = tem;
                t = t / 2;
            } else {
                break;
            }
        }
    }

    public  void delete() {
        long t = 1;
        if (size == 1) {
            heap[1] = 0;
        } else {
            heap[1] = heap[(int) size];
        }
        size--;
        while (2 * t <= size) {
            if (2 * t + 1 <= size && heap[(int) t] > heap[(int) (2 * t + 1)] && heap[(int) (2 * t)] > heap[(int) (2 * t + 1)]) {
                int tem = heap[(int) (2 * t + 1)];
                heap[(int) (2 * t + 1)] = heap[(int) t];
                heap[(int) t] = tem;
                t = 2 * t + 1;
            } else if (heap[(int) t] > heap[(int) (2 * t)]) {
                int tem = heap[(int) (2 * t)];
                heap[(int) (2 * t)] = heap[(int) t];
                heap[(int) t] = tem;
                t = 2 * t;
            } else {
                break;
            }
        }
    }
}


