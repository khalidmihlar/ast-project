//comments: 27
//comment length: 3160
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	T data[]; 
	int size; 


	
	@SuppressWarnings("unchecked")
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}
	
	@SuppressWarnings("unchecked")
	private void grow() {

		T temp[] = (T[]) new Object[data.length * 2];
		for (int index = 0; index < data.length; index++) {
			temp[index] = data[index];
		}
		data = temp;
	}
	
	public boolean add(T arg0) {

		if (!contains(arg0)) {
			if (data.length == size) {
				grow();
			}
			data[size] = arg0;
			size++;
			return true;
		}

		return false;
	}
	
	public boolean addAll(Collection<? extends T> arg0) {

		boolean elementAdded = false;

		for (T element : arg0) {
			if (!contains(element)) {
				elementAdded = add(element);
			}
		}
		return elementAdded;
	}
	
	public void clear() {
		size = 0;
	}
	
	public boolean contains(Object arg0) {

		for (int index = 0; index < size; index++) {
			if (data[index].equals(arg0)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAll(Collection<?> arg0) {

		for (Object element : arg0) {
			if (!contains(element)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}
	
	public boolean remove(Object arg0) {

		for (int index = 0; index < size; index++) {
			if (data[index].equals(arg0)) {
				for (int j = index; j < size - 1; j++) {
					if (j == size - 1) {
						data[j] = null;
					} else {
						data[j] = data[j + 1];
					}
				}
				data[size - 1] = null;
				size--;
				return true;
			}
		}

		return false;
	}
	
	public boolean removeAll(Collection<?> arg0) {

		boolean removedItem = false;

		for (Object element : arg0) {
			if (contains(element)) {
				removedItem = remove(element);
			}
		}

		return removedItem;
	}
	
	public boolean retainAll(Collection<?> arg0) {

		boolean removed = false;

		Iterator<T> itr = iterator();
		while (itr.hasNext()) {
			T element = itr.next();
			if (!arg0.contains(element)) {
				itr.remove();
				removed = true;
			}
		}

		return removed;
	}
	
	public int size() {
		return size;
	}
	
	@SuppressWarnings("unchecked")
	public Object[] toArray() {

		T result[] = (T[]) new Object[size];
		for (int index = 0; index < size; index++) {
			result[index] = data[index];
		}
		return result;
	}
	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {

		ArrayList<T> sorted = new ArrayList<>();
		for (int index = 0; index < size - 1; index++) {
			int min = index;
			for (int secondIdx = index + 1; secondIdx < size; secondIdx++) {
				if (cmp.compare(data[min], data[secondIdx]) > 0) {
					min = secondIdx;
				}
			}
			T temp = data[index];
			data[index] = data[min];
			data[min] = temp;
		}
		for (int idx = 0; idx < size; idx++) {
			sorted.add(data[idx]);
		}
		return sorted;
	}
	
	private class ArrayCollectionIterator implements Iterator<T> {

		boolean nextItem;
		int index;

		public ArrayCollectionIterator() {
			nextItem = false;
			index = -1;
		}
		
		public boolean hasNext() {

			if (data[index + 1] != null) {
				return true;
			}
			return false;
		}
		
		public T next() {
			if (hasNext()) {
				nextItem = true;
				return data[++index];
			} else {
				throw new NoSuchElementException();
			}

		}
		
		public void remove() {
			if (nextItem) {
				nextItem = false;
				ArrayCollection.this.remove(data[index--]);
			} else {
				throw new IllegalStateException();
			}
		}

	}

}
