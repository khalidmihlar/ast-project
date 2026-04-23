//comments: 45
//comment length: 4852
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
		
		
		
		T doubleData[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			doubleData[i] = data[i];
		}
		data = doubleData;
	}

	
	public boolean add(T arg0) {
		
		if (this.contains(arg0)) {
			return false;
		}
		if (size >= data.length) {
			this.grow();
		}
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean added = false;
		for (T item : arg0) {
			if (this.add(item)) {
				added = true;
			}
		}
		return added;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		
		data = (T[]) new Object[10];
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null) {
				if (data[i].equals(arg0)) {
					return true;
				}
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		
		for (Object item : arg0) {
			if (!this.contains(item)) {
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
		
		if (!this.contains(arg0)) {
			return false;
		}
		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(arg0)) {
				data[i] = null;
				for (int j = i + 1; j <= size; j++) {
					if (i == size - 1) {
						data[i] = null;
						size--;
						return true;
					}
					data[i] = data[j];
					i++;
				}
			}
		}
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		
		boolean removed = false;
		for (Object item : arg0) {
			if (this.remove(item)) {
				removed = true;
			}
		}
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		boolean removed = false;
		ArrayCollectionIterator iter = new ArrayCollectionIterator();
		while (iter.hasNext()) {
			if (removed) {
				iter.nextIndex = iter.nextIndex - 1;
			}
			if (!arg0.contains(iter.next())) {
				iter.remove();
				removed = true;
			} else {
				removed = false;
			}
		}
		iter.nextIndex = iter.nextIndex - 1;
		if (!arg0.contains(iter.next())) {
			iter.remove();
			removed = true;
		}
		return removed;
	}

	
	public int size() {
		
		return size;
	}

	
	public Object[] toArray() {
		
		Object[] collectionArray = new Object[size];
		for (int i = 0; i < size; i++) {
			collectionArray[i] = data[i];
		}
		return collectionArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		list.addAll(this);
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++) {
				if (cmp.compare(list.get(j), list.get(minIndex)) < 0) {
					minIndex = j;
				}
			}
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
		return list;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int nextIndex; 

		private boolean called; 

		public ArrayCollectionIterator() {
			
			nextIndex = 0;
			called = false;
		}

		
		public boolean hasNext() {
			
			return nextIndex < size;
		}

		
		public T next() {
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			called = true;
			return data[nextIndex++];
		}

		
		public void remove() {
			if (!called) {
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(data[nextIndex - 1]);
			return;
		}
	}
}