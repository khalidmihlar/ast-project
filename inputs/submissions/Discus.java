//comments: 38
//comment length: 4347
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
		T AdditionalData[];
		AdditionalData = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			AdditionalData[i] = data[i];
		}
		this.data = AdditionalData;

	}

	
	public boolean add(T arg0) {
		if (size == data.length) {
			grow();
		}

		if (!contains(arg0)) {

			data[size] = arg0;
			
			size++;
			return true;
		}

		return false;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		for (T addAllVariable : arg0) {
			add(addAllVariable);
			return true;
		}
		return false;
	}

	
	public void clear() {
		for (int i = 0; i < data.length; i++) {
			data[i] = null;
		}
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
		for (Object containsAllVariable : arg0) {
			if (!(contains(containsAllVariable)))
				return false;
		}
		return true;
	}

	
	public boolean isEmpty() {
		if (size == 0)
			return size == 0;
		return false;
	}

	

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				for (int j = i; j < size - 1; j++) {
					
					data[j] = data[j + 1];
				}
				size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		for (Object removeThis : arg0) {
			remove(removeThis);
			return true;
		}
		return false;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		int total = -1;
		Iterator<?> a = new ArrayCollectionIterator();
		while (a.hasNext()) {
			if (!arg0.contains(a.next())) {
				a.remove();
			}
			if (total != 0) {
				return true;
			}
		}
		return false;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] objectArray = new Object[size];
		for (int i = 0; i < size; i++) {
			objectArray[i] = data[i];
		}
		return objectArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			list.add(this.data[i]);
		}
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				
				if (cmp.compare(list.get(i), list.get(j)) > 0) {
					
					T temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
		private int index = 0;
		private boolean lastRemoved = false;

		public ArrayCollectionIterator() {

		}

		
		public boolean hasNext() {
			
			
			return index < ArrayCollection.this.size();
		}

		
		public T next() {
			if (index >= ArrayCollection.this.size())
				throw new NoSuchElementException();

			
			T object = ArrayCollection.this.data[index];
			
			index++;
			lastRemoved = false;
			return object;
		}

		
		public void remove() {
			
			if (index == 0)
				throw new IllegalStateException();
			if (lastRemoved)
				throw new IllegalStateException();

			
			ArrayCollection.this.remove(ArrayCollection.this.data[index - 1]);
			
			index--;
			lastRemoved = true;
		}

	}

}
