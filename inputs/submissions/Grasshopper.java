//comments: 34
//comment length: 6460
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
 

public class ArrayCollection<T> implements Collection<T> {

	private T data[]; 
	private int size; 


	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		T temp[] = (T[]) new Object[data.length*2];
		for(int i = 0; i < size;  i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	
	public boolean add(T arg0) {
		
		if (size == data.length) grow();
		
		if (contains(arg0)) return false;
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		int sizeBefore = size;
		for (T item : arg0) {
			add(item);
		}
		return size > sizeBefore ? true : false;
	}

	
	public void clear() {
		size = 0;
		for (int i = 0; i < data.length; i++) data[i] = null;
	}

	
	public boolean contains(Object arg0) {
		for (int i=0; i<size; i++) {
			if (data[i].equals(arg0)) return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for (Object item : arg0) {
			boolean temp = contains(item);
			if (!temp) return false;
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
		int index = 0;
		for (index=0; index<size; index++) {
			if (data[index].equals(arg0)) {
				break;
			}
			if (index == size-1) return false;
		}
		
		for (int i = index; i < size-1; i++) {
			data[i] = data [i+1];
		}
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		int temp = size;
		for (Object object: arg0) {
			remove(object);
		}
		return size != temp;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		int temp = size;
		ArrayCollectionIterator iter = new ArrayCollectionIterator();
		while (iter.hasNext()) {
			if (!arg0.contains(iter.next())) {
				iter.remove();
			}
		}
		return size != temp;
	}

	
	public int size() {
		return size;
	}
	
	
	public int length() {
		return data.length;
	}

	
	public Object[] toArray() {
		Object[] array = new Object[size];
		for (int i = 0; i < size; i++) {
			array[i] = data[i];
		}
		return array;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		T[] copy = (T[]) toArray();
		sort(copy, cmp);
		
		
		ArrayList<T> arrayList = new ArrayList<T>(Arrays.asList(copy));
		return arrayList;
	}

	
	private <ListType> void sort(T[] data, Comparator<? super T> c) {
	for (int i = 0; i < size - 1; i++) {
		int j, minIndex;
		for (j = i + 1, minIndex = i; j < size; j++)
			if (c.compare(data[j], data[minIndex]) < 0)
				minIndex = j;
		T temp = data[i];
		data[i] = data[minIndex];
		data[minIndex] = temp;
		}
	}


	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int index;
		private boolean nextCalled;
		
		public ArrayCollectionIterator()
		{
			this.index = 0;
			this.nextCalled = false;
		}
		
		
		public boolean hasNext() {
			if (index < size) { 
				return true;
			}
			return false;
		}
		
		
		public T next() {
			if (this.hasNext()) {
				nextCalled = true;
				return data[index++]; 
			}
			else {
				throw new NoSuchElementException("No more items through which to iterate");
			}
		}
		
		
		public void remove() {
			if (nextCalled) {
				ArrayCollection.this.remove(data[index-1]); 
				nextCalled = false;
				--index;
			}
			else {
				throw new IllegalStateException("next() has not been called");
			}
		}
	}
}
