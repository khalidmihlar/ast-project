//comments: 32
//comment length: 3736
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	T data[]; 
	int size; 
	ArrayCollectionIterator iter; 


	
	@SuppressWarnings("unchecked")
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
		iter = new ArrayCollectionIterator(); 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		T temp[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		data = temp;

	}

	
	public boolean add(T item) {
		if (data.length == size)
			grow();
		if (data[size] == null && data[size] != item && item != null) {
			data[size] = item;
			size++;
			return true;
		}
		return false;
	}

	
	public boolean addAll(Collection<? extends T> list) {
		boolean changed = false;
		for (T item : list) {
			if (!contains(item)) {
				add(item);
				changed = true;
			}
		}

		return changed;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		data = (T[]) new Object[size];
		size = 0;
	}

	
	public boolean contains(Object item) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] == item)
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> list) {
		boolean changed = false;
		for (Object item : list) {
			if (contains(item))
				changed = true;
			else
				changed = false;
		}
		return changed;
	}

	
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	
	public Iterator<T> iterator() {
		return iter;
	}

	
	public boolean remove(Object item) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] == item) {
				data[i] = null;
				for (int j = i; j < data.length; j++) {
					if (data[j] == data[size - 1])
						break;
					if (data[j + 1] != null && j + 1 < data.length)
						data[j] = data[j + 1];
				}
				data[size - 1] = null;
				size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> list) {
		int oldSize = size;
		for (Object item : list) {
			if (contains(item)) {
				remove(item);
				continue;
			}
		}
		if (oldSize > size)
			return true;
		return false;
	}

	
	public boolean retainAll(Collection<?> list) {
		iter = new ArrayCollectionIterator();
		while (iter.hasNext()) {
			if (!list.contains(iter.next())) {
				iter.remove();
				return true;
			}
		}
		return false;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] data = Arrays.copyOf(this.data, size()); 
															
															
		return data;
	}

	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null)
				list.add(data[i]);
		}
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++) {
				if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			}
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
		return list;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private boolean isNext = false;
		private int index = 0;

		
		public ArrayCollectionIterator() {
			isNext = false;
			index = 0;
		}

		
		@Override
		public boolean hasNext() {
			return index < size;
		}

		
		@Override
		public T next() {
			if (hasNext()) {
				isNext = true;
				return data[index++];
			}
			throw new NoSuchElementException();
		}

		
		@Override
		public void remove() {
			if (!isNext)
				throw new IllegalStateException();
			ArrayCollection.this.remove(data[--index]);
			isNext = false;
		}

	}

}
