package ru.mirea;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class SyncSet<E> implements Set<E>{
    private final Semaphore semaphore = new Semaphore(1);
    private final Set<E> set = new HashSet<>();

    @Override
    public int size() {
        int size = 0;
        try {
            semaphore.acquire();
            size = set.size();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = false;
        try {
            semaphore.acquire();
             isEmpty= set.isEmpty();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return isEmpty;
    }

    @Override
    public boolean contains(Object o) {
        boolean isContains = false;
        try {
            semaphore.acquire();
             isContains = set.contains(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
        return isContains;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iter = null;
        try {
            semaphore.acquire();
            iter = set.iterator();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        assert iter != null;
        return iter;
    }

    @Override
    public Object[] toArray() {
        Object[] objs = null;
        try {
            objs = set.toArray();
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return objs;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] arr = null;
        try {
            semaphore.acquire();
            arr = set.toArray(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        assert arr != null;
        return arr;
    }

    @Override
    public boolean add(E e) {
        boolean added = false;
        try {
            semaphore.acquire();
            added = set.add(e);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            semaphore.release();
        }
        return added;
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = false;
        try {
            semaphore.acquire();
            removed = set.remove(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean contained = false;
        try {
            semaphore.acquire();
            contained = set.containsAll(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
        return contained;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean added = false;
        try {
            semaphore.acquire();
            added = set.addAll(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return added;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;
        try {
            semaphore.acquire();
            removed  = set.removeAll(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean retained = false;
        try {
            semaphore.acquire();
            retained = set.retainAll(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return retained;
    }

    @Override
    public void clear() {
        try {
            semaphore.acquire();
            set.clear();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
