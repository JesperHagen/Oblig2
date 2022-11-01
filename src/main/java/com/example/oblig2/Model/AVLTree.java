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

    /**
     * Oppdaterer høyden til treet
     * @param node
     */
    private void updateHeight(AVLTreeNode<E> node) {
        if (node.left == null && node.right == null)
            node.height = 0;
        else if (node.left == null)
            node.height = 1 + ((AVLTreeNode<E>) (node.right)).height;
        else if (node.right == null)
            node.height = 1 + ((AVLTreeNode<E>) (node.left)).height;
        else
            node.height = 1 +
                    Math.max(((AVLTreeNode<E>) (node.right)).height,
                            ((AVLTreeNode<E>) (node.left)).height);

    }

    /**
     * Balanserer treet ved å bruke BalanceLL, BalanceLR, BalanceRR og BalanceRL 
     * @param e
     */
    private void balancePath(E e) {
        java.util.ArrayList<TreeNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> A = (AVLTreeNode<E>) (path.get(i));
            updateHeight(A);
            AVLTreeNode<E> parentOfA = (A == root) ? null :
                    (AVLTreeNode<E>) (path.get(i - 1));

            switch (balanceFactor(A)) {
                case -2:
                    if (balanceFactor((AVLTreeNode<E>) A.left) <= 0) {
                        balanceLL(A, parentOfA);
                    } else {
                        balanceLR(A, parentOfA);
                    }
                    break;
                case +2:
                    if (balanceFactor((AVLTreeNode<E>) A.right) >= 0) {
                        balanceRR(A, parentOfA);
                    } else {
                        balanceRL(A, parentOfA);
                    }
            }

        }

    }

    private int balanceFactor(AVLTreeNode<E> node) {
        if (node.right == null)
            return -node.height;
        else if (node.left == null)
            return +node.height;
        else
            return ((AVLTreeNode<E>) node.right).height -
                    ((AVLTreeNode<E>) node.left).height;
    }

    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left;

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        A.left = B.right;
        B.right = A;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
    }

    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left;
        TreeNode<E> C = B.right;

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = A;

        // Adjust heights
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        A.right = B.left;
        B.left = A;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
    }

    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;
        TreeNode<E> C = B.left;

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;

        // Adjust heights
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

    @Override
    /** Sletter et element i treet
     * returner true hvis sletting er ok
     * Returner false hvis elementet ikke er i treet */
    public boolean delete(E element) {
        if (root == null)
            return false; // elementet er ikke i treet

        // lokaliser noden som skal slettes og foreldrenoden
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(element, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (c.compare(element, current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                break;
        }

        if (current == null)
            return false; // elementet er ikke i treet


        if (current.left == null) {

            if (parent == null) {
                root = current.right;
            } else {
                if (c.compare(element, parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;

                // balanser treet om nødvendig
                balancePath(parent.element);
            }
        } else {

            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // fortsett til høyre
            }


            current.element = rightMost.element;


            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                parentOfRightMost.left = rightMost.left;

            // Balance the tree if necessary
            balancePath(parentOfRightMost.element);
        }

        size--;
        return true; // Element insatt
    }


    protected static class AVLTreeNode<E> extends BST.TreeNode<E> {
        protected int height = 0; // Høyde på noden
        protected int size = 1; // Antall children noder + seg selv


        public AVLTreeNode(E o) {
            super(o);
        }
    }


    public E find (int k) {
        return find(k, (AVLTreeNode<E>) this.root);
    }
    public E find(int k, AVLTreeNode<E> node) {
       if (k < 0 || k > this.size) {
           return null;
       }
       else if (node.left == null && k==1) {
           return node.element;
       }
       else if (node.left == null && k == 2) {
           return node.right.element;
       }
       else if (k <= ((AVLTreeNode<E>)node.left).size)
        {
            return find(k, (AVLTreeNode<E>) node.left);
        }
        else if  (k == (((AVLTreeNode<E>)node.left).size)+1) {
            return node.element;
        }
        else {
            return find(k-(((AVLTreeNode<E>) node.left).size)-1,(AVLTreeNode<E>)node.right);
        }
    }
}




