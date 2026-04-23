//comments: 36
//comment length: 4623
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;


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
		
		T[] tempData = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			tempData[i] = data[i];
		}
		
		
		data = tempData;
	}

	
	public boolean add(T arg0) {
		if (contains(arg0)) {
			return false;
		}
		if (size >= data.length - 1) {
			grow();
		}
	
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		Boolean anyAdded = false;
		for (T temp : arg0) {
			if (add(temp)) {
				anyAdded = true;
			}
		}
		return anyAdded;
	}

	
	public void clear() {
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
		for (Object temp : arg0) {
			if (!contains(temp)) {
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
				shiftDown(i);
				return true;
			}
		}
		return false;
	}

	
	public void shiftDown(int initialShiftPosition) {
		for (int i = initialShiftPosition; i < size; i++) {
			data[i] = data[i + 1];
		}
		size--;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		Boolean hasAnItem = false;
		for (Object temp : arg0) {
			if (remove(temp)) {
				hasAnItem = true;
			}
		}
		return hasAnItem;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		Boolean itemDeleted = false;
		T next;
		Iterator<T> myIterator = iterator();
		while (myIterator.hasNext()) {
			next = myIterator.next();
			if (!arg0.contains(next)) {
				myIterator.remove();
				itemDeleted = true;
			}
		}
		return itemDeleted;
	}

	
	public int size() {
		return size;
	}

	
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		T[] toArray = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			toArray[i] = data[i];
		}
		return toArray;
	}

	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			list.add(data[i]);
		}
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}

		return list;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
		int currentIndex = -1; 
								
		T lastSeen = null; 

		
		public ArrayCollectionIterator() {
		}

		
		public boolean hasNext() {
			return currentIndex + 1 < size;
		}

		
		public T next() {
			if (!hasNext()) {
				throw new IllegalArgumentException();
			}
			currentIndex++;
			lastSeen = data[currentIndex];
			return data[currentIndex];
		}

		
		public void remove() {
			if (lastSeen == null) {
				throw new IllegalArgumentException();
			}
			for (int i = 0; i < size; i++) {
				if (data[i].equals(lastSeen)) {
					shiftDown(i);
					currentIndex--;
				}
			}
			lastSeen = null;
		}

		
		public void shiftDown(int startingIndex) {
			for (int i = startingIndex; i < size; i++) {
				data[i] = data[i + 1];
			}
			size--;
		}

	}

}
