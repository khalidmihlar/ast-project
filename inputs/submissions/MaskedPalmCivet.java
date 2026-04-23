//comments: 28
//comment length: 3158
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
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
		T temp[] = (T[]) new Object[data.length * 2];

		for (int i = 0; i < size; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	
	public boolean add(T arg0) {

		if (this.contains(arg0))
			return false;

		if (size == data.length)
			this.grow();

		data[size] = arg0;
		size++;

		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean result = false;
		for (T i : arg0) {
			result = this.add(i) || result;
		}

		return result;
	}

	
	public void clear() {

		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		boolean result = true;
		for (Object i : arg0) {
			result = this.contains(i) && result;
		}
		return result;
	}

	
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}

	
	public Iterator<T> iterator() {
		ArrayCollectionIterator res = new ArrayCollectionIterator();
		return res;
	}

	
	public boolean remove(Object arg0) {
		boolean bool = false;
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				bool = true;
				i++;
			}
			if (bool) {
				data[i - 1] = data[i];
			}
		}
		if (bool) {
			data[size - 1] = null;
			size--;
		}
		return bool;

	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean result = false;
		for (Object i : arg0) {
			result = this.remove(i) || result;
		}

		return result;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean temp = false;
		Iterator<T> iterator = this.iterator();
		while (iterator.hasNext()) {
			if (!arg0.contains(iterator.next())) {
				iterator.remove();
				temp = true;
			}
		}
		return temp;
	}
	
	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object temp[] = new Object[this.size];
		for (int i = 0; i < size; i++) {
			temp[i] = this.data[i];
		}
		return temp;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {

		T tempArray[] = (T[]) this.toArray();
		for (int i = 0; i < this.size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < this.size; j++)
				if (cmp.compare(tempArray[j], tempArray[minIndex]) < 0)
					minIndex = j;
			Object temp = tempArray[i];
			tempArray[i] = tempArray[minIndex];
			tempArray[minIndex] = (T) temp;
		}
		return new ArrayList<T>(Arrays.asList(tempArray));
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
		int nextIndex = 0;
		private boolean legal = false;

		public ArrayCollectionIterator() {

		}
		
		
		public boolean hasNext() {

			return nextIndex < size;
		}

		
		public T next() {
			if (!(this.hasNext()))
				throw new NoSuchElementException("No such Element!");

			legal = true;
			return data[nextIndex++];

		}
		
		
		public void remove() {
			if (!legal) {
				throw new IllegalStateException("can only call remove once per next.");
			}
			ArrayCollection.this.remove(data[nextIndex - 1]);
			nextIndex--;
			legal = false;
		}

	}

}
