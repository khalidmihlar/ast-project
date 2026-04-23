//comments: 46
//comment length: 4500
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
		T[] temp = data; 
		data = (T[]) new Object[(data.length * 2)]; 
		for(int i =0; i < temp.length; i++) 
		{
			data[i] = temp[i];
		}
	}


	
	public boolean add(T arg0) {
		for(int i = 0; i < size + 1; i++) 
		{
			if(arg0.equals(data[i]))
			{
				return false;
			}
		}
		
		if(data.length == size + 1) 
		{
			grow();
		}
		data[size] = arg0; 
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean didAdd = false;
		for(T element:arg0)
		{
			if(add(element)) 
			{
				didAdd = true;
			}
		}
		return didAdd;
	}

	@SuppressWarnings("unchecked")
	
	public void clear() {
		size = 0;
		data = (T[]) new Object[10]; 
		
	}

	
	public boolean contains(Object arg0) {
		for(int i = 0; i < size + 1; i++)
		{
			if(arg0.equals(data[i]))
			{
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		if(arg0.isEmpty() ) 
		{
			return false;
		}
		for(Object element : arg0)
		{
			if(! this.contains(element)) 
			{
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		if(size == 0)
		{
			return true;
		}
		return false;
	}

	
	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		for(int i = 0; i < size + 1; i++)
		{
			if(arg0.equals(data[i])) 
			{
				data[i] = null;
				for(int j = i+1; j < size+1; j++) 
				{
					data[j-1] = data[j];
					data[j] = null;
				}
				size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean didRemove = false;
		for(Object element : arg0)
		{
			if(remove(element))
			{
				didRemove = true;
			}
		}
		return didRemove;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<T> itr = this.iterator();
		boolean hasRemoved = false;
		while(itr.hasNext())
		{
			if(!arg0.contains(itr.next())) 
			{
				itr.remove();
				hasRemoved = true;
			}
		}
		return hasRemoved;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] createdArray = new Object[size];
		for(int i = 0; i < size; i++)
		{
			createdArray[i] = data[i];
		}
		return createdArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> createdArray = new ArrayList<T>(); 
		for(int i = 0; i < size; i++) 
		{
			createdArray.add(i, data[i]);
		}
		
		
	    for (int i = 0; i < createdArray.size() - 1; i++) {
	    int j, minIndex;
	    for (j = i + 1, minIndex = i; j < createdArray.size(); j++)
	    if (cmp.compare(createdArray.get(j), createdArray.get(minIndex)) < 0)
	    minIndex = j;
	    T temp = createdArray.get(i);
	    createdArray.set(i, createdArray.get(minIndex));
	    createdArray.set(minIndex, temp);
	    }
	    
	    return createdArray;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		private boolean hasCalledNext;
		private int currentPosition;
		
		public ArrayCollectionIterator()
		{
			hasCalledNext = false;
			currentPosition = 0;
		}

		
		public boolean hasNext() {
			
			boolean hasCalledNextHolder = hasCalledNext; 
			try {
				next();
			}
			catch(NoSuchElementException e) {
				return false;
			}
			currentPosition--; 
			hasCalledNext = hasCalledNextHolder; 
			return true;
		}

		
		public T next() {
			if(currentPosition == size) { 
				throw(new NoSuchElementException());
			}
			hasCalledNext = true; 
			currentPosition++;
			return data[currentPosition - 1];
			
		}

		
		public void remove() {
			if(!hasCalledNext) {
				throw( new IllegalStateException());
				}
			ArrayCollection.this.remove(data[currentPosition -1]);
			currentPosition--; 
			hasCalledNext = false; 
			
		}

	}

}
