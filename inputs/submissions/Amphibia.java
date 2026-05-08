//comments: 28
//comment length: 4234
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
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
		T temp[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		data = temp;
		
	}


	
	public boolean add(T arg0) {
		if(contains(arg0))
			return false;
		if (data.length == size) {
			this.grow();
		}
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean added = false;
		for(T item : arg0)
			if(!contains(item)) {
				add(item);
			}
		
		return added;
	}

	
	public void clear() {
		size = 0;
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for (Object item : arg0)
			if (!contains(item))
				return false;
		return true;
	}

	
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		boolean removed = false;
		if (!this.contains(arg0))
			return removed;
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				for (int j = i; j < size - 1; j++) {
					data[j] = data[j+1];
				}
				size--;
				removed = true;
			}
			
		}
		return removed;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean removed = false;
		for (Object item : arg0) {
			if(this.contains(item)) {
				this.remove(item);
				removed = true;
			}
		}
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<T> itr = this.iterator();
		boolean removed = false;
		while (itr.hasNext()) {
			if(!arg0.contains(itr.next())) {
				itr.remove();
				removed = true;
			}
		}
		return removed;
	}
	
	public int size() {
	
		return size;
	}

	
	public Object[] toArray() {
		T[] arr = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			arr[i] = data[i];
		}
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		
		ArrayList<T> arr = new ArrayList<T>();
		for (int i = 0; i < this.size(); i++) {
			arr.add(data[i]);
		}
		
		for (int i = 0; i < size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare(arr.get(j), arr.get(minIndex)) < 0)
					minIndex = j;
			T temp = arr.get(i);
			arr.set(i, arr.get(minIndex));
			arr.set(minIndex, temp);
		}
		return arr;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int nextIndex = 0;
		private int lastReturned = -1;
		
		public ArrayCollectionIterator()
		{
			
		}
		
		public boolean hasNext() {
		
			return nextIndex < size;
		}
		
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			lastReturned = nextIndex;
			return data[nextIndex++];
		}

		
		public void remove() {
			if(lastReturned < -1) {
				throw new IllegalStateException();
				
			}
			try {
				ArrayCollection.this.remove(data[lastReturned]);
				nextIndex = lastReturned;
				lastReturned = -1;
			} catch (IndexOutOfBoundsException e) {
				System.err.println("IndexOutOfBounds " + e.getMessage());
			}
			
			
		}

	}

}
