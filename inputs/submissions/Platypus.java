//comments: 31
//comment length: 5682
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
		int newSize = data.length * 2;
		T[] oldData = data;
		T[] newData = (T[]) new Object[newSize];

		for (int i = 0; i < size; i++) {
			newData[i] = oldData[i];
		}
		data = newData; 
						
	}

	
	public boolean add(T arg0) {

		if (contains(arg0))
			return false;

		if (data.length > size) {
			data[size] = arg0;
			size++;
		} else {
			grow();
			data[size] = arg0;
			size++;
		}
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {

		Iterator<? extends T> scanner = arg0.iterator();
		boolean added = false; 

		for (int i = 0; i < arg0.size(); i++) {
			if (add(scanner.next()))
				added = true;
		}
		return added;
	}

	
	public void clear() {
		for (int i = 0; i < size(); i++) {
			data[i] = null;
		}
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size(); i++) {
			if (data[i].equals(arg0))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		Iterator<?> scanner = arg0.iterator();
		while (scanner.hasNext()) {
			if (!contains(scanner.next()))
				return false;
		}
		return true;
	}

	
	public boolean isEmpty() {

		return size() == 0;
	}

	
	public Iterator<T> iterator() {

		return null;
	}

	
	public boolean remove(Object arg0) {
		for (int i = 0; i < size(); i++) {
			if (data[i].equals(arg0)) {
				for (int p = i; p < size() - 1; p++) {
					data[p] = data[p + 1];
				}
				size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		Iterator<?> scanner = arg0.iterator();
		boolean removed = false; 

		for (int i = 0; i < arg0.size(); i++) {
			if (remove(scanner.next()))
				removed = true;
		}
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<?> scanner = arg0.iterator();
		boolean removed = false; 

		for (int i = 0; i < arg0.size(); i++) {
			Object obj = scanner.next();

			if (!contains(obj)) {
				if (remove(obj))
					removed = true;
			}
		}
		return removed;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] arr = new Object[size];

		for (int i = 0; i < size; i++) {
			arr[i] = data[i];
		}
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		
		for (int i = 0; i < size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size(); j++) {
				if (cmp.compare(data[j], data[minIndex]) < 0)
					minIndex = j;
				
			T temp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = temp;
			list.add(data[i]);
			}
		}
			return list;
		
	}

		private class ArrayCollectionIterator implements Iterator<T> {

			private int index = 0;
			private T currentObj = null;

			public ArrayCollectionIterator() {
			}

			
			public boolean hasNext() {
				return index < size();
			}

			
			public T next() {
				if (hasNext()) {
					T next = data[index];
					index++;
					currentObj = next;
					return currentObj;
				}
				throw new NoSuchElementException();

			}

			
			public void remove() {
				if (currentObj == null) {
					throw new IllegalStateException();
				}

				ArrayCollection.this.remove(currentObj);
				currentObj = null;
			}

		}
	}

