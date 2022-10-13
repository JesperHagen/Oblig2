package com.example.oblig2.Model;

public class BST<E> implements Tree<E>{
    protected TreeNode <E> root;
    protected int size = 0;
    protected java.util.Comparator<E> c;
}