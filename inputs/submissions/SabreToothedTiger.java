//comments: 32
//comment length: 6115
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T>
{
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
		T[] newList = (T[]) new Object[data.length * 2];
		
		for (int idx = 0; idx < data.length; idx++)
		{
			newList[idx] = data[idx];
		}
		
		data = newList;
	}

	
	public boolean add(T arg0)
	{
		
		if ((size + 1) > data.length)
			this.grow();
		
		for (int idx = 0; idx < data.length; idx++)
		{
			if ((data[idx] != null) && (data[idx].equals(arg0)))
				return false;
		}

		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0)
	{
		boolean elementsAdded = false;
		
		for (T element : arg0)
		{
			
			if (elementsAdded)
			{
				this.add(element);
			}
			else
			{
				elementsAdded = this.add(element);
			}
		}
		
		return elementsAdded;
	}

	
	public void clear()
	{
		data = (T[]) new Object[data.length];
		size = 0;
	}

	
	public boolean contains(Object arg0)
	{
		for (int idx = 0; idx < size; idx++)
		{
			if (arg0.equals(data[idx]))
				return true;
		}
		
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0)
	{		
		for (Object element : arg0)
		{
			
			if (!this.contains(element))
				return false;
		}
		
		return true;
	}

	
	public boolean isEmpty()
	{
		return size == 0;
	}

	
	public Iterator<T> iterator()
	{		
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0)
	{
		if (this.size == 0)
			return false;
		
		for (int idx01 = 0; idx01 < this.size; idx01++)
		{
			if (data[idx01].equals(arg0))
			{
				data[idx01] = null;
				
				
				for (int idx02 = idx01; idx02 < data.length - 1; idx02++)
				{
					data[idx02] = data[idx02 + 1];
					
					if ((idx02 + 1) == data.length - 1)
					{
						data[idx02 + 1] = null;
					}
				}
			
				size--;
				return true;
			}
		}
		
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0)
	{
		if (this.size == 0)
			return false;
		
		boolean elementsRemoved = false;
				
			for (Object element : arg0)
			{
				
				if (elementsRemoved)
				{
					this.remove(element);
				}
				else 
				{
					elementsRemoved = this.remove(element);
				}
			}
				
		return elementsRemoved;
	}

	
	public boolean retainAll(Collection<?> arg0)
	{	
		ArrayList<T> newArray = new ArrayList<>();
		ArrayCollectionIterator iterator = new ArrayCollectionIterator();
		boolean elementsRemoved = false;
				
		for (int idx = 0; idx < size; idx++)
		{
			if (arg0.contains(data[idx]))
				newArray.add(data[idx]);
			else
				elementsRemoved = true;
		}
		
		data = (T[]) newArray.toArray();
		size = data.length;
		return elementsRemoved;
	}

	
	public int size()
	{
		return this.size;
	}

	
	public Object[] toArray()
	{
		Object[] collectionAsArray = new Object[size];
		
		for (int idx = 0; idx < size; idx++)
		{
			collectionAsArray[idx] = data[idx];
		}
		
		return collectionAsArray;
	}

	
	public <T> T[] toArray(T[] arg0)
	{
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{		
		for (int i = 0; i < size - 1; i++)
		{
			int j, minIndex;
		  
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare(data[j], data[minIndex]) < 0)
				  minIndex = j;
		  
			T temp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = temp;
		}
		
		ArrayList<T> sortedCollection = new ArrayList<>();
		
		for (int idx = 0; idx < size; idx++)
		{
			sortedCollection.add(data[idx]);
		}
		
		return sortedCollection;
	}

	
	
	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int posCurrent;
		private boolean removeCallable;
		
		
		public ArrayCollectionIterator()
		{
			posCurrent = -1;
			removeCallable = false;
		}

		
		public boolean hasNext()
		{	
			if ((posCurrent + 1) >= data.length)
				return false;
			
			return data[posCurrent + 1] != null;
		}

		
		public T next() throws NoSuchElementException
		{
			if (this.hasNext())
			{
				removeCallable = false;
				
				posCurrent++;
				return data[posCurrent];
			}
			else
			{
				throw new NoSuchElementException();
			}
		}

		
		public void remove()
		{
			if (removeCallable == true || posCurrent == -1)
			{
				throw new IllegalStateException();
			}
			else
			{
				ArrayCollection.this.remove(posCurrent);
				removeCallable = true;
			}
		}
	}
}