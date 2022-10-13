package com.example.oblig2.Model;

public class AVLTree<E> extends BST<E> {

    public AVLTree() {
    }

    public AVLTree(java.util.Comparator<E> c) {
        super(c);
    }

    public AVLTree(E[] objects) {
        super(objects);
    }

    @Override
    protected AVLTreeNode<E> createNewNode(E e) {
        return new AVLTreeNode<E>(e);
    }

    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (!successful)
            return false;
        else {
            balancePath(e);
        }
        return true;
    }

    private void updateHeight(AVLTreeNode<E> node) {
        if (node.left == null && node.right == null)
            node.height = 0;
        else if (node.left == 0)
            node.height = 1 + ((AVLTreeNode<E>)(node.right)).height;
        else if (node.right == null)
            node.height = 1 + ((AVLTreeNode<E>)(node.left)).height;
        else
            node.height = 1 +
                    Math.max(((AVLtreeNode<E>)(node.right)).height,
                            ((AVLTreeNode<E>)(node.left)).height);

    }

    private void balancePath(E e) {
        java.util.ArrayList<TreeNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--)
            AVLTreeNode<E> A = (AVLTreeNode<E>)(path.get(i));
            updateHeight(A);
            AVLTreeNode<E> parentOfA = (A == root) ? null :
                    (AVLTreeNode<E>)(path.get(i-1));

            switch (balanceFactor(A)) {
                case - 2:
                    if (balanceFactor ((AVLTreNode<E>)A.left) <= 0) {
                        balanceLL(A, parentOfA);
                    }
                    else {
                        balanceLR(A, parentOfA);
                    }
                    break;
                case +2:
                    if(balanceFactor((AVLTreeNode<E>)A.right) >= 0) {
                        balanceRR(A, parentOfA);
                    }
                    else  {
                        balanceRL(A, parentOfA);
                    }
            }

    }

}
