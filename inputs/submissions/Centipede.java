//comments: 29
//comment length: 3149
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
		T[] temp = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++)
			temp[i] = data[i];
		data = temp;

	}

	
	public boolean add(T arg0) {
		if (data.length == size)
			grow();
		if (!(contains(arg0))) {
			data[size] = arg0;
			size++;
			return true;
		}
		return false;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean added = false;
		for (T element : arg0) {
			if (!contains(element)) {
				add(element);
				added = true;
			}
		}

		return added;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		data = (T[]) new Object[10];
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for (T t : data)
			if (t == arg0)
				return true;
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		boolean contains = true;
		for (Object element : arg0) {
			if (!contains(element)) {
				contains = false;
			}
		}
		return contains;
	}

	
	public boolean isEmpty() {
		boolean empty = size == 0;
		return empty;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		if (!contains(arg0)) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				while (i != size) {
					data[i] = data[++i];
				}
			}
		}
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean removed = false;
		for (Object element : arg0) {
			if (contains(element)) {
				remove(element);
				removed = true;
			}
		}
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean retained = false;
		for (int i = 0; i < size; i++) {
			if (!arg0.contains(data[i])) { 
				remove(data[i]);
				retained = true;
				if (retained) {
					i = 0;
				}
			}
		}

		return retained;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		T[] newArray = (T[]) new Object[size];
		for (int i = 0; i < size; i++)
			newArray[i] = data[i];
		return newArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		for (int i = 0; i < size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare(data[j], data[minIndex]) < 0)
					minIndex = j;
			T temp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = temp;
			list.add(data[minIndex]);
		}
		return list;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		
		private int index;
		private boolean canRemove;

		public ArrayCollectionIterator() {
			index = 0;
			canRemove = false;
		}

		
		public boolean hasNext() {
			return index < size;
		}

		
		public T next() {
			if (index == size - 1)
				throw new NoSuchElementException(); 
			canRemove = true;
			return data[index++];
		}

		
		public void remove() {
			if (canRemove) {
				for (int i = index - 1; i < data.length; i++) {
					data[i] = data[i + 1];
				}
				size--;
				canRemove = false;
			}
		}

	}

}
