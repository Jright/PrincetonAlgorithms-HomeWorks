package week_4.exercise_2;


import edu.princeton.cs.algs4.Queue;

public class Exercise_2 {

//    Question 1
//    Java autoboxing and equals(). Consider two double values a and b and their corresponding <tt>Double</tt> values x and y.
//
//    Find values such that (a==b) is true but x.equals(y) is false.
//    Find values such that (a==b) is false but x.equals(y) is true.

    //As far as I know the IEEE has some special regulations for 0.0 and -0.0 and NaN(Not a number) within the definition of float number
    public static void main(String[] args){

        //=====================Question 1===========================
//        double a = Double.NaN;
//        double b = Double.NaN;
//        double a = 0.0;
//        double b = -0.0;
//        Double x = new Double(a);
//        Double y = new Double(b);
//        System.out.println(a==b);
//        System.out.println(x.equals(y));

        //=====================Question 3===========================
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        inOrder(tree.root);
        System.out.println("mQueue.size: " + mQueue.size());
        while(!mQueue.isEmpty()){
            Node dequeue = mQueue.dequeue();
            System.out.print(dequeue.key + " ");
        }
    }

    private static Queue<Node> mQueue = new Queue<>();

    static class BinaryTree {
        // Root of Binary Tree
        Node root;

        BinaryTree() {
            root = null;
        }
    }


    // Question 2
//    Check if a binary tree is a BST. Given a binary tree where each Node contains a key,
// determine whether it is a binary search tree. Use extra space proportional to the height of the tree.

    //The node class in alg4.BST is private, so make a copy
    private static class Node{
        private int key;
        private Node left,right;
        private int value;
        private int N;

        public Node(int key){
            this.key = key;
        }
    }

    private boolean checkIsBST(Node x, int max,int min){
        if(x == null){
            return true;
        }
        if(x.key >= max || x.key <= min){
            return false;
        }else{
            //Beautiful Recursion
            return checkIsBST(x.left, x.key, min) && checkIsBST(x.right,max, x.key);
        }
    }

    //Question 3
    //Inorder traversal with constant extra space.
    // Design an algorithm to perform an inorder traversal of a binary search tree using only a constant amount of extra space.


    //Explain: Inorder traversal(Left-Root-Right) is a Depth First Traversals, the other two types of DFS is pre-order traversal(Root-Left-Right)
    // and post-order traversal(Right-Root_Left)
//    In case of binary search trees (BST), Inorder traversal gives nodes in non-decreasing order.
    private static void inOrder(Node node){

        if(node == null){
            return;
        }

        inOrder(node.left);

        mQueue.enqueue(node);

        inOrder(node.right);

    }

    private static void preOrder(Node node){

        if(node == null){
            return;
        }

        mQueue.enqueue(node);

        preOrder(node.left);

        preOrder(node.right);

    }

    private static void postOrder(Node node){

        if(node == null){
            return;
        }

        postOrder(node.left);

        postOrder(node.right);

        mQueue.enqueue(node);

    }

    //Question 4
//    Web tracking. Suppose that you are tracking n web sites and m users and you want to support the following API:
//    User visits a website.
//    How many times has a given user visited a given site?
//    What data structure or data structures would you use?

    //1. Symbol Table, use user as key, website as value
    //2. Symbol Table, use website as key, the value is also a Symbol Table, key is website and value as how much times the user visits

}
