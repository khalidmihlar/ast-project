//comments: 37
//comment length: 4689
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
		T[] temp = (T[])new Object[data.length*2];
		for (int i = 0; i < data.length; i++) { 
			temp[i] = data[i];
		}
		data = temp;
	}



	
	public boolean add(T arg0) {
		if (this.contains(arg0))	
			return false;
		
		data[size] = arg0;
		size++;
		
		if (data.length == size) { 	
			grow();
		}
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		int counter = 0;		
		for (T item : arg0) {
			if (this.add(item))
				counter++;
			}
		if (counter > 0)
			return true;
					
		return false;
	}

	
	public void clear() {
		for(int i = size-1; i >= 0; i--)
			this.remove(data[i]);
	}

	
	public boolean contains(Object arg0) {
		for(int i = 0; i < size; i++) {
			if(data[i].equals(arg0))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for (Object item : arg0)
			if(!(this.contains(item)))
				return false;
		return true;
	}

	
	public boolean isEmpty() {
		if (size > 0)
			return false;
		return true;
	}

	
	public Iterator<T> iterator() {
		return (Iterator<T>) new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		int index = -1;
		
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {		
				index = i;
				break;
			}
		}
		if (index == -1)
		{
			return false;				
		}
		for (int j = index; j < size; j++) {	
			data[j] = data[j+1];
		}
		size--;	
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		int counter = 0;
		for (Object item : arg0) {
			if (this.remove(item))
				counter++;
		}
		if (counter==0)			
			return false;
		return true;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<T> itr = iterator();
		boolean hasRemovedSomething = false;
		while (itr.hasNext()) {						
			T next = itr.next();
			boolean doesNotContain = true;
			
			for (Object item : arg0) {				
				if (next.equals(item))				
					doesNotContain = false;			
			}
			
			if (doesNotContain) {
				itr.remove();						
				hasRemovedSomething = true;
			}
		}
		return hasRemovedSomething;
	}

	
	public int size() {
		
		return size;
	}

	
	public Object[] toArray() {
		Object[] objarr = new Object[size];
		for (int i = 0; i < objarr.length; i++) {
			objarr[i] = data[i];
		}
		return objarr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public void toSortedList(Comparator<? super T> cmp)
	{
		for (int i = 0; i < this.size - 1; i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < this.size; j++)	
				  if (cmp.compare(data[j], data[minIndex]) < 0)
					  minIndex = j;
			  
			  T temp = this.data[i];
			  this.data[i] = this.data[minIndex];
			  this.data[minIndex] = temp;
		  	}
		}
	
	
	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int nextIdx = 0;
		private boolean legalToRemove = false;
		
		
		public ArrayCollectionIterator()
		{
			
		}

		
		public boolean hasNext() {
			if (nextIdx < size) 
				return true;
			return false;
		}
		
		
		public T next() {
			if (hasNext()) {
				nextIdx++;
				legalToRemove = true;
				return data[nextIdx - 1];
			}
			else
				throw new NoSuchElementException();
		}

		
		public void remove() {
			if (legalToRemove) {
				nextIdx--;
				ArrayCollection.this.remove(data[nextIdx]);
				legalToRemove = false;
				
			}
			else {
				throw new IllegalStateException();
			}

		}

	}
	
}






