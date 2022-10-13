package com.example.oblig2.Model;

public class BST<E> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> c;

    /** Laget standard BST */
    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    /** Laget BST med comparator */
    public BST(java.util.Comparator<E> c) {
        this.c = c;
    }

    /** Lager et binært tree fra en Array av objekter */
    public BST(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    /** Returnerer true hvis elementet er i treet */
    @Override
    public boolean search(E e) {
        TreeNode<E> current = root; // Start fra roten
        while (current != null) {
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
            } else // elementet er likt current.element
                return true; // Elementet er funnet
        }
        return false; // elementtet er ikke funnet i treet
    }

    /** Insert elementet e inn i treet, returnerer True hvis elementet er innsett */
    @Override
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Opprett en ny rotnode
        else {
            // finn foreldre noden
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (c.compare(e, current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (c.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false; // Duplikat node ikke tillatt

            // Opprett en ny node og koble den til forelderen
            if (c.compare(e, parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }
        size++;
        return true; // Elementet er satt inn
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    // Inorder fra root
    @Override
        public void inorder(){
            inorder(root);
        }

    // Inorder fra en subtre (rekursiv hjelpemetode)
    protected void inorder(TreeNode<E> root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    // Postorder fra root
    @Override
        public void postorder(){
            postorder(root);
        }

    // Postorder fra en subtre (rekursiv hjelpemetode)
    protected void postorder(TreeNode<E> root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    // Preorder fra root
    @Override
        public void preorder(){
            preorder(root);
        }

    // Preorder fra en subtre (rekursiv hjelpemetode)
    protected void preorder(TreeNode<E> root) {
        if (root == null)
            return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Inner class tre node
    public static class TreeNode<E> {
        public E element;
        public TreeNode<E> left;
        public TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    // Hent antall noder i treet
    @Override
    public int getSize() {
        return size;
    }

    // Returnerer rooten til treet
    public TreeNode<E> getRoot() {
        return root;
    }

    // returnerer en Path fra roten til en spesifisert element
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start fra roten

        while (current != null) {
            list.add(current); // Legg til i listen
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
            } else
                break;
        }
        return list; // Returner en arrayliste med nodene
    }

    // Fjern et element fra binær treet. Returnerer true hvis elementet er fjernet,
    // eller false hvis elementet ikke er i treet
    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(e, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                break; // Elementet er funnet
        }
        if (current == null)
            return false; // Elementet er ikke i treet

        // Case 1: current har ingen venstre barn
        if (current.left == null) {
            // Koble parent til høyre barn av current
            if (parent == null) {
                root = current.right;
            } else {
                if (c.compare(e, parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {
            // Case 2: current har venstre barn
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Gå til høyre
            }
            // Koble parent til den største i venstre subtre
            current.element = rightMost.element;

            // Fjern den største i venstre subtre
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                parentOfRightMost.left = rightMost.left;
        }
        size--;
        return true; // Elementet er fjernet
    }

    // Skaff en iterator, bruk Inorder
    @Override
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class inorder iterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Stakk for å lagre nodene
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0; // Peker til listen

        public InorderIterator() {
            inorder(); // Fyller listen med elementer
        }

        // Fyller listen med elementer i inorder
        private void inorder() {
            inorder(root);
        }

        private void inorder(TreeNode<E> root) {
            if (root == null)
                return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        // Hvis det er flere elementer igjen
        @Override
        public boolean hasNext() {
            if (current < list.size())
                return true;
            return false;
        }

        // Returner neste element i inorder
        @Override
        public E next() {
            return list.get(current++);
        }

        // Fjern elementet som returneres av neste
        @Override
        public void remove() {
            if (current == 0) // next er ikke kalt ennå
                throw new IllegalStateException();
            delete(list.get(--current));
            list.clear(); // Fjern alle elementer fra listen
            inorder(); // Fyll listen på nytt
        }
    }

    // Fjern alle elementer fra treet
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}