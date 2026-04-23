//comments: 90
//comment length: 6128
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
		
		T[] tempData = (T[]) new Object[data.length * 2];
		
		for (int i=0; i < data.length; i++)
		{
			tempData[i] = data[i];
		}
		
		data = tempData;
	}

	
	public boolean add(T arg0) {
		
		for (int i = 0; i < size; i++)
			if (data[i] == arg0)
				return false;
		
		
		size++;
		
		
		if (size > data.length)
			grow();
		
		
		data[size - 1] = arg0;
		
		
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean anyItemsWereAdded = false;

		
		for (T element : arg0)
		{
			
			boolean success = add(element);
			
			anyItemsWereAdded = anyItemsWereAdded || success;
		}
		
		
		return anyItemsWereAdded;
	}
	
	
	public void clear() {
		
		for (int i = 0; i < size; i++)
		{
			data[i] = null;
		}
		
		size = 0;
	}
	
	
	public boolean contains(Object arg0) {
		
		for (int i = 0; i < size; i++)
			
			if (arg0 == data[i])
				
				return true;
		
		
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		
		boolean allItemsAreContained = true;
		
		
		for (Object element : arg0)
		{
			
			allItemsAreContained = allItemsAreContained && contains(element);
		}
		
		
		return allItemsAreContained;
	}

	
	public boolean isEmpty() {
		
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		
		for (int i = 0; i < size; i++)
			
			if (arg0 == data[i])
			{
				
				for (int j = i+1; j < size; j++)
					data[j-1] = data[j];
				
				data[size-1] = null;
				
				size--;
				
				return true;
			}
		
		
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		
		boolean anyItemsWereRemoved = false;
		
		
		for (Object element : arg0)
		{
			
			boolean success = remove(element);
			
			anyItemsWereRemoved = anyItemsWereRemoved || success;
		}
		
		
		return anyItemsWereRemoved;
	}
	
	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<T> iterator = iterator();

		
		boolean anyItemsWereRemoved = false;
		
		
		while (iterator.hasNext())
		{
			
			if (!arg0.contains(iterator.next()))
			{
				
				iterator.remove();
				
				anyItemsWereRemoved = true;
			}
		}
		
		
		return anyItemsWereRemoved;
	}
	
	
	public int size() {
		return size;
	}
	
	
	public Object[] toArray() {
		
		Object[] tempNew = new Object[size];
		for(int i = 0; i < size; i++)
		{
			tempNew[i] = data[i];
		}
		return tempNew;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		
		ArrayList<T> list = new ArrayList<T>(this);
		
		
		for (int i = 0; i < size - 1; i++)
		{
			
			T minimum = list.get(i);
			
			int minimumPosition = i;
			
			
			for (int j = i; j < size; j++)
			{
				
				T candidate = list.get(j);
				
				
				if (cmp.compare(minimum, candidate) > 0)
				{
					
					minimum = candidate;
					
					minimumPosition = j;
				}
			}
			
			
			T temp = list.get(i);
			list.set(i, minimum);
			list.set(minimumPosition, temp);
		}
		
		
		return list;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		
		private int position = 0;
		
		
		private boolean nextSinceLastRemove = false;
		
		public ArrayCollectionIterator() {
			
		}

		
		@Override
		public boolean hasNext() {
			return
				
				position < data.length &&
				
				data[position] != null;
		}

		
		@Override
		public T next() {
			
			if (!hasNext())
				throw new NoSuchElementException();
			
			
			nextSinceLastRemove = true;
			
			
			return data[position++];
		}

		
		@Override
		public void remove() {
			
			if (!nextSinceLastRemove)
				throw new IllegalStateException();
			
			
			for (int j = position; j < size; j++)
			{
				data[j-1] = data[j];
			}
			
			data[size-1] = null;
			
			size--;
			
			position--;
			
			nextSinceLastRemove = false;
		}
	}

}
