//comments: 27
//comment length: 3469
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
		
		
		
		T newData[] = (T[]) new Object[data.length * 2];

		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
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
		boolean anyAdded = false;
		for (T e : arg0) {
			if (add(e)) {
				anyAdded = true;
			}
		}
		return anyAdded;
	}

	
	public void clear() {
		data = (T[]) new Object[10];
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
		for (Object e : arg0) {
			if (!contains(e)) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		for (T e : data) {
			if (e != null) {
				return false;
			}
		}
		return true;
	}

	
	public Iterator<T> iterator() {
		
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (arg0.equals(data[i])) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			return false;
		}

		for (int i = 0; i < size - 1; i++) {

			data[i] = data[i + 1];
		}
		data[size] = null;
		size--;

		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean anyRemoved = false;

		for (Object e : arg0) {
			if (remove(e)) {
				anyRemoved = true;
			}
		}

		return anyRemoved;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		T[] retain = (T[]) new Object[data.length];
		int i = 0;
		int sizeBefore = size();

		for (Object e : arg0) {
			if (contains(e)) {
				retain[i] = (T) e;
				i++;
			}
		}
		data = retain;

		if (i == sizeBefore) {
			return false;
		} else {
			size = i;
			return true;
		}

	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		T[] toReturn = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			toReturn[i] = data[i];
		}
		return toReturn;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> toReturn = new ArrayList<T>(this);
		for(int i = 0; i < toReturn.size()-1; i++) {
			int j, minIndex;
			for(j = i+1, minIndex = i; j < toReturn.size(); j++) {
				if(cmp.compare(toReturn.get(j), toReturn.get(minIndex)) < 0) {
					minIndex = j;
				}
			}
			T temp = toReturn.get(i);
			toReturn.set(i, toReturn.get(minIndex));
			toReturn.set(minIndex, temp);
		}
		return toReturn;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
		private int currentIndex = 0;
		private boolean canRemove = false;
		
		public ArrayCollectionIterator() {
		}

		public boolean hasNext() {
			if (currentIndex < size - 1) {
				return true;
			}
			return false;
		}

		public T next() {
			if (hasNext()) {
				currentIndex++;
				canRemove = true;
				return data[currentIndex];
			} else
				throw new NoSuchElementException();
		}

		public void remove() {
			if (canRemove) {
				for (int i = currentIndex; i < size - 1; i++) {
					data[i] = data[i + 1];
				}
				data[size] = null;
				currentIndex--;
				canRemove = false;
				size--;
			} else
				throw new IllegalStateException();
		}

	}

}
