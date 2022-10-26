package com.example.oblig2;

import com.example.oblig2.Model.AVLTree;

public class TestAVLTree {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<Integer>(new Integer[] { 25, 20, 5 });
        System.out.println("After inserting 25, 20, 5");
        printTree(tree);

        tree.insert(34);
        tree.insert(50);
        System.out.println("\nAfter inserting 34, 50");
        printTree(tree);

        tree.insert(30);
        System.out.println("\nAfter inserting 30");
        printTree(tree);

        tree.insert(10);
        System.out.println("\nAfter inserting 10");
        printTree(tree);


        tree.delete(34);
        tree.delete(30);
        tree.delete(50);
        System.out.println("\nAfter deleting 34, 30, 50");
        printTree(tree);

        System.out.print("\nTraverse the elemtnts of the tree: ");
        for (int e : tree)
            System.out.print(e + " ");
    }

    public static void printTree(AVLTree<Integer> tree) {
        System.out.print("Inorder (sorted): ");
        tree.inorder();
        System.out.print("\nPreorder: ");
        tree.preorder();
        System.out.print("\nPostorder: ");
        tree.postorder();
        System.out.print("\nThe number of nodes is " + tree.getSize());

    }
}
