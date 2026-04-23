//comments: 29
//comment length: 4169
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
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		
		T[] temp = (T[]) new Object[data.length];
		for(int index = 0; index < data.length; index++) {
			temp[index] = data[index];
		}
		
		
		data = (T[]) new Object[temp.length * 2];
		for(int index = 0; index < temp.length; index++) {
			data[index] = temp[index];
		}
	}

	
	public boolean add(T item) {
		
		for(int index = 0; index < this.size; index++) {
			if (this.contains(item)) {
				return false;
			}
		}
		if (this.size == data.length) {
			grow();
		}
		data[size] = item;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> items) {
		boolean isAdded = false;
		
		for(T item : items) {
			if(this.add(item)) {
				isAdded = true;
			}
		}
		return isAdded;
	}

	
	public void clear() {
		for(int index = 0; index < this.size; index++) {
			data[index] = null;
		}
		size = 0;
	}

	
	public boolean contains(Object item) {
		
		if(this.isEmpty()) {
			return false;
		}
		
		for (int index = 0; index < data.length; index++) {
			if (item.equals(data[index])) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> items) {
		
		for(Object item: items) {
			if(!this.contains(item)) {
				return false;
			}
		}
		
		return true;
	}

	
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	
	public Iterator<T> iterator() {
		ArrayCollectionIterator iterator = new ArrayCollectionIterator();
		return iterator;
	}

	
	public boolean remove(Object item) {
		if (this.isEmpty()) {
			return false;
		}
		
		if (this.contains(item)) {
			for(int index = 0; index < this.size; index++) {
				if(item.equals(data[index])) {
					for(int moveTo = index; moveTo < this.size - 1; moveTo++) {
						data[moveTo] = data[moveTo + 1];
					}
					size--;
					return true;
				}
			}
		}
		
		return false;
	}

	
	public boolean removeAll(Collection<?> items) {
		boolean isRemoved = false;
		
		for(Object item: items) {
			if(this.remove(item)) {
				isRemoved = true;
			}
		}
		return isRemoved;
	}

	
	public boolean retainAll(Collection<?> otherCollec) {
		ArrayCollectionIterator iterator = new ArrayCollectionIterator();
		boolean anyItemRemoved = false;
		
		while(iterator.hasNext()) {
			
			if(!otherCollec.contains(iterator.next())) {
				iterator.remove();
				anyItemRemoved = true;
			}
		}
		
		return anyItemRemoved;
	}

	
	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		
		Object[] dataCopy = new Object[this.size];
		
		for (int index = 0; index < this.size; index++) {
			dataCopy[index] = data[index];
		}
		
		return dataCopy;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sortedList = new ArrayList<T>();
		for(int index = 0; index < this.size; index++) {
			sortedList.add(this.data[index]);
		}
		
		for (int i = 0; i < sortedList.size() - 1; i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < sortedList.size(); j++)
			  if (cmp.compare(sortedList.get(j), sortedList.get(minIndex)) < 0)
			  minIndex = j;
			  T temp = sortedList.get(i);
			  sortedList.set(i, sortedList.get(minIndex));
			  sortedList.set(minIndex, temp);
			  }
		
		return sortedList;
	}
	
	



	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int index;
		private boolean nextJustCalled;
		
		public ArrayCollectionIterator()
		{
			index = 0;
			nextJustCalled = false;
		}

		
		public boolean hasNext() {
			
			if (index >= size) {
				return false;
			}
			
			return true;
		}

		
		
		public T next() {
			
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				index++;
				nextJustCalled = true;
				return data[index - 1];
			}
		}

		
		public void remove() {
			
			if(!nextJustCalled) {
				throw new IllegalStateException();
			}
			else {
				index--;
				ArrayCollection.this.remove(data[index]);
				nextJustCalled = false;
			}
		}
	}
}
