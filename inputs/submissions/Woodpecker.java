//comments: 29
//comment length: 3231
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

	@Override
	public boolean equals(Object other) {
		if (other instanceof ArrayCollection<?>) {
			ArrayCollection<?> otherc = (ArrayCollection<?>) other;
			Object[] othera = otherc.toArray();
			Object[] thisa = toArray();
			if (otherc.size() != size()) {
				return false;
			}
			for (int i = 0; i < otherc.size(); i++) {
				if (!(othera[i].equals(thisa[i]))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		T[] data2 = (T[]) new Object[2 * (data.length)];
		for (int i = 0; i < data.length; i++) {
			data2[i] = data[i];
		}
		data = data2;
	}

	
	public boolean add(T arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i] == arg0) {
				return false;
			}
		}
		if (size + 1 >= data.length) {
			grow();
		}
		data[size] = (arg0);
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean addedItemsp = false;
		for (Object o : arg0) {
			T newo = (T) o;
			add(newo);
			addedItemsp = true;
		}
		return addedItemsp;
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
		for (Object o : arg0) {
			if (!contains(o)) {
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
		for (int i = 0; i < size; i++) {
			if (arg0.equals(data[i])) {
				
				for (int j = i; j < size; j++) {
					data[j] = data[j + 1];
				}
				data[size - 1] = null;
				size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean removedanyp = false;
		for (Object item : arg0) {
			if (remove(item)) {
				removedanyp = true;
			}
		}
		return removedanyp;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean removedanyp = false;
		Iterator itr = iterator();
		while (itr.hasNext()) {
			T next = (T) itr.next();
			if (!arg0.contains(next)) {
				itr.remove();
				removedanyp = true;
			}
		}
		return removedanyp;
	}

	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] newa = new Object[size];
		for (int i = 0; i < size; i++) {
			newa[i] = data[i];
		}
		return newa;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		Object[] data2 = new Object[size];
		for (int k = 0; k < size; k++) {
			data2[k] = data[k];
		}
		for (int i = 0; i < size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare((T) data2[j], (T) data2[minIndex]) < 0)
					minIndex = j;

			Object temp = data2[i];

			data2[i] = data2[minIndex];
			data2[minIndex] = temp;
		}
		ArrayList<T> data3 = new ArrayList<T>();
		for(int e = 0; e < size; e++) {
			data3.add((T) data2[e]);
		}
		return data3;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		int nextIndex;
		boolean removeAblep;

		public ArrayCollectionIterator() {
			nextIndex = 0;
			
			removeAblep = false;
		}

		
		public boolean hasNext() {
			if (nextIndex < size) {
				return true;
			} else {
				return false;
			}
			
		}

		
		public T next() {
			
			if (hasNext() == false) {
				throw (new NoSuchElementException());
			} else {
				removeAblep = true;
				nextIndex++;
				return data[nextIndex - 1];
			}
		}

		
		public void remove() {
			if (!removeAblep) {
				throw (new IllegalStateException());
			}
			removeAblep = false;
			for (int i = nextIndex - 1; i < size; i++) {
				data[i] = data[i + 1];
			}
			data[size - 1] = null;

			nextIndex--;
			size--;
		}

	}

}
