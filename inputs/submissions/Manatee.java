//comments: 27
//comment length: 5460
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
		Object[] newData = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++)
			newData[i] = data[i];
		data = (T[]) newData;
	}

	
	public boolean add(T arg0) {
		for (int i = 0; i < size; i++)
			if (data[i].equals(arg0))
				return false;
		if (size == data.length)
			grow();
		data[size++] = arg0;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean flag = false;
		for (T elem : arg0)
			if (add(elem))
				flag = true;
		return flag;
	}

	
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++)
			if (data[i].equals(arg0))
				return true;
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for (Object elem : arg0)
			if (!contains(elem))
				return false;
		return true;
	}

	
	public boolean isEmpty() {
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		int idx = 0;
		boolean isInCollection = false;
		while (idx < size) {
			if (data[idx].equals(arg0)) {
				isInCollection = true;
				break;
			}
			++idx;
		}
		if (isInCollection) {
			for (int i = idx; i < size - 1; i++) {
				data[i] = data[i + 1];
			}
			data[size - 1] = null;
			--size;
		}

		return isInCollection;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean isInCollection = false;
		for (Object elem : arg0) {
			if (remove(elem))
				isInCollection = true;
		}
		return isInCollection;
	}

	
	@SuppressWarnings("unchecked")
	public boolean retainAll(Collection<?> arg0) {
		int iterator = 0;
		Object[] unionElements = new Object[data.length];
		for (Object elem : arg0) {
			if (contains(elem))
				unionElements[iterator++] = elem;
		}
		data = (T[]) unionElements;
		if (iterator < size) {
			size = iterator;
			return true;
		}
		return false;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] dataCopy = new Object[size];
		for (int i = 0; i < size; i++)
			dataCopy[i] = data[i];
		return dataCopy;
	}

	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T[] toArray(T[] arg0) {
		Object[] newArrayOfData = (T[]) new Object[size];
		for (int i = 0; i < size; i++)
			newArrayOfData[i] = data[i];
		return (T[]) newArrayOfData;

	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> copy = new ArrayList<T>();
		copy.addAll(this);

		for (int i = 0; i < copy.size() - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < copy.size(); j++) {
				if (cmp.compare(copy.get(j), copy.get(minIndex)) < 0) {
					minIndex = j;
				}
			}
			T temp = copy.get(minIndex);
			copy.set(minIndex, copy.get(i));
			copy.set(i, temp);
		}
		return copy;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		int idx = 0;
		boolean isLegalToCallRemove = false;

		public ArrayCollectionIterator() {
			idx = 0;
			isLegalToCallRemove = false;
		}

		
		public boolean hasNext() {
			return idx < size;
		}

		
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException("There are no more items to iterate");
			isLegalToCallRemove = true;
			return data[idx++];
		}

		
		public void remove() {
			if (!isLegalToCallRemove)
				throw new IllegalStateException("Need to call next() before every call to remove()");
			ArrayCollection.this.remove(data[idx]);
			--idx; 
		}

	}

}
