//comments: 10
//comment length: 1618
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	private T data[]; 
	private int size; 

	public ArrayCollection() {
		this(0);
	}
	

	@SuppressWarnings("unchecked")
	public ArrayCollection(int size) {
		this.size = 0;
		int capacity = 10; 
		while (capacity < size) {
			capacity *= 2;
		}
		
		data = (T[]) new Object[capacity];
	}
	
	public ArrayCollection(T[] arr) {
		this(arr.length);
		for (T e: arr) {
			add(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void grow() {
		
		T temp[] = (T[]) new Object[2 * size];
		for (int i = 0; i < size; ++i) {
			temp[i] = data[i];
		}
		data = temp;
	}

	public boolean add(T arg0) {
		for (int i = 0; i < size; ++i) {
			if (arg0.equals(data[i])) {
				return false;
			}
		}
		if (size == data.length) {
			grow();
		}
		data[size++] = arg0;
		return true;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		boolean added = false;
		for (T e: arg0) {
			added |= add(e);
		}
		return added;
	}

	public void clear() {
		for (int i = 0; i < size; ++i) {
			data[i] = null;
		}
		size = 0;
	}

	public boolean contains(Object arg0) {
		for (int i = 0; i < size; ++i) {
			if (arg0.equals(data[i])) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		for (Object e: arg0) {
			if (!contains(e)) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	public boolean remove(Object arg0) {
		for (int i = 0; i < size; ++i) {
			if (arg0.equals(data[i])) {
				--size;
				for (int j = i; j < size; ++j) {
					data[j] = data[j + 1];
				}
				return true;
			}
		}
		return false;
	}

	public boolean removeAll(Collection<?> arg0) {
		boolean removed = false;
		for (Object e: arg0) {
			removed |= remove(e);
		}
		return removed;
	}

	public boolean retainAll(Collection<?> arg0) {
		boolean removed = false;
		ArrayCollectionIterator iter = new ArrayCollectionIterator();
		while (iter.hasNext()) {
			if (!arg0.contains(iter.next())) {
				iter.remove();
				removed |= true;
			}
		}
		return removed;
	}

	public int size() {
		return size;
	}
	
	public int length() {
		return data.length;
	}

	public Object[] toArray() {
		Object arr[] = new Object[size];
		for (int i = 0; i < size; ++i) {
			arr[i] = data[i];
		}
		return arr;
	}

	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}


	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> arr = new ArrayList<T>(Arrays.asList(data).subList(0, size));
		Collections.sort(arr, cmp);
		return arr;
	}
	
	private class ArrayCollectionIterator implements Iterator<T> {
		int i;
		boolean removeAllowed;
		
		public ArrayCollectionIterator() {
			i = 0;
			removeAllowed = false;
		}

		public boolean hasNext() {
			return i < size;
		}

		public T next() {
			if (i < size) {
				removeAllowed |= true;
				return data[i++];
			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			if (removeAllowed) {
				--size;
				for (int j = i - 1; j < size; ++j) {
					data[j] = data[j + 1];
				}
				removeAllowed = false;
				--i;
			} else {
				throw new IllegalStateException();
			}
		}
	}
}
