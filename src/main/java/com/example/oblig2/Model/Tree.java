package com.example.oblig2.Model;

import java.util.Collection;
import java.util.Iterator;

public interface Tree<E> extends Collection<E> {

    public boolean search(E e);

    public boolean insert(E e);

    public boolean delete(E e);

    public int getSize();

    public default void inorder() {

    }

    public default void postorder() {

    }

    public default void preorder() {

    }

    @Override
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public default boolean contains(Object e){
       return search((E)e);
    }

    @Override
    public default boolean add(E e) {
        return insert(e);
    }

    @Override
    public default boolean remove(Object e) {
        return delete((E)e);
    }

    public default int size() {
        return getSize();
    }

    @Override
    public default boolean containsAll(Collection<?> c){
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        for(Object e : c)
            add((E) c);
        return true;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        try {
            for (int i = 0; i <= c.size(); i++) {
                remove(i);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException("the collection is null");

        Iterator itr = iterator();

        boolean found = false;
        while(itr.hasNext()){
            if (!c.contains(itr.next()))
                itr.remove();
            found = true;
        }
        return found;
    }

    @Override
    public default Object[] toArray() {

        int indexCount = 0;
        Iterator<E> itr = iterator();

        while(itr.hasNext()) {
            indexCount++;
        }
        Object[] arr = new Object[indexCount];

        while(itr.hasNext()){
            arr[indexCount] = itr.next();
        }
        return arr;
    }

    @Override
    public default <T> T[] toArray(T[] array) {
        if (array == null)
            throw new NullPointerException("the array is null");

        if (array.length < size())
            array = (T[])java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size());

        int indexCount = 0;
        Iterator<E> iterator = iterator();

        while(iterator.hasNext()) {
            array[indexCount] = (T) iterator.next();
            indexCount++;
        }
        return array;
    }
}
