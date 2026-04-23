//comments: 54
//comment length: 8076
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
	T newData[] = (T[]) new Object[data.length * 2];
	for (int i = 0; i < data.length; i++)
		newData[i] = data[i];
	data = newData;
	}

	
	public boolean add(T input) {
		
		if (contains(input))
			return false;
		
		
		if (data.length == size + 1)
			grow();
		
		
		data[size] = input;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> input) {
		boolean itemsAdded = false;

		for (T i: input)
			
			
			
			if (add(i))
				itemsAdded = true;
		return itemsAdded;
	}
	
	
	public void clear() {
		size = 0;
	}

	
	public boolean contains(Object input) {
		for (Object i: this)
			if (i.equals(input))
				return true;
		return false;
	}

	
	public boolean containsAll(Collection<?> input) {
		for (Object i: input)
			if (!contains(i))
				return false;
		return true;
	}

	
	public boolean isEmpty() {
		if (size != 0)
			return false;
		return true;
	}

	
	public boolean remove(Object input) {
		for (int i = 0; i < size; i++)
			if(data[i].equals(input)) {
				
				
				for (int j = i + 1; j < size; j++, i++) 
					data[i] = data[j];
				
				size--;	
				return true;
				}	
		return false;
	}

	
	public boolean removeAll(Collection<?> input) {
		boolean itemsRemoved = false;

		for (Object i: input)
			
			
			
			if (remove(i))
				itemsRemoved = true;
		return itemsRemoved;
	}

	
	@SuppressWarnings("unchecked")  
	public boolean retainAll(Collection<?> input) {
		
		boolean itemsRemoved = false;
		
		T[] newData = (T[]) new Object[input.size()];
		int newDataIndex = 0;
		
		
		
		for(T i: this)
			
			if(input.contains(i))
				newData[newDataIndex++] = i;
			
			
			else
				itemsRemoved = true;
		
		
		
		
		
		size = newDataIndex;
		this.data = newData;
		return itemsRemoved;
	}
	
	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] newArray = new Object[this.size];
		
		for (int i = 0; i < newArray.length; i++)
			newArray[i] = data[i];
			
		return newArray;
	}

	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] input) {
		return null;
	}



	
	@SuppressWarnings("unchecked")
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		
		T[] tempArr = (T[]) toArray();
		
		
		
		
		for (int i = 0; i < tempArr.length - 1; i++) {
            int j, minIndex;
            for (j = i + 1, minIndex = i; j < tempArr.length; j++)
                if (cmp.compare(tempArr[j], tempArr[minIndex]) < 0)
                    minIndex = j;
            T temp = tempArr[i];
           	tempArr[i] = tempArr[minIndex];
            tempArr[minIndex] = temp;
        }
		
		
		return new ArrayList<T>(Arrays.asList(tempArr));
	}

	
	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int index = 0;
		private boolean removed = true;
		
		public ArrayCollectionIterator()
		{
		}

		
		public boolean hasNext() {
			if (index < size)
				return true;
			return false;
		}

		
		public T next() {
			removed = false;
			if (index == size)
				throw new NoSuchElementException();
			return data[index++];
		}

		
		public void remove() {
			if(!removed) {
				for (int i = index; i < size; i++)
					data[i - 1] = data[i];
				size--;
				removed = true;
			}
			else 
				throw new IllegalStateException();
		}

	}
}
