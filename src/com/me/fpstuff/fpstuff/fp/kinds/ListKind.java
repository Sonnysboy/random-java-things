package com.me.fpstuff.fpstuff.fp.kinds;

import java.util.*;
import java.util.function.UnaryOperator;

public class ListKind<T> implements List<T>, Kind1<ListKind.MU, T> {

    private final List<T> boxed;

    public ListKind(List<T> list) {
        this.boxed = list;
    }
    public ListKind() {
        this.boxed = new ArrayList<>();
    }


    public static <T> List<T> narrow(Kind1<MU, T> hkt) {
        return (ListKind<T>) hkt;
    }
    @SafeVarargs
    public static <T> ListKind<T> of(T... arr) {
        return new ListKind<T>(Arrays.asList(arr));
    }
    public static <T> ListKind<T> widen(List<T> ts) {
        return new ListKind<>(ts);
    }
    public final static class MU {}

    @Override
    public int size() {
        return boxed.size();
    }

    @Override
    public boolean isEmpty() {
        return boxed.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return boxed.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return boxed.iterator();
    }

    @Override
    public Object[] toArray() {
        return boxed.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return boxed.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return boxed.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return boxed.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return boxed.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return boxed.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return boxed.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return boxed.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        
        return boxed.retainAll(c);
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        boxed.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        boxed.sort(c);
    }

    @Override
    public void clear() {
        boxed.clear();
    }

    @Override
    public boolean equals(Object o) {
        return boxed.equals(o);
    }

    @Override
    public int hashCode() {
        return boxed.hashCode();
    }

    @Override
    public T get(int index) {
        return boxed.get(index);
    }

    @Override
    public T set(int index, T element) {
        return boxed.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        boxed.add(index, element);
    }

    @Override
    public T remove(int index) {
        return boxed.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return boxed.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return boxed.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return boxed.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return boxed.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return boxed.subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<T> spliterator() {
        return boxed.spliterator();
    }

    @Override
    public void addFirst(T t) {
        boxed.addFirst(t);
    }

    @Override
    public void addLast(T t) {
        boxed.addLast(t);
    }

    @Override
    public T getFirst() {
        return boxed.getFirst();
    }

    @Override
    public T getLast() {
        return boxed.getLast();
    }

    @Override
    public T removeFirst() {
        return boxed.removeFirst();
    }

    @Override
    public T removeLast() {
        return boxed.removeLast();
    }

    @Override
    public List<T> reversed() {
        return boxed.reversed();
    }

    @Override
    public String toString() {
        return boxed.toString();
    }
}
