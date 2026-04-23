//comments: 52
//comment length: 4495
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
		
		if (size == data.length) {
			T temp[] = (T[]) new Object[size * 2];
			
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}
	}

	
	public boolean add(T arg0) {
		
		if (contains(arg0)) {
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
		boolean b = false;
		
		
		for (T item : arg0) {
			if (!contains(item)) {
				add(item);
				b = true;
				size++;
			}
		}
		return b;
	}

	
	public void clear() {
		
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		
		for (int i = 0; i < size; i++) {
			if (arg0.equals(data[i])) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		
		for (Object item : arg0) {
			if (!contains(item)) {
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
		
		if (!contains(arg0)) {
			return false;
		}
		
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				index = i;
			}
		}
		for (int i = index; i < size; i++) {
			if (i == size - 1) {
				data[i] = null;
			}
			data[i] = data[i + 1];
		}
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean b = false;
		
		
		for (Object temp : arg0) {
			if (contains(temp)) {
				remove(temp);
				b = true;
			}
		}
		return b;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean b = false;
		
		ArrayCollectionIterator iterator = new ArrayCollectionIterator();
		
		while (iterator.hasNext()) {
			if (contains(iterator.next())) {
				iterator.remove();
				b = true;
			}
		}
		return b;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		T temp[] = (T[]) new Object[size];
		
		for (int i = 0; i < size; i++) {
			temp[i] = data[i];
		}
		return temp;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> sorted = new ArrayList<T>();
		
		for (int i = 0; i < size; i++) {
			int minIndex = i;

			for (int j = i + 1; j < size; j++) {
				if (cmp.compare(data[j], data[minIndex]) < 0) {
					minIndex = j;
				}
			}
			
			
			T temp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = temp;
			sorted.add(data[i]);
		}
		return sorted;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		
		private int next;
		
		
		boolean nextCalled;

		public ArrayCollectionIterator() {
			next = 0;
			nextCalled = false;
		}

		
		public boolean hasNext() {
			return next < size;
		}

		
		public T next() {
			
			
			try {
				T temp = data[next++];
				nextCalled = true;
				return temp;
			} catch (Exception e) {
				throw new NoSuchElementException();
			}
		}

		
		public void remove() {
			
			
			if (!nextCalled) {
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(next());
			next--;
		}

	}

}
