//comments: 68
//comment length: 5827
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	T data[]; 
	int size; 
	int remIndex; 


	
	
	@SuppressWarnings("unchecked")
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		T arr[];
		arr = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			arr[i] = data[i];
		}
		data = arr;
	}
	
	public boolean add(T element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (this.contains(element)) {
			return false;
		}
		if (size == 0) {
			data[0] = element;
			size++;
			return true;
		}
		if (data.length == size) {
			this.grow();
			data[size] = element;
			size++;
			return true;
		}
		data[size] = element;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> coll) {
		boolean hasChanged = false;
		for (T element: coll) {
			if (this.add(element)) {
			hasChanged = true;
			}
		}
		return hasChanged;
	}

	
	public void clear() {
		data = (T[]) new Object[data.length];
		size = 0;
	}
	
	public boolean contains(Object element) {
		
		if (size < 1) {
			return false;
		}
		
		if (element == null) {
			throw new NullPointerException();
		}
		T elem;
		try {
			elem = (T) element;	
		} catch (ClassCastException e) {
			return false;
		}
		for (int i = 0; i < size; i++) {	
			if (data[i].equals(elem)) {
				remIndex = i;	
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> coll) {
		for (Object element: coll) {	
			if (!(this.contains(element))) { 
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		
		if (size < 1) {
			return true;
		}
		return false;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}
	
	public boolean remove(Object element) {
		if (this.contains(element)) { 
			data[remIndex] = null;	
			for (int i = remIndex; i < size; i++) { 
				data[i] = data[i+1];
			}
			data[size - 1] = null; 
			size--; 
			return true;
		}
		return false;
	}
	
	public boolean removeAll(Collection<?> list) {
		boolean hasChanged = false;	
		for (Object element: list) {	
			if (this.remove(element)) {	
				hasChanged = true;
			}
		}
		return hasChanged;
	}
	
	public boolean retainAll(Collection<?> list) {
		ArrayCollectionIterator iterator = new ArrayCollectionIterator(); 
		boolean hasChanged = false; 
		while(iterator.hasNext()) { 
			T element = iterator.next();
			if (list.contains(element)) {
				continue;
			}
			if (!(list.contains(element))) { 
				iterator.remove();
				hasChanged = true;
			}
		}
		return hasChanged;
	}

	
	public int size() {
		return size;
	}







	
	public Object[] toArray() {
		Object[] arr = new Object[data.length]; 
		for (int i = 0; i < data.length; i++) {
			arr[i] = data[i]; 
		}
		return arr;
	}

	
	public <T> T[] toArray(T[] arr) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> arr = new ArrayList<T>();
		for (int i = 0; i < size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size(); j++)
				if (cmp.compare(data[j], data[minIndex]) < 0)
					minIndex = j;
			T temp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = temp;
		}
		for (int k = 0; k < size; k++) {
			arr.add(data[k]);
		}
		return arr;
	}
	
	private class ArrayCollectionIterator implements Iterator<T> {
		
		public ArrayCollectionIterator() {
			
		}
		boolean canRemove = false; 
		private int nextIndx = 0; 
		
		public boolean hasNext() {
			return nextIndx < size;
		}
		
		public T next() {
			
			if (nextIndx == data.length) { 
				throw new IndexOutOfBoundsException();
			}
			if (this.hasNext()) { 
			canRemove = true;
			return data[nextIndx++]; 
			}
			return null;
		}
		
		public void remove() {
			if (!canRemove) { 
				throw new IllegalStateException();
			}		
			ArrayCollection.this.remove(data[nextIndx-1]); 
			nextIndx--; 
			canRemove = false;
		}

	}

}
