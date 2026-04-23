//comments: 27
//comment length: 2702
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	private T data[]; 
	public int size; 


	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		T newData[] = (T[]) new Object[data.length * 2];
		for(int idx = 0; idx < size; idx++){
			newData[idx] = data[idx];
		}
		data = newData;
		
	}

	
	public boolean add(T arg0) {
		
		
		if(contains(arg0)) {
			return false;
		}
		
		
		  
			if(size == data.length) {
				grow();
			}
			data[size++] = arg0;
			return true;
		}
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean doesAdd = false;
		for(T item : arg0) {
			if(this.add(item)) {
				doesAdd = true;
			}
		}
		return doesAdd;
	}
	
	
	public void clear() {
		for(int idx = 0; idx < data.length; idx++) {
			data[idx] = null;
		}
		size = 0;
	}
	
	
	public boolean contains(Object arg0) {
		for(int idx = 0; idx < data.length; idx++) {
			if(data[idx] == arg0)
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		boolean doesContain = false;
		for(Object item : arg0) {
			if(this.contains(item)) {
				doesContain = true;
			}
		}
		return doesContain;
	}

	
	public boolean isEmpty() {
		for(int idx = 0; idx < data.length; idx++) {
			if(size == 0) {
				return true;
			}
		}
		return false;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		for(int idx = 0; idx < data.length; idx++) {
			if(data[idx] == arg0) {
				if(idx == data.length-1)
					data[idx] = null;
				for(int jdx = idx; jdx < data.length-1; jdx++) {
					data[jdx] = data[jdx+1];
					data[jdx+1] = null;
				}
				size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean doesRemove = false;
		for(Object item : arg0) {
			if(remove(item)) {
				doesRemove = true;
			}
		}
		return doesRemove;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean doesRetain = false;
		Iterator<T> iterator = this.iterator();
		while(iterator.hasNext()) {
			T element = iterator.next();
			if(!arg0.contains(element)) {
				iterator.remove();
				this.remove(element);
				doesRetain = true;
				iterator.next();
			}
		}
		
		return doesRetain;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] newArray = new Object[size()];
		int idx = 0;
		for(T item : this) {
			newArray[idx] = item;
			idx++;
		}
		return newArray;
	}

	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		@SuppressWarnings("unchecked")
		T[] newArray = (T[]) new Object[size()];
		int idx = 0;
		for(T item : arg0) {
			newArray[idx] = item;
			idx++;
		}
		return newArray;
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		T[] sortedList = (T[])new Object[size()];
		int idx = 0;
		for(T item : this) {
			sortedList[idx] = item;
			idx++;	
	}
		for(int jdx = 1; jdx < size; jdx++) {
			T temp = sortedList[jdx];
			int kdx;
			for(kdx = jdx - 1; kdx >= 0 && cmp.compare(sortedList[kdx], temp) > 0; kdx--) {
				sortedList[kdx+1] = sortedList[kdx];
			}
			sortedList[kdx+1] = temp;
		}
		ArrayList<T> sorted = new ArrayList<T>();
		sorted.add((T) sortedList);
		return sorted;
	}

	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int currentPosition;
		private boolean hasRemoved;
		
		public ArrayCollectionIterator()
		{
			currentPosition = size()-1;
			hasRemoved = false;
		}

		
		public boolean hasNext() {
			return (currentPosition >= 0);
		}

		
		public T next() {		
			if(currentPosition < 0) {
				throw new NoSuchElementException();
			}
			currentPosition--;
			hasRemoved = false;
			return data[currentPosition + 1];
		}

		
		public void remove() {
			if(hasRemoved != false) {
				throw new IllegalStateException();
			} else {
				
			}
			currentPosition++;
			hasRemoved = true;
		}

	}

}
