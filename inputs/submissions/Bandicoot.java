//comments: 27
//comment length: 3583
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

		T tempArray[] = (T[]) new Object[size * 2];

		for (int i = 0; i < size; i++) {
			tempArray[i] = data[i];

		}
		data = tempArray;
	}

	
	public boolean add(T arg0) {
		for (int i = 0; i < size; i++) {
			if (arg0.equals(data[i])) {
				return false;
			}
		}
		if (!(size < data.length)) {
			grow();
		}
		data[size++] = arg0;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		int timesAdded = 0;
		for (T i : arg0) {
			for (int j = 0; j < size; j++) {
				if (data[j].equals(i)) {
					continue;
				}
			}
			timesAdded++;
			this.add(i);

		}
		return timesAdded > 0;
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

		for (Object i : arg0) {
			for (int j = 0; j < size; j++) {
				if (!this.contains(i)) {
					return false;
				}
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		
		for (int i = 0; i < size; i++) {
			if (data[i] != null) {

				return false;

			}
		}
		return true;

	}

	
	public Iterator<T> iterator() {

		return new ArrayCollectionIterator();

		

	}

	
	public boolean remove(Object arg0) {
		
		if (!this.contains(arg0)) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				for (int j = i; j < size; j++) {
					data[j] = data[j + 1];
				}
				data[size--] = null;
				return true;
			}
		}
		return false;

	}

	
	public boolean removeAll(Collection<?> arg0) {
		int timesRemoved = 0;

		for (Object i : arg0) {
			if (this.contains(i)) {
				for (int j = 0; j < size; j++) {
					if (this.data[j].equals(i)) {
						this.remove(data[j]);
						timesRemoved++;
					}
				}
			}
		}
		return timesRemoved > 0;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		int timesRemoved = 0;

		ArrayCollectionIterator itr = new ArrayCollectionIterator();

		while (itr.hasNext()) {
			if (!arg0.contains(itr.next())) {
				itr.remove();
				timesRemoved++;
			}

		}

		return timesRemoved > 0;
	}

	public int size() {
		return size;
	}

	public Object[] toArray() {
		Object[] newData = new Object[size];

		for (int i = 0; i < size; i++) {
			newData[i] = data[i];
		}

		return newData;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> sorted = new ArrayList<T>(this);

		for (int i = 0; i < this.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < this.size(); j++)
				if (cmp.compare(sorted.get(i), sorted.get(minIndex)) < 0)
					minIndex = j;

		}
		return sorted;
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

			if (nextIdx == size) {
				throw new NoSuchElementException();
			}
			nextCalled = true;
			return data[nextIdx++];
		}

		public void remove() {

			if (!nextCalled) {
				throw new IllegalStateException("next not called");
			}


			ArrayCollection.this.remove(data[--nextIdx]);

			nextCalled = false;
		}

	}

}
