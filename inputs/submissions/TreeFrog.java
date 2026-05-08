//comments: 35
//comment length: 4435

package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

    private T data[]; 
    private int size; 


    
    
    @SuppressWarnings("unchecked")
    public ArrayCollection() {
	size = 0;
	
	
	data = (T[]) new Object[10]; 
    }

    
    @SuppressWarnings("unchecked")
    private void grow() {
	
	T[] temp = (T[]) new Object[size * 2];
	for (int i = 0; i < size; i++) {
	    temp[i] = data[i];
	}
	data = temp;

    }

    
    public boolean add(T arg0) {
	if (contains(arg0))
	    return false;
	if (size == data.length)
	    grow();
	data[size++] = arg0;
	return true;
    }

    
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> arg0) {
	boolean addedCheck = false;
	for (Object e : arg0) {
	    if (add((T) e))
		addedCheck = true;
	}
	return addedCheck;
    }

    
    @SuppressWarnings("unchecked")
    public void clear() {
	ArrayCollectionIterator itr = new ArrayCollectionIterator();
	while(itr.hasNext()) {
	    itr.next();
	    itr.remove();
	}
	size = 0;
    }

    
    public boolean contains(Object arg0) {
	for (int i = 0; i < size; i++) {
	    if (data[i].equals(arg0))
		return true;
	}
	return false;
    }

    
    public boolean containsAll(Collection<?> arg0) {
	boolean wasFound = true;
	for (Object e : arg0)
	    if (!contains(e)) {
		wasFound = false;
		break;
	    }

	return wasFound;
    }

    
    public boolean isEmpty() {
	return (size == 0);
    }

    
    public Iterator<T> iterator() {
	return new ArrayCollectionIterator();
    }

    
    public boolean remove(Object arg0) {
	boolean wasFound = false;
	
	int index = 0;
	for (int i = 0; i < size; i++) {
	    if (data[i].equals(arg0)) {
		data[i] = null;
		wasFound = true;
		index = i;
		break;
	    }
	}
	
	
	if(!wasFound)
	    return false;
	for (int i = index + 1;  i <= size; i++) {
	    data[i - 1] = data[i];
	}
	if (wasFound) 
	    size--;
	return wasFound;
    }

    
    public boolean removeAll(Collection<?> arg0) {
	boolean removedSomething = false;
	for (Object o : arg0) {
	    if (contains(o)) {
		removedSomething = true;
		remove(o);
	    }
	}
	return removedSomething;
    }

    
    public boolean retainAll(Collection<?> arg0) {
	boolean itemRemoved = false;
	ArrayCollectionIterator itr = new ArrayCollectionIterator();

	while (itr.hasNext()) {
	    T e = itr.next();
	    if (!arg0.contains(e)) {
		itr.remove();
		itemRemoved = true;
	    }
	}

	return itemRemoved;
    }

    
    public int size() {
	return size;
    }

    
    public Object[] toArray() {
	T[] newArr = (T[]) new Object[size];
	for (int i = 0; i < size; i++)
	    newArr[i] = data[i];
	return newArr;
    }

    
    public <T> T[] toArray(T[] arg0) {
	return null;
    }

    
    public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
	
	ArrayList<T> list = new ArrayList<>();
	for (int i = 0; i < size; i++)
	    list.add(data[i]);

	
	for (int i = 0; i < list.size() - 1; i++) {
	    int j, minIndex;
	    for (j = i + 1, minIndex = i; j < list.size(); j++)
		if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
		    minIndex = j;
	    T temp = list.get(i);
	    list.set(i, list.get(minIndex));
	    list.set(minIndex, temp);
	}

	return list;
    }

    
    private class ArrayCollectionIterator implements Iterator<T> {
	private boolean recentRemoval;
	private int nextIndex;

	
	public ArrayCollectionIterator() {
	    recentRemoval = true;
	    nextIndex = 0;
	}

	
	public boolean hasNext() {
	    return (nextIndex < size);
	}

	
	public T next() {

	    if (!hasNext())
		throw new NoSuchElementException();
	    recentRemoval = false;
	    return data[nextIndex++];
	}

	
	public void remove() {
	    if (recentRemoval)
		throw new IllegalStateException();
	    ArrayCollection.this.remove(data[--nextIndex]);
	    recentRemoval = true;
	}
    }
}
