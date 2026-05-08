//comments: 56
//comment length: 5865
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
		for (int i = 0; i < size; i++) {
			temp[i] = data[i];
		}

		data = temp;

	}

	
	public boolean add(T arg0) {

		if (arg0 == null) {
			return false;
		}
		
		if (this.contains(arg0)) {
			return false;
		}

		
		if (this.size == data.length) {
			this.grow();

		}
		
		data[size++] = arg0;

		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean added = false;
		for (T t : arg0) {
			
			if (t == null) {
				continue;
			}
			if (this.add(t)) {
				added = true;
			}
		}

		return added;
	}

	
	public void clear() {
		size = 0;

	}

	
	public boolean contains(Object arg0) {
		if (arg0 == null) {
			return false;
		}

		for (int i = 0; i < size; i++) {

			if (arg0.equals(data[i])) {
				return true;
			}
		}
		return false;

	}

	
	public boolean containsAll(Collection<?> arg0) {

		if (arg0.size() == 0) {
			return false; 
		}
		for (Object t : arg0) {
			if (!this.contains(t)) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {

		return this.size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	@SuppressWarnings("unchecked")
	public boolean remove(Object arg0) {
		if (!this.contains(arg0)) { 
			return false;
		}
		int index = 0; 
		T temp[] = (T[]) new Object[data.length];
		for (int i = 0; i < size; i++) { 

			
			if (arg0.equals(data[i])) {
				index = i;
				break;
			} else {
				temp[i] = data[i];
			}

		}
		
		for (int i = index; i < size - 1; i++) {
			temp[i] = data[i + 1]; 
		}
		size--;

		this.data = temp; 
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		
		boolean removed = false;

		
		for (Object t : arg0) {

			
			if (this.contains(t)) {

				removed = true;

			}
		}
		return removed;

	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<T> iterator = new ArrayCollectionIterator();
		boolean removed = false; 
		while (iterator.hasNext()) {
			T element = iterator.next();
			if (!arg0.contains(element)) { 
				iterator.remove();
				removed = true;
			}

		}

		return removed;
	}

	
	public int size() {

		return this.size;
	}

	
	public Object[] toArray() {
		Object newArray[] = new Object[size]; 
		for (int i = 0; i < this.size; i++) {
			newArray[i] = data[i];
		}
		return newArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> sortedList = new ArrayList<T>();
		sortedList.addAll(this);
		for (int i = 0; i < size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare(sortedList.get(j), sortedList.get(minIndex)) < 0)
					minIndex = j;
			T temp = sortedList.get(i);
			sortedList.set(i, sortedList.get(minIndex));
			sortedList.set(minIndex, temp);
		}

		
		
		

		return sortedList;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
		private int nextIdx = 0;
		private boolean next = false;

		public ArrayCollectionIterator() {
		}

		
		public boolean hasNext() {

			return nextIdx < size;
		}

		
		public T next() {
			if (!this.hasNext()) { 
				throw new NoSuchElementException();
			}
			next = true;
			return data[nextIdx++];
		}

		
		public void remove() {
			if (!next) { 
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(data[nextIdx - 1]); 
			next = false;
			nextIdx--;

		}

	}

}
