//comments: 37
//comment length: 7079
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
		T[] dataTemp = (T[]) new Object[2 * data.length];
		
		for(int i = 0; i < data.length; i++) {
			dataTemp[i] = data[i];
		} 
		data = dataTemp; 
	}

	
	public boolean add(T arg0) {
		
		if(this.contains(arg0))
			return false;
		
		
		if(size == data.length)
			grow();
		
		data[size++] = arg0;
		
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		int priorSize = size;
		for(T el : arg0) {
			add(el);
		}
		if(size != priorSize)
			return true;
			
		return false;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		size = 0;
		data = (T[]) new Object[10]; 
	}

	
	public boolean contains(Object arg0) {
		for(Object el : data) {
			if(el != null) { 
				if (el.equals(arg0)) 
					return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		
		for(Object el : arg0) {
			if(! contains(el))
				return false;
		}
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
		
		int idx = -1;
		
		for(int i = 0; i < size ; i++) {
			if(data[i].equals(arg0)) {
				idx = i;
			}
		}
		
		if(idx != -1) {
			for(int i = idx+1; i < size ; i++)
				data[i-1] = data[i]; 
			
			data[size-1] = null;
			size--;
			return true;
		}

		return false;
	}

	
	
	public boolean removeAll(Collection<?> arg0) {
		int temp = size;
		for(Object el : arg0)
			remove(el);
		if(temp != size)
			return true;
		
		return false; 
	}

	
	
	public boolean retainAll(Collection<?> arg0) {
		Iterator myIterator = new ArrayCollectionIterator();
		int temp = size;
		while(myIterator.hasNext())
		{
			if(!arg0.contains(myIterator.next()))
				myIterator.remove();
		}

		if(size != temp)
			return true;
		
		return false;
		
	}
	
	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] outData = new Object[size]; 
		
		for(int i = 0; i < size; i++) {
				outData[i] = data[i];
		}		
		return outData;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
	 ArrayList<T> returnList = new ArrayList<>();
	 
	 
		 for(int i = 0; i < size; i++) {
			 returnList.add(data[i]);
		 }
		 
	 for(int i = 0; i < returnList.size() - 1; i++) {		 

		 int j, minIndex;
		 for (j = i+1, minIndex = i; j < returnList.size(); j++) {
			 if (cmp.compare(returnList.get(j), returnList.get(minIndex)) < 0) {
				 minIndex = j;
			 }
		 }

		 T temp = returnList.get(i);
		 returnList.set(i,  returnList.get(minIndex));
		 returnList.set(minIndex, temp);

	 }

	return returnList;
	}
	
	


	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int nextIdx;	 
		private boolean legality;	
		
		public ArrayCollectionIterator() 
		{
			this.nextIdx = 0;
			this.legality = false;
		}
		
		
		public boolean hasNext() {
			return nextIdx < size;
		}
		
		
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			
			legality = true;
			return data[nextIdx++];
		}

		
		public void remove() {
			if(!legality) {
				throw new IllegalStateException();
			}
			
			int removedIdx = nextIdx-1; 
			
			ArrayCollection.this.remove(data[removedIdx]);
			legality = false;
			
		}

	}

}
