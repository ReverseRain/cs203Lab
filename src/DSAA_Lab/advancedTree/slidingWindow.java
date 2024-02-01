package DSAA_Lab.advancedTree;

import java.util.Scanner;

public class slidingWindow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), k = sc.nextInt();
        nodeTreeF[] num = new nodeTreeF[m + 1];
        for (int i = 0; i < m; i++) {
            num[i + 1] = new nodeTreeF(sc.nextInt());
        }
        long[] list = new long[(m - k + 1)];
        for (int i = 0; i < (m - k + 1); i++) {
            list[i] = sc.nextInt();
        }

        nodeTreeF root = num[1];
//        for (int i = 2; i < m + 1; i++) {
//            root = insert(root, num[i]);
//        }
//        root = delete(root, num[4]);
//        inOrder(root);
        for (int i = 2; i < k + 1; i++) {
            root = insert(root, num[i]);
        }

        findCount(root, list[0], root.cnt);
        for (int i = 1; i < (m - k + 1); i++) {

            root = delete(root, num[i]);
            root = insert(root, num[i + k]);
            findCount(root, list[i], root.cnt);
        }

    }

    public static void inOrder(nodeTreeF root) {
        if (root == null) {

            return;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    public static void findCount(nodeTreeF root, long cnt, long rootCnt) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            if (root.left.cnt + 1 < cnt) {
                findCount(root.right, cnt - (root.left.cnt + 1), rootCnt - (root.left.cnt + 1));
            } else if (root.left.cnt + 1 > cnt) {
                findCount(root.left, cnt, rootCnt - (root.left.cnt + 1));
            } else {
                System.out.println(root.val);
            }
        } else if (cnt == 1) {
            System.out.println(root.val);
        } else {
            findCount(root.right, cnt - 1, rootCnt - 1);
        }
    }

    public static nodeTreeF LL_rotate(nodeTreeF node) {
        if (node == null) {
            return null;
        }
        nodeTreeF parent = node.parent;
        nodeTreeF son = node.left;
        if (son != null) {
            if (son.right != null) {
                son.right.parent = node;
            }
            node.left = son.right;
            son.parent = parent;
            son.right = node;
        }
        if (parent != null) {
            if (parent.left == node) {
                parent.left = son;
            } else {
                parent.right = son;
            }
        }

        node.parent = son;
        getHigh(node);
        getHigh(son);
        return son;
    }

    public static nodeTreeF RR_rotate(nodeTreeF node) {
        if (node == null) {
            return null;
        }
        nodeTreeF parent = node.parent;
        nodeTreeF son = node.right;
        if (son != null) {
            if (son.left != null) {
                son.left.parent = node;
            }
            node.right = son.left;
            son.parent = parent;
            son.left = node;
        }
        if (parent != null) {
            if (parent.left == node) {
                parent.left = son;
            } else {
                parent.right = son;
            }
        }
        node.parent = son;
        getHigh(node);
        getHigh(son);
        return son;
    }

    public static nodeTreeF RL_rotate(nodeTreeF node) {
        node.right = LL_rotate(node.right);
        return RR_rotate(node);
    }

    public static nodeTreeF LR_rotate(nodeTreeF node) {
        node.left = RR_rotate(node.left);
        return LL_rotate(node);
    }

    public static long getHigh(nodeTreeF node) {
        if (node == null) {
            return 0;
        }
        if (node.right != null && node.left != null) {
            node.high = Math.max(node.right.high, node.left.high) + 1;
            node.cnt = node.right.cnt + node.left.cnt + 1;
        } else if (node.right == null && node.left != null) {
            node.high = node.left.high + 1;
            node.cnt = node.left.cnt + 1;
        } else if (node.right != null && node.left == null) {
            node.high = node.right.high + 1;
            node.cnt = node.right.cnt + 1;
        } else if (node.right == null && node.left == null) {
            node.high = 1;
            node.cnt = 1;
        }
        return node.high;
    }

    public static nodeTreeF insert(nodeTreeF root, nodeTreeF node) {
        if (node == null) {
            return root;
        }
        if (root == null) {
            return node;
        }
        if (root.val > node.val) {
            if (root.left != null) {
                root.left = insert(root.left, node);
                if (getHigh(root.left) - getHigh(root.right) == 2) {
                    if (node.val > root.left.val) {
                        root = LR_rotate(root);//pay attention that in here update the root
                    } else {
                        root = LL_rotate(root);
                    }
                }
            } else {
                root.left = node;
                node.parent = root;
            }
        } else {
            if (root.right != null) {
                insert(root.right, node);
                if (getHigh(root.right) - getHigh(root.left) == 2) {
                    if (node.val > root.right.val) {
                        root = RR_rotate(root);
                    } else {
                        root = RL_rotate(root);
                    }
                }
            } else {
                root.right = node;
                node.parent = root;
            }
        }
        getHigh(root);
        return root;
    }

    public static nodeTreeF delete(nodeTreeF root, nodeTreeF node) {
        if (root == null) {
            return null;
        } else if (root.val > node.val) {
            root.left = delete(root.left, node);
            if (getHigh(root.right) - getHigh(root.left) == 2) {
                if (getHigh(root.right.right) < getHigh(root.right.left)) {
                    root = RL_rotate(root);
                } else {
                    root = RR_rotate(root);
                }
            }
            //make sure it is an AVL
        } else if (root.val < node.val) {
            root.right = delete(root.right, node);
            if (getHigh(root.left) - getHigh(root.right) == 2) {
                if (getHigh(root.left.left) > getHigh(root.left.right)) {
                    root = LL_rotate(root);
                } else {
                    root = LR_rotate(root);
                }
            }
        }//this part not the most easy one check remake it again
        else if (root.left == null && root.right == null) {
            if (root.parent == null) {
                return null;
            }
            if (root.parent.left == root) {
                root.parent.left = null;
            } else {
                root.parent.right = null;
            }
            getHigh(root.parent);
            return null;
        }
//        else if (root.left == null && root.right != null) {
//            nodeTreeF parent = root.parent;
//            if (parent != null) {
//                if (parent.left == root) {
//                    parent.left = root.right;
//                } else {
//                    parent.right = root.right;
//                }
//            }
//            root = root.right;
//            root.parent = parent;
//            getHigh(parent);
//        }
        else if (root.left != null && root.right == null) {
            nodeTreeF parent = root.parent;
            if (parent != null) {
                if (parent.left == root) {
                    parent.left = root.left;
                } else {
                    parent.right = root.left;
                }
            }
            root = root.left;
            root.parent = parent;
            getHigh(parent);
        } else {
            nodeTreeF v = successorQuery(root, node);
            root.val = v.val;
            root.right = delete(root.right, v);
            getHigh(root.right);
        }
        getHigh(root);
        return root;
    }

    //successorQuery is to find the smallest number that bigger than target;
    //find the smallest node in the tree;
    public static nodeTreeF successorQuery(nodeTreeF root, nodeTreeF target) {
        if (root == null) {
            return null;
        } else if (root.val > target.val) {
            nodeTreeF tem = successorQuery(root.left, target);
            if (tem == null) {
                return root;
            } else {
                return tem;
            }
        } else {
            return successorQuery(root.right, target);
        }
    }
}

class nodeTreeF {
    nodeTreeF left = null;
    nodeTreeF right = null;
    nodeTreeF parent = null;
    long val = 0;
    long high = 1;
    long cnt = 1;

    public nodeTreeF(long val) {
        this.val = val;
    }
}
