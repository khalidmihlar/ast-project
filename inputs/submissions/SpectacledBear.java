//comments: 30
//comment length: 2081
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

		T[] doubleData = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			doubleData[i] = data[i];
		}
		data = doubleData;
	}

	public boolean add(T arg0) {
		if (this.contains(arg0)) {
			return false;
		}
		if (size == data.length) {
			grow();
		}
		data[size] = arg0;
		size++;
		return true;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		Iterator<Object> addAllIterator = (Iterator<Object>) arg0.iterator();
		boolean addAllBoolean = false;
		for (Object element : arg0) {
			if (this.add((T) element)) {
				addAllBoolean = true;
			}
		}
		return addAllBoolean;
	}













	public void clear() {
		data = (T[]) new Object[10];
		size = 0;
	}

	public boolean contains(Object arg0) {
		for (int i = 0; i < data.length; i++) {
			if (arg0.equals(data[i])) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		Iterator<Object> containsAllIterator = (Iterator<Object>) arg0.iterator();
		boolean boolContains = true;
		while (containsAllIterator.hasNext()) {
			if (!this.contains(containsAllIterator.next())) {
				boolContains = false;
			}
		}

		return boolContains;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	public boolean remove(Object arg0) {
		if (!(this.contains(arg0))) {
			return false;
		} else {
			for (int i = 0; i < this.size; i++) {
				if (data[i].equals(arg0)) {
					for (int j = i + 1; j < this.size; j++) {
						data[i] = data[j];
					}
				}
			}
			data[size] = null;
			size--;
		}

		return true;
	}

	public boolean removeAll(Collection<?> arg0) {
		
		boolean removeBool = false;
		for (Object element : arg0) {
			if (this.remove(element)) {
				removeBool = true;
			}
		}
		return removeBool;
	}

	public boolean retainAll(Collection<?> arg0) {
		boolean removeBool = false;
		Iterator<T> retainIterator = iterator();







		for (T element : this) {
			if (!(arg0.contains(element))) {
				this.remove(element);
				removeBool = true;
			}
		}
		return removeBool;
	}

	public int size() {
		return size;
	}

	public Object[] toArray() {
		T[] toArray = (T[]) new Object[size];
		for(int i = 0; i < data.length; i++) {
			toArray[i] = data[i];
		}
		return toArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return (T[]) data;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		class NewComparator implements Comparator<Integer> {
			@Override
			public int compare(Integer first, Integer second) {
				return first - second;
			}
		}
		for (int i = 0; i < data.length - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < data.length; j++)
				if (cmp.compare(data[minIndex], data[j]) > 0) {
					T temp = data[minIndex];
					data[minIndex] = data[j];
					data[j] = temp;
				}
		} 
		return null;

	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int nextIdx = 0;
		private boolean nextCalled = false;

		public ArrayCollectionIterator() {

		}

		public boolean hasNext() {
			return nextIdx < size;
		}

		public T next() {
			if (nextIdx > size) {
				throw new NoSuchElementException();
			}
			nextCalled = true;
			nextIdx++;
			return data[nextIdx - 1];
		}

		public void remove() {
			if (nextCalled != true) {
				throw new IllegalStateException();
			} else {
				nextCalled = false;
				ArrayCollection.this.remove(data[nextIdx - 1]);
			}
		}

	}

}
