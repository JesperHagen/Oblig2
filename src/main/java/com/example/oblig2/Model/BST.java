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
                current= current.left;
            }
            else if (c.compare(e, current.element) > 0) {
                current = current.right;
            }
            else // elementet er likt current.element
                return true; // Elementet er funnet
        }
        return false; // elementtet er ikke funnet i treet
    }
    /**Insert elementet e inn i treet, returnerer True hvis elementet er innsett */
    @Override
    public boolean insert (E e){
        if (root == null)
            root= createNewNode(e); // Opprett en ny rotnode
        else {
            // finn foreldre noden
            TreeNode<E> parent = null;
            Treenode<E> current = root;
            while (current != null)
                if (c.compare(e, current.element) < 0){
                    parent = current;
                    current = current.left;
                }
                else if (c.compare(e,current.element) >0){
                    parent = current;
                    current = current.right;
                }
                else
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
        protected TreeNode<E> createNewNode(E e){
            return new TreeNode<>(e);
        }
        // Inorder fra root
        @Override
        public inorder(){
            inorder(root);
        }
        // Inorder fra en subtre (rekursiv hjelpemetode)
        protected void inorder(TreeNode<E> root){
            if (root == null) return;
            inorder(root.left);
            System.out.print(root.element + " ");
            inorder(root.right);
        }
        // Postorder fra root
        @Override
        public postorder(){
            postorder(root);
        }
        // Postorder fra en subtre (rekursiv hjelpemetode)
        protected void postorder(TreeNode<E> root){
            if (root == null) return;
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.element + " ");
        }
        // Preorder fra root
        @Override
        public preorder(){
            preorder(root);
        }
        // Preorder fra en subtre (rekursiv hjelpemetode)
        protected void preorder(TreeNode<E> root){
            if (root == null) return;
            System.out.print(root.element + " ");
            preorder(root.left);
            preorder(root.right);
        }
        // Inner class tre node
        public static class TreeNode<E>{
            protected E element;
            protected TreeNode<E> left;
            protected TreeNode<E> right;
            
            public TreeNode(E e){
                element = e;
            }
        }

        // Hent antall noder i treet
        @Override
        public int getSize(){
            return size;
        }

        // Returnerer rooten til treet
        public TreeNode<E> getRoot(){
            return root;
        }

        // returnerer en Path fra roten til en spesifisert element
        public java.util.ArrayList<TreeNode<E>> path(E e){
            java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
            TreeNode<E> current = root; // Start fra roten
            
        }
    }