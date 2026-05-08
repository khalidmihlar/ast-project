//comments: 53
//comment length: 5689

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
		
		
		T[] doubleData = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			doubleData[i] = data[i];
		}
		
		this.data = doubleData;
	}

	
	public boolean add(T arg0) {
		
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
		
		boolean result = false;
		for (T arg : arg0) {
			if (!this.contains(arg)) {
				this.add(arg);
				result = true;
			}
		}
		return result;
	}

	
	public void clear() {
		
		
		for (int i = 0; i < this.data.length; i++) {
			this.data[i] = null;
		}
		this.size = 0;
	}

	
	public boolean contains(Object arg0) {
		
		for (int i = 0; i < this.size; i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		
		boolean result = true;
		for (Object arg : arg0) {
			if (!this.contains(arg)) {
				result = false;
			}
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
		
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		
		for (int i = 0; i < this.size; i++) {
			if (this.data[i].equals(arg0)) {
				for (int j = i; j < this.size; j++) {
					this.data[j] = this.data[j + 1];
					this.data[j + 1] = null;
				}
				this.size = size - 1;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		
		boolean result = false;
		for (Object arg : arg0) {
			if (this.contains(arg)) {
				result = true;
				this.remove(arg);
			}
		}
		return result;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		boolean result = false;
		Iterator<T> arrayIterator = this.iterator();

		while (arrayIterator.hasNext()) {
			T val = (T) arrayIterator.next();
			if (!arg0.contains(val)) {
				arrayIterator.remove();
				result = true;
			}
		}
		return result;
	}

	
	public int size() {
		
		return this.size;
	}

	
	public Object[] toArray() {
		
		Object[] newDataArray = new Object[this.size];
		for (int i = 0; i < this.size; i++) {
			newDataArray[i] = this.data[i];
		}
		return newDataArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	public String toString() {
		return Arrays.toString(data);
	}

	public int arrayLength() {
		return this.data.length;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		for (int i = 0; i < this.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < this.size(); j++)
				if (cmp.compare(this.data[j], this.data[minIndex]) < 0)
					minIndex = j;
			T temp = this.data[i];
			this.data[i] =  this.data[minIndex];
			this.data[minIndex] = temp;
		}
		ArrayList<T> sortedList = new ArrayList<T>();
		sortedList.addAll(this);
		return sortedList;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		int ind; 
		boolean nextCalled; 
		

		public ArrayCollectionIterator() {
			
			ind = -1; 
			nextCalled = false; 
		}

		
		public boolean hasNext() {
			
			
			if (ArrayCollection.this.size() > ind + 1) {
				return true;
			}
			return false;
		}

		
		public T next() {
			
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				nextCalled = false;
				return data[++ind];
			}
		}

		
		public void remove() {
			
			if (!this.nextCalled) {
				throw new IllegalStateException();
			} else {
				ArrayCollection.this.remove(ind--);
				nextCalled = true;
			}
		}
	}
}
