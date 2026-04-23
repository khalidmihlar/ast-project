//comments: 23
//comment length: 3134


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
		T newData[] = (T[]) new Object[size * 2];
		for(int i = 0; i < size; i++) 
		{
			newData[i] = data[i];
		}
		
		data = newData;
	}


	public boolean add(T arg0) {
		if(this.contains(arg0))
			return false;
		if(this.data.length == size)
			this.grow();
		this.data[size] = arg0;
		size++;
		return true;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		boolean returnBool = false;
		for(T arg: arg0) 
		{
			if(this.add(arg))
			{
				returnBool = true;
			}
			
		}
		return returnBool;
	}

	public void clear() {
		for(int i = 0; i < size; i++) 
		{
			this.data[i] = null;
		}
		
		size = 0;
		
	}

	public boolean contains(Object arg0) {
		for(int i = 0; i < size; i++) 
		{
			if(data[i].equals(arg0)) 
				return true;
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		for(Object arg: arg0) 
		{
			if(!this.contains(arg)) 
			{
				return false;
			}
		}
		
		return true;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	public boolean remove(Object arg0) {
		if(this.size == 0) 
		{
			return false;
		}
		for(int i = 0; i < size; i++) 
		{
			if(this.data[i].equals(arg0)) 
			{
				for(int j = i; j < size - 1; j++) 
				{
					this.data[j] = this.data[j + 1];
				}
				size--;
				return true;
			}
		}
		
		return false;
		
	}

	public boolean removeAll(Collection<?> arg0) {
		boolean returnBool = false;
		
		for(Object arg: arg0) 
		{
			if(this.remove(arg))
				returnBool = true;
			
		}
		return returnBool;
	}

	public boolean retainAll(Collection<?> arg0) {
		boolean returnBool = false;
		boolean containsBool = false;
		for(int i = 0; i < this.size; i++) 
		{
			for(Object arg: arg0) 
			{
				
				if(this.data[i].equals(arg)) 
				{
					
					containsBool = true;
				}
				
				
				
			}
			
			if(!containsBool) 
			{
				this.remove(this.data[i]);
				
				returnBool = true;
				i--;
			}
			
			containsBool = false;
			
			
			
		}
		
		return returnBool;
		
		
	}

	public int size() {
		return this.size;
	}

	public Object[] toArray() {
		T newData[] = (T[]) new Object[size];
		for(int i = 0; i < size; i++) 
		{
			newData[i] = data[i];
		}
		return newData;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> returnList = new ArrayList<T>();
		for(int i = 0; i < this.size; i++) 
		{
			returnList.add(this.data[i]);
		}
		
		for (int i = 0; i < this.size(); i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < this.size; j++)
			  if (cmp.compare(returnList.get(j), returnList.get(minIndex)) < 0)
			  minIndex = j;
			  T temp = returnList.get(i);
			  returnList.set(i, returnList.get(minIndex));
			  returnList.set(minIndex, temp);
			  
			  }
	
		
		return returnList;
	}
	



	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int index;
		private boolean legal;

		public ArrayCollectionIterator()
		{
		
			this.index = 0;
			this.legal = false;
			
		}

		public boolean hasNext() {
			return index < ArrayCollection.this.size();
		}

		public T next() {
			if(this.hasNext()) 
			{
				int current = index;
				this.legal = true;
				index++;
				return ArrayCollection.this.data[current];
			}
			
			throw new NoSuchElementException();
		}
			

		public void remove() {
			if(!legal) 
			{
				throw new IllegalStateException();
			}
			
			ArrayCollection.this.remove(index);
			index--;
		}

	}

}
