//comments: 85
//comment length: 4238
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
		this.size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		T valueKeeper[] = (T[]) new Object[data.length * 2];
		
		for (int i = 0; i < data.length; i++) {
			valueKeeper[i] = data[i];
		}
		
		data = valueKeeper;
		
		
		
	}

	public boolean add(T arg0) {
		
		if (this.size == data.length) {
			grow();
		}
		if (this.size > 0) {
			for (int i = 0; i < this.size; i++) {
				if (data[i] == arg0) { 
					return false;
				}
			}
			data[this.size] = arg0;
			this.size++;
			return true;
		} else {
			data[this.size] = arg0;
			this.size++;

			
			return true;
		}
	}

	public boolean addAll(Collection<? extends T> arg0) {

		if (this.size == data.length) {
			grow();
		}
		int comparewithsize = this.size;
		
		for (Object e : arg0) {
			add((T) e); 
		}
		if (comparewithsize < this.size) {
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		data = (T[]) new Object[10];
		this.size = 0;
	}

	public boolean contains(Object arg0) {
		if (arg0 == null || arg0.equals(null)) {
			return false;
		}
		for (int i = 0; i < this.size; i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		int count = 0;
		for (Object e : arg0) {
			if (contains(e) == true) {
				count++;
			}
		}
		if (count == arg0.size()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		
		return false;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
		
	}

	public boolean remove(Object arg0) {
		int position = 0;
		boolean resultToReturn = false;
		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(arg0)) {
				position = i;
				resultToReturn = true;
				break;
			}
		}
		for (int j = position; j < this.size - 1; j++) {
														
			
			data[j] = data[j + 1];
		}
		data[this.size - 1] = data[this.size];
		this.size--;
		
		return resultToReturn;
	}

	public boolean removeAll(Collection<?> arg0) {

		int comparewithsize = this.size;
		for (Object e : arg0) {
			remove(e);
		}
		if (comparewithsize > this.size) {
			return true;
		} else {
			return false;
		}
	}

	public boolean retainAll(Collection<?> arg0) {
		T result[] = (T[]) new Object[data.length];
		int position = 0;

		for (Object e : arg0) {

			if (this.contains(e)) {
				result[position] = (T) e;
				position++;
			}
		}
		this.data = result;
		if (position < this.size) {
									
			this.size = position;
			return true;
		}
		return false;
	}

	public int size() {
		return this.size;
	}

	public Object[] toArray() {
		Object[] objectArray = new Object[this.size];
		for (int i = 0; i < this.size; i++) {
			objectArray[i] = data[i];
		}
		return objectArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	

	private void sort(T[] list, Comparator<? super T> c) {
		for (int i = 0; i < this.size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < this.size; j++)
				if (c.compare(list[j], list[minIndex]) < 0)
					minIndex = j;
			T temp = list[i];
			list[i] = list[minIndex];
			list[minIndex] = temp;
		}
	}

	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		sort(this.data, cmp);
		ArrayList<T> resultToReturn = new ArrayList<T>();
		for (int i = 0; i < this.size; i++) {
												
			resultToReturn.add(data[i]);
		}
		return resultToReturn;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int nextIdx;
		private boolean forRemove;

		public ArrayCollectionIterator() {
			nextIdx = 0;
			forRemove = false;
		}

		public boolean hasNext() {
			if (nextIdx >= size) {
				return false;
			} else {

				return true;
			}
		}

		public T next() {
			forRemove = true;
			nextIdx++;
			if (nextIdx > size) {
				throw new NoSuchElementException();
			}
			return data[nextIdx - 1];
		}

		public void remove() {
			if (forRemove == true) {
				ArrayCollection.this.remove(data[nextIdx - 1]);
				
				nextIdx--;
				forRemove = false;
			} else {
				throw new IllegalStateException();
			}
		}

	}

}
