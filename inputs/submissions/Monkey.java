//comments: 39
//comment length: 3839
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
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		
		T tempArray[] = (T[]) new Object[data.length * 2];
		
		
		for(int i = 0; i < data.length; i++) {
			tempArray[i] = data[i];
		}
		
		
		data = tempArray;
	}


	
	public boolean add(T arg0) {
		
		
		if(contains(arg0)) {
			return false;
		}
		
		
		if(size >= data.length) {
			grow();
		}
		
		
		data[size] = arg0;
		
		
		size++;
		
		return true;
		
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean state = false;
		
		
		for(T item : arg0) {
			if(add(item)) {
				state = true;
			}
		}
		
		
		return state;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		 data = (T[]) new Object[10];
		 
		 size = 0;
		
		
	}

	
	public boolean contains(Object arg0) {
		
		
		for(int i = 0; i < data.length; i++) {
			if(arg0.equals(data[i])) {
				return true;
			}
		}
			
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		boolean state = true;
		
		
		for(Object item : arg0) {
			if(!contains(item)) {
				state = false;
			}
		}
		
		return state;
	}

	
	public boolean isEmpty() {
		
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		
		
		for(int i = 0; i < size; i++) {
			if(data[i] != null && data[i].equals(arg0)) {
				for(int k = i; k <= size-1; k++) {
					data[k] = data[k+1];
				}
				return true;
			}
				
		}
		
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean state = false;
		
		for(Object item : arg0) {
			if(remove(item)) {
				state = true;
			}
		}
		
		return state;
	}

	
	@SuppressWarnings("unchecked")
	public boolean retainAll(Collection<?> arg0) {
		
		
		boolean state = false;
		
		Iterator<T> itr = iterator();
		
		while(itr.hasNext()) {
			
			if(!arg0.contains(itr.next())) {
				itr.remove();
				state = true;
			}
			
		}
		return state;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		T tempArray[] = (T[]) new Object[size];
		
		for(int i = 0; i < size; i++) {
			tempArray[i] = data[i];
		}
		
		return tempArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}


	 
	  private static <T> void sort(ArrayList<T> sortedList,
	      Comparator<? super T> cmp) {
	    for (int i = 0; i < sortedList.size() - 1; i++) {
	      int j, minIndex;
	      for (j = i + 1, minIndex = i; j < sortedList.size(); j++)
	        if (cmp.compare(sortedList.get(j), sortedList.get(minIndex)) < 0)
	          minIndex = j;
	      T temp = sortedList.get(i);
	      sortedList.set(i, sortedList.get(minIndex));
	      sortedList.set(minIndex, temp);
	    }
	  }

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sortedList = new ArrayList<T>();
		
		for(int i = 0; i < size; i++) {
			sortedList.add(data[i]);
		}
		sort(sortedList,cmp);
		
		return sortedList;
	}



	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int indexAt;
		private boolean isOkay;
		T currentData;
		
		public ArrayCollectionIterator()
		{
			indexAt = 0;
			isOkay = false;
		}

		public boolean hasNext() {
			if(data[indexAt] != null) {
				return true;
			}
			
			return false;
		}

		public T next() {
			int indexAt2;
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			isOkay = true;
			indexAt2 = indexAt;
			indexAt++;
			currentData = data[indexAt2];
			return data[indexAt2];
		}


		public void remove() {
			
			if(!isOkay) {
				throw new IllegalStateException();
			}
			
			ArrayCollection.this.remove(currentData);
			isOkay = false;
			indexAt--;
			
		}

	}

}
