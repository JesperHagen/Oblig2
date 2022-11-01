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
        TreeNode<E> B = A.left; // A is left-heavy and B is left-heavy

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
        TreeNode<E> B = A.left; // A is left-heavy
        TreeNode<E> C = B.right; // B is right-heavy

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.left = C.right; // Make T3 the left subtree of A
        B.right = C.left; // Make T2 the right subtree of B
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
        TreeNode<E> B = A.right; // A is right-heavy
        TreeNode<E> C = B.left; // B is left-heavy

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.right = C.left; // Make T2 the right subtree of A
        B.left = C.right; // Make T3 the left subtree of B
        C.left = A;
        C.right = B;

        // Adjust heights
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

    @Override
    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E element) {
        if (root == null)
            return false; // Element is not in the tree

        // Locate the node to be deleted and also locate its parent node
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
                break; // Element is in the tree pointed by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left children (See Figure 23.6)
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (c.compare(element, parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;

                // Balance the tree if necessary
                balancePath(parent.element);
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost is current
                parentOfRightMost.left = rightMost.left;

            // Balance the tree if necessary
            balancePath(parentOfRightMost.element);
        }

        size--;
        return true; // Element inserted
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




