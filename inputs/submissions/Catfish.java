//comments: 28
//comment length: 5447
package assignment3;

import java.util.ArrayList;
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
	
	
	T[] newArray = (T[]) new Object[data.length * 2];

	for (int i = 0; i < size; i++) {
	    newArray[i] = data[i];
	}

	data = newArray;

    }

    public boolean add(T arg0) {
	if (size == data.length) {
	    grow();
	}
	for (int i = 0; i < data.length; i++) {
	    if (data[i] == arg0) {
		return false;
	    }
	}
	data[size] = arg0;
	size++;
	return true;
    }

    
    public boolean addAll(Collection<? extends T> arg0) {
	boolean checker = false;

	if (size == data.length) {
	    grow();
	}
	for (T thing : data) {
	    if (!arg0.contains(thing)) {
		this.add(thing);
		checker = true;
	    }
	}
	return checker;
    }

    
    public void clear() {
	for (int i = 0; i < data.length; i++) {
	    data[i] = null;
	}
	size = 0;
    }

    
    public boolean contains(Object arg0) {
	for (T thing : data) {
	    if (thing == arg0) {
		return true;
	    }
	}

	return false;
    }

    
    public boolean containsAll(Collection<?> arg0) {
	for (T thing : data) {
	    if (!arg0.contains(thing)) {
		return false;
	    }
	}
	return true;
    }

    
    public boolean isEmpty() {
	if (size == 0) {
	    return true;
	}
	return false;
    }

    
    public Iterator<T> iterator() {
	return new ArrayCollectionIterator();
    }

    
    public boolean remove(Object arg0) {
	boolean checker = false;

	for (int i = 0; i < size; i++) {
	    if (data[i] == arg0) {
		data[i] = null;
		checker = true;
		for (int j = i; j < size; j++) {
		    data[j] = data[j + 1];
		}
	    }
	}
	return checker;
    }

    
    public boolean removeAll(Collection<?> arg0) {
	boolean checker = false;

	for (T item : data) {
	    if (arg0.contains(item)) {
		this.remove(item);
		checker = true;
	    }
	}
	return checker;

    }

    
    public boolean retainAll(Collection<?> arg0) {
	boolean checker = false;
	for (T item : data) {
	    if (!arg0.contains(item)) {
		this.remove(item);
		checker = true;
	    }
	}
	return checker;
    }

    
    public int size() {
	return size;
    }

    
    public Object[] toArray() {
	Object[] temp = new Object[size()];

	for (int i = 0; i < size; i++) {
	    if (data[i] != null) {
		temp[i] = data[i];
	    }
	}

	return temp;
    }

    
    @SuppressWarnings("hiding")
    public <T> T[] toArray(T[] arg0) {
	return null;
    }

    

    
    public ArrayList<T> toSortedList(Comparator<? super T> cmp) {

	ArrayList<T> list = new ArrayList<T>(this);

	for (int i = 0; i < size - 1; i++) {
	    int j, minIndex;
	    for (j = i + 1, minIndex = i; j < size; j++)
		if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
		    minIndex = j;
	    T temp = list.get(i);
	    list.set(i, list.get(minIndex));
	    list.set(minIndex, temp);

	}
	return list;
    }

    private class ArrayCollectionIterator implements Iterator<T> {

	private int currentPosition;

	@SuppressWarnings("unused")
	private boolean canBeRemoved;

	public ArrayCollectionIterator() {
	    currentPosition = 0;
	    canBeRemoved = false;
	}

	
	public boolean hasNext() {
	    if (data[currentPosition + 1] != null) {
		return true;
	    }
	    return false;
	}

	
	public T next() {
	    T currentSpot = data[currentPosition];
	    currentPosition++;
	    canBeRemoved = true;
	    return currentSpot;
	}

	
	public void remove() {
	    if (canBeRemoved = true) {
		ArrayCollection.this.remove(currentPosition);
		currentPosition--;
	    }
	    canBeRemoved = false;

	}

    }
}
