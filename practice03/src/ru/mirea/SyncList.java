package ru.mirea;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncList<E> implements List<E>{
    private final Lock lock = new ReentrantLock();
    private final List<E> list = new ArrayList<>();

    @Override
    public int size() {
        lock.lock();
        int size = list.size();
        lock.unlock();
        return size;
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        boolean isEmpty = list.isEmpty();
        lock.unlock();
        return isEmpty;
    }

    @Override
    public boolean contains(Object o) {
        lock.lock();
        boolean isContains = list.contains(o);
        lock.unlock();
        return isContains;
    }

    @Override
    public Iterator<E> iterator() {
        lock.lock();
        Iterator<E> iter = list.iterator();
        lock.unlock();
        return iter;
    }

    @Override
    public Object[] toArray() {
        lock.lock();
        Object[] objs = list.toArray();
        lock.unlock();
        return objs;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        lock.lock();
        T[] arr = list.toArray(a);
        lock.unlock();
        return arr;
    }

    @Override
    public boolean add(E e) {
        lock.lock();
        list.add(e);
        lock.unlock();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        lock.lock();
        list.remove(o);
        lock.unlock();
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        lock.lock();
        list.containsAll(c);
        lock.unlock();
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        lock.lock();
        list.addAll(c);
        lock.unlock();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        lock.lock();
        list.add(index, (E) c);
        lock.unlock();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        lock.lock();
        list.removeAll(c);
        lock.unlock();
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        lock.lock();
        list.retainAll(c);
        lock.unlock();
        return true;
    }

    @Override
    public void clear() {
        lock.lock();
        list.clear();
        lock.unlock();
    }

    @Override
    public E get(int index) {
        lock.lock();
        E gettingIdx = list.get(index);
        lock.unlock();
        return gettingIdx;
    }

    @Override
    public E set(int index, E element) {
        lock.lock();
        E changed = list.set(index, element);
        lock.unlock();
        return changed;
    }

    @Override
    public void add(int index, E element) {
        lock.lock();
        list.add(index, element);
        lock.unlock();
    }

    @Override
    public E remove(int index) {
        lock.lock();
        E removed = list.remove(index);
        lock.unlock();
        return removed;
    }

    @Override
    public int indexOf(Object o) {
        lock.lock();
        int idx = list.indexOf(o);
        lock.unlock();
        return idx;
    }

    @Override
    public int lastIndexOf(Object o) {
        lock.lock();
        int idx = list.lastIndexOf(o);
        lock.unlock();
        return idx;
    }

    @Override
    public ListIterator<E> listIterator() {
        lock.lock();
        ListIterator<E> iter = list.listIterator();
        lock.unlock();
        return iter;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        lock.lock();
        ListIterator<E> iter = list.listIterator(index);
        lock.unlock();
        return iter;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        lock.lock();
        List<E> subList = list.subList(fromIndex, toIndex);
        lock.unlock();
        return subList;
    }
}
