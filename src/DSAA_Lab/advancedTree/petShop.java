package DSAA_Lab.advancedTree;

import java.util.Scanner;

public class petShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        nodeTreeE petRoot = null, adoptRoot = null;
        nodeTreeE ans = new nodeTreeE(0);
        for (int i = 0; i < n; i++) {
            if (sc.nextInt() == 0) {
                nodeTreeE target = new nodeTreeE(sc.nextInt());
                if (adoptRoot == null) {
                    petRoot = insert(petRoot, target);
                }else {
                    adoptRoot=adopt(adoptRoot,target,ans);
                }
            } else {
                nodeTreeE target = new nodeTreeE(sc.nextInt());
                if (petRoot==null){
                adoptRoot = insert(adoptRoot, target);
                }else {
                    petRoot=adopt(petRoot,target,ans);
                }
            }
        }
        System.out.println(ans.val);
    }

    public static nodeTreeE adopt(nodeTreeE root, nodeTreeE target, nodeTreeE ans) {
        nodeTreeE successor = successorQuery(root, target);
        nodeTreeE preorder = preorderQuery(root, target);
        if (successor == null && preorder != null) {
            ans.val += Math.abs(preorder.val - target.val);
            root = delete(root, preorder);
            return root;
        } else if (successor != null && preorder == null) {
            ans.val += Math.abs(successor.val - target.val);
            root = delete(root, successor);
        } else if (successor != null) {
            long s = Math.abs(successor.val - target.val);
            long p = Math.abs(preorder.val - target.val);
            ;
            if (s >= p) {
                ans.val += p;
                root = delete(root, preorder);
            } else {
                ans.val += s;
                root = delete(root, successor);
            }
        }
        return root;
    }

    public static nodeTreeE insert(nodeTreeE root, nodeTreeE node) {
        if (root == null) {
            return node;
        }
        if (root.val > node.val) {
            root.left = insert(root.left, node);
        } else {
            root.right = insert(root.right, node);
        }
        return root;
    }
    public static nodeTreeE delete(nodeTreeE root, nodeTreeE node) {
        if (root == null) {
            return null;
        } else if (root.val > node.val) {
            root.left = delete(root.left, node);
        } else if (root.val < node.val) {
            root.right = delete(root.right, node);
        } else if (root.right != null && root.left != null) {
            nodeTreeE tem = successorQuery(root.right, node);
            root.val = tem.val;
            root.right = delete(root.right, tem);
        } else {
            root = remove(node);
        }
        return root;
    }

    public static nodeTreeE remove(nodeTreeE node) {
        nodeTreeE parent = node.parent;
        if (node.left == null && node.right == null) {
             point(parent, node, null);
            return null;
        } else if (node.left != null && node.right == null) {
             point(parent, node, node.left);
            return node.left;
        } else if (node.left == null && node.right != null) {
            point(parent, node, node.right);
            return node.right;
        } else {
            return null;
        }
    }

    public static nodeTreeE point(nodeTreeE parent, nodeTreeE node, nodeTreeE newNode) {
        if (parent == null) {
            return null;
        } else if (parent.right == node) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        if (newNode != null) {
            newNode.parent = parent;
        }
        return parent;
    }

    public static nodeTreeE successorQuery(nodeTreeE root, nodeTreeE node) {
        if (root == null) {
            return null;
        } else if (root.val >= node.val) {
            nodeTreeE tem = successorQuery(root.left, node);
            if (tem == null) {
                return root;
            } else {
                return tem;
            }
        } else {
            return successorQuery(root.right, node);
        }
    }

    public static nodeTreeE preorderQuery(nodeTreeE root, nodeTreeE node) {
        if (root == null) {
            return null;
        } else if (root.val <= node.val) {
            nodeTreeE tem = preorderQuery(root.right, node);
            if (tem == null) {
                return root;
            } else {
                return tem;
            }
        } else {
            return preorderQuery(root.left, node);
        }
    }
}

class nodeTreeE {
    nodeTreeE left;
    nodeTreeE right;
    nodeTreeE parent;
    long val = 0;

    public nodeTreeE(long val) {
        this.val = val;
    }
}
