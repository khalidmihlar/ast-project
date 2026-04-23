//comments: 25
//comment length: 3552
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
		if (size == data.length) {
			T[] newData = (T[]) new Object[data.length * 2 + 1];
			for (int i = 0; i < data.length; i++) {
				newData[i] = data[i];

			}
			data = newData;
		}

	}

	
	public boolean add(T arg0) {
		if (contains(arg0)) {
			return false;
		}
		if (size + 1 > data.length) {
			grow();
		}
		data[size] = arg0;
		size++;

		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean result = false;
		Object[] things = arg0.toArray();
		for (int i = 0; i < arg0.size(); i++) {
			if (!contains(things[i])) {
				add((T) things[i]);
				result = true;
			}
		}

		return result;
	}

	
	public void clear() {
		int counter = size;
		for (int i = 0; i < counter; i++) {
			data[i] = null;
			size--;
		}

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
		for (int i = 0; i < arg0.size(); i++) {
			if (!arg0.contains(data[i])) {
				result = false;
			}

		}
		return result;
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
		Iterator<T> findsThings = new ArrayCollectionIterator();
		return findsThings;
	}

	
	public boolean remove(Object arg0) {
		boolean result = false;
		int location = 0;
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				data[i] = null;
				result = true;
				location = i;
			}
		}
		T current = null;
		for (int j = location + 1; j < size; j++) {
			current = data[j];
			data[j] = null;
			data[j - 1] = current;
		}
		size--;
		return result;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean result = false;
		for (int i = 0; i < size; i++) {
			if (arg0.contains(data[i])) {
				remove(data[i]);
				result = true;
			}
		}
		return result;
	}

	
	public boolean retainAll(Collection<?> arg0) {

		boolean result = false;
		Iterator<T> itr = this.iterator();
		while (itr.hasNext()) {
			T current = (T) itr.next();

			if (!arg0.contains(current)) {
				itr.remove();
				result = true;
			}
		}

		return result;
	}

	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		T[] copy = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			copy[i] = data[i];
		}
		return copy;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			list.add(data[i]);
		}

		for (int i = 0; i < size; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);

		}
		return list;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		int location;
		boolean removable;

		public ArrayCollectionIterator() {
			location = 0;
			removable = false;
		}

		
		public boolean hasNext() {

			return location < size;
		}

		
		public T next() {

			if (size < location) {
				throw new NoSuchElementException();
			}

			removable = true;
			return data[location++];

		}

		
		public void remove() {
			if (removable != false) {
				location--;
				ArrayCollection.this.remove(data[location]);
				removable = false;
			} else {
				throw new IllegalStateException();
			}
		}

	}

}
