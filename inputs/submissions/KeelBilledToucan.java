//comments: 81
//comment length: 9241
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

		
		T tempData[] = (T[]) new Object[size * 2];
		for (int i = 0; i < data.length; i++) {
			tempData[i] = data[i];
		}
		data = tempData;
	}

	
	public boolean add(T arg0) {
		
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				return false;
			}
		}

		
		if (size == data.length) {
			grow();
		}

		
		data[size] = arg0;
		size++;

		
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean isAdded = false;

		
		for (T item : arg0) {
			
			if (this.add(item)) {
				isAdded = true;
			}
		}
		return isAdded;
	}

	
	public void clear() {
		
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}

		
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		
		if (size == 0) {
			return false;
		}

		
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
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
		
		
		return (size == 0);
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		
		for (int i = 0; i < size; i++) {

			if (data[i].equals(arg0)) {
				
				for (int j = i + 1; j < size; j++) {
					data[j - 1] = data[j];
				}

				
				data[size - 1] = null;
				
				size--;
				
				return true;
			}
		}

		
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		
		boolean removed = false;

		
		for (Object item : arg0) {
			
			if (contains(item)) {
				remove(item);
				removed = true;
			}
		}

		
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		
		if (arg0.isEmpty() && size > 0) {
			removeAll(this);
			return true;
		}

		
		boolean removed = false;

		
		for (Object item : arg0) {
			
			if (item == null) {
				continue;
			}

			
			if (!contains(item)) {
				remove(item);
				removed = true;
			}
		}

		
		return removed;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		
		Object[] arr = new Object[size];

		
		for (int i = 0; i < size; i++) {
			arr[i] = data[i];
		}

		
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		
		ArrayList<T> arr = new ArrayList<T>();

		
		arr.addAll(this);

		
		for (int i = 0; i < arr.size() - 1; i++) {
			int j, minIndex;

			
			for (j = i + 1, minIndex = i; j < arr.size(); j++)
				if (cmp.compare(arr.get(j), arr.get(minIndex)) < 0) {
					minIndex = j;
				}

			
			
			T temp = arr.get(i);
			
			arr.set(i, arr.get(minIndex));
			
			arr.set(minIndex, temp);
		}

		
		return arr;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {

		
		private int currentIndex = 0;

		
		private boolean canRemove = false;

		public ArrayCollectionIterator() {
			super();
		}

		
		public boolean hasNext() {
			return currentIndex < size;
		}

		
		public T next() {
			
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			canRemove = true;

			
			return data[currentIndex++];
		}

		
		public void remove() {
			
			if (canRemove == false) {
				throw new IllegalStateException();
			}

			
			
			ArrayCollection.this.remove(currentIndex);

			
			canRemove = false;
		}

	}

}
