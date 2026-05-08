//comments: 36
//comment length: 5208
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
		T temp[] = (T[]) new Object[size * 2]; 
		for (int index = 0; index < size; index++) { 
			temp[index] = data[index];
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
		boolean contains = false;
		for (T item : arg0) {
			if (this.add(item))
				contains = true;
		}
		return contains;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		data = (T[]) new Object[10];
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for (Object item : arg0) {
			if (!this.contains(item))
				return false;
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
		Iterator<T> IT = this.iterator();
		while (IT.hasNext()) {
			if (IT.next().equals(arg0)) {
				IT.remove();
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean removed = false;
		for (Object item : arg0) {
			if (remove(item))
				removed = true;
		}
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean changed = false;
		for (T item : this) {
			if (!arg0.contains(item)) {
				this.remove(item);
				changed = true;
			}

		}
		return changed;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {

		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size]; 
		for (int index = 0; index < size; index++) {
			array[index] = data[index]; 
		}

		return array;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> SortedList = new ArrayList<T>();

		for (T item : this) {
			SortedList.add(item); 
		}

		for (int i = 0; i < SortedList.size() - 1; i++) {  
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < SortedList.size(); j++)
				if (cmp.compare(SortedList.get(j), SortedList.get(minIndex)) < 0) 
					minIndex = j; 
			T temp = SortedList.get(i);
			SortedList.set(i, SortedList.get(minIndex)); 
			SortedList.set(minIndex, temp);
		}

		return SortedList;

	}

	private class ArrayCollectionIterator implements Iterator<T> {
		int index; 

		public ArrayCollectionIterator() {
			index = 0;
		}

		public boolean hasNext() {

			return index < size;
		}

		public T next() {
			return data[index++];
		}

		public void remove() {
			for (int i = index; i < size; i++) {
				data[index - 1] = data[index];
			}
			size--;
		}

	}

}
