//comments: 112
//comment length: 11353



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

		
		T newBaseArray[]; 
		
		newBaseArray = (T[]) new Object[data.length * 2];
		for(int itemNumber = 0; itemNumber < data.length; itemNumber++)
		{
			newBaseArray[itemNumber] = data[itemNumber];
		}
		data = newBaseArray;
	}

	
	
	
	
	
	public boolean add(T arg0) {
		
		if(this.contains(arg0))
		{
			return false;
		}
		if(size == data.length)
		{
			this.grow();
		}
		data[size] = arg0;
		size++;

		return true;
	}

	
	
	
	
	
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean addedItems = false;
		Iterator otherItr = arg0.iterator();
		
		while(otherItr.hasNext())
		{

			if(this.add( (T) otherItr.next()))
			{
				addedItems = true;
			}

		}
		return addedItems;
	}

	
	
	@SuppressWarnings("unchecked")
	public void clear() {
		
		T newBaseArray[];
		newBaseArray = (T[]) new Object[data.length];
		data = newBaseArray;
		size = 0;
	}

	
	
	
	public boolean contains(Object arg0) {
		for(int itemNumber = 0; itemNumber < size; itemNumber++)
		{
			if(arg0.equals(data[itemNumber]))
			{
				return true;
			}
		}
		return false;
	}

	
	
	
	public boolean containsAll(Collection<?> arg0) {
		boolean containsAllItems = true;
		Iterator otherItr = arg0.iterator(); 
		while(otherItr.hasNext()) 
			
		{

			if(!this.contains(otherItr.next())) 
			{
				containsAllItems = false; 
			}
		}

		return containsAllItems;
	}

	
	
	
	public boolean isEmpty() {
		for(int itemNumber = 0; itemNumber < data.length; itemNumber++)
		{
			if(data[itemNumber] != null)
			{
				return false;
			}
		}
		return true;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator(); 
	
	}

	
	
	
	
	
	
	
	public boolean remove(Object arg0) {
		
		boolean removeItem = false;
		int indexOfRemoved = -1;
		for(int itemNumber = 0; itemNumber < size; itemNumber++)
		{
			if(data[itemNumber].equals(arg0))
			{
				indexOfRemoved = itemNumber;
				removeItem = true;
			}
		}
		if(removeItem)
		{
			T[] newArray;
			newArray = (T[]) new Object[size - 1];
			for(int itemNumber = 0; itemNumber < indexOfRemoved; itemNumber++)
			{
				newArray[itemNumber] = data[itemNumber];
			}
			for(int itemNumber = indexOfRemoved + 1; itemNumber < size; itemNumber++)
			{
				newArray[itemNumber -1] = data[itemNumber];
			}
			data = newArray;
			size--;
		}

		return removeItem;
	}

	
	
	
	
	
	
	
	
	
	public boolean removeAll(Collection<?> arg0) {
		
		boolean removedItems = false;
		boolean temp;
		Iterator otherItr = arg0.iterator();
		
		while(otherItr.hasNext())
		{
			temp = this.remove(otherItr.next());
			if(temp)
			{
				removedItems = true;
			}
		}
		return removedItems;
	}

	
	
	
	
	
	
	
	
	
	
	
	public boolean retainAll(Collection<?> arg0) {
		
		boolean canRemove = true;
		boolean itemsRemoved = false;
		
		Iterator thisItr = this.iterator();
		Object temp;
		int locationCount = -1; 
		
		
		while(thisItr.hasNext())
		{
			
			temp = thisItr.next();
			locationCount++;
			if(canRemove == true && locationCount > 0)
			{
				thisItr.remove();
				canRemove = false;
				itemsRemoved = true;
			}
			Iterator otherItr = arg0.iterator();
			while(otherItr.hasNext())
			{
				if(temp.equals(otherItr.next()))
				{
					canRemove = false;
				}
			}
		}
		
		return itemsRemoved;
	}

	
	
	
	public int size() {
		
		return size;
	}
	

	
	
	public Object[] toArray() {
		
		Object[] newObjectArray = new Object[size];
		for(int itemNumber = 0; itemNumber < size; itemNumber++)
		{
			newObjectArray[itemNumber] = data[itemNumber];
		}
		return newObjectArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	
	
	
	
	
	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> collectionCopy = new ArrayList<T>();
		
		
		for(int itemNumber = 0; itemNumber < size; itemNumber++)
		{
			collectionCopy.add(data[itemNumber]);
		}
		
		
		for(int i = 0; i < collectionCopy.size() - 1; i++)
		{
			int j, minIndex;
			for(j = i + 1, minIndex = i; j < collectionCopy.size(); j++)
			{
				if(cmp.compare(collectionCopy.get(j), collectionCopy.get(minIndex))<0)
				{
					minIndex = j;
				}
			}
			T temp = collectionCopy.get(i);
			collectionCopy.set(i, collectionCopy.get(minIndex));
			collectionCopy.set(minIndex, temp);
		}
		
		return collectionCopy;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		int currentLocation;
		boolean canRemove;
		
		
		
		
		
		
		
		public ArrayCollectionIterator()
		{
			currentLocation = -1;
			canRemove = false;
		}

		
		
		
		
		public boolean hasNext() {
			
			return currentLocation < size - 1;
		}

		
		
		
		
		public T next() {
			
			if(this.hasNext() == false)
			{
				throw new NoSuchElementException();
			}
			currentLocation++;
			if(currentLocation > 0)
			{
				canRemove = true;
			}






			return data[currentLocation];
		}
		
		
		
		
		
		public void remove() {
			
			if(!canRemove)
			{
				throw new IllegalStateException();
			}
			canRemove = false;
			ArrayCollection.this.remove(data[currentLocation - 1]);
			currentLocation--;
		}

	}

}
