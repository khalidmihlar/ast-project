//comments: 29
//comment length: 2834
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
		
		T grow[] = (T[]) new Object[size * 2]; 
		if(data.length == size)
			{
				for(int i = 0; i < data.length; i++)
				{
					grow[i] = data[i];
					
				}	
				data = grow;
			}
		
	}
	
	public boolean add(T arg0) {
		
		if(!this.contains(arg0))
		{
			if(size == data.length)
			{
				this.grow();
			}
			data[size] = arg0;
			size++;
			return true;
		}
		
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean addAll(Collection<? extends T> arg0) {
		boolean addedItems = false;
		for(Object o : arg0)
		{
			if(this.add((T) o))
			{
				addedItems = true;
			}
		}
		
		
		return addedItems;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		
		data = (T[]) new Object[10]; 
		size = 0;
		
	}
	
	public boolean contains(Object arg0) {
		for(int i = 0; i < size; i++)
		{
			if(data[i].equals(arg0))
			{
				return true;
			}
			
			
		}
		return false;
	}
	
	public boolean containsAll(Collection<?> arg0) {
		for(Object o : arg0)
		{
			if(!this.contains(o))
			{
				return false;
			}
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
		
		int i = 0;
		while(i < size - 1)
		{
			if(data[i].equals(arg0))
			{
				break;
			
			}
			
			i++;
		
		}
		if(!data[i].equals(arg0))
		{
			return false;
		}
		if(i == size - 1)
		{
			data[i] = null;
		}
		for(int j = i; j < size - 1; j++)
		{
			data[j] = data[j + 1];
		}
		size = size - 1;
		
		
		return true;
	}
	
	public boolean removeAll(Collection<?> arg0) {
		boolean removeItems = false;
		for(Object o : arg0)
		{
			if(this.remove(o))
			{
				removeItems = true;
			}
		}
		
		return removeItems;
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean retainAll(Collection<?> arg0) {
		boolean removed = false;
		ArrayCollection<T> temp = new ArrayCollection<T>();
		temp.addAll((Collection<? extends T>) arg0);
		ArrayCollectionIterator iterator = (ArrayCollection<T>.ArrayCollectionIterator) temp.iterator();
		while(iterator.hasNext())
		{
			
			if(!this.contains(iterator.next()))
			{
				iterator.remove();
				removed = true;
			}
			
			
		}
		this.clear();
		this.addAll((Collection<? extends T>) temp);
		return removed;
	}
	
	public int size() {
		
		return size;
	}
	
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		T newArray[] =(T[]) new Object[size];
		for(int i = 0; i < size; i++)
		{
			newArray[i] = data[i];
		}
		
		return newArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sorted = new ArrayList<T>();
		for(int i = 0; i < size; i++)
		{
			sorted.add(data[i]);
		}
		for(int i = 0; i < size - 1; i++)
		{
			int j, minIndex;
			for(j = i + 1, minIndex = i; j < size; j++)
			{
				if(cmp.compare(sorted.get(j), sorted.get(minIndex)) < 0)
				{
					minIndex = j;
				}
				
			}
			T temp = sorted.get(i);
			sorted.set(i, sorted.get(minIndex));
			sorted.set(minIndex, temp);
		}
		return sorted;
	}

	

	private class ArrayCollectionIterator implements Iterator<T>
	{
		public ArrayCollectionIterator()
		{
			
		}
		
		private int index = -1;
		private boolean nextCalled = false;
		public boolean hasNext() {
			
			
			
			return index < size - 1;
		}

		public T next() {
			index++;
			nextCalled = true;
			
			return data[index];
		}

		public void remove() {
			if(!nextCalled)
			{
				throw new IllegalStateException();
			}
			for(int j = index; j < size - 1; j++)
			{
				data[j] = data[j + 1];
			}
			index--;
			size = size - 1;
			
			nextCalled = false;
			
		}
		
		public void returnToStart()
		{
			index = -1;
		}

	}

}
