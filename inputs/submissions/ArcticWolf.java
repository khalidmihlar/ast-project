//comments: 73
//comment length: 6907
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
		
		T newDataArray[] = (T[]) new Object[size * 2];
		
		for(int i = 0; i < size; i++) 
		{
			newDataArray[i] = data[i];
		}
		
		data = newDataArray;
	}

	
	public boolean add(T toBeAdded) {
		
		
		if(this.contains(toBeAdded)) {
			return false;
		}
		
		if (size == data.length) 
		{
			grow();
		}
		
		data[size] = toBeAdded;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> listToBeAdded) {
		Iterator iter = listToBeAdded.iterator();
		
		boolean arrayChanged = false;
		
		while (iter.hasNext()) 
		{
			T currentEntry = (T) iter.next();
			
			
			if (this.add(currentEntry)) 
				arrayChanged = true;
		}
		return arrayChanged;
	}

	
	public void clear() 
	{
		
		
		
		
		if (size < 10) {
			data = (T[]) new Object[10];	
		}
		else {
			data = (T[]) new Object[size];
		}
		size = 0;
	}

	
	public boolean contains(Object toBeChecked) {
		
		for (int i =0; i < size; i++) 
		{
			
			
			if (data[i].equals(toBeChecked))
			{
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> listToBeChecked) {
		Iterator iter = listToBeChecked.iterator();
		
		while (iter.hasNext()) 
		{
			T currentEntry = (T) iter.next();
			
			if (!this.contains(currentEntry)) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		ArrayCollectionIterator iter = new ArrayCollectionIterator();
		
		
		iter.originalSize = this.size;
		
		return iter;
	}

	
	public boolean remove(Object toBeRemoved) {
		
		int removeItemIndex = 0;
		
		boolean removedItem = false;
			for (int i =0; i < size; i++) 
			{
				
				
				if (data[i].equals(toBeRemoved)) 
				{
					removeItemIndex = i; 
					removedItem = true;
					break;
				}
			}
		
		if (removedItem) 
		{
			
			for (int i = removeItemIndex; i < size -1; i++) 
			{
				data[i] = data[i + 1];
			}
			if (removeItemIndex == size) {
				data[removeItemIndex] = null;
			}
			
			size--;
			return true;
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> listToBeRemoved) {
		Iterator iter = listToBeRemoved.iterator();
		
		boolean atLeastOneRemoved = false;
		while (iter.hasNext()) 
		{
			T currentEntry = (T) iter.next();
			
			if (remove(currentEntry))
				atLeastOneRemoved = true;
		}
		return atLeastOneRemoved;
	}

	
	public boolean retainAll(Collection<?> listToBeRetained) 
	{
		ArrayCollectionIterator currCollectionIter = new ArrayCollectionIterator();
		
		
		currCollectionIter.originalSize = this.size;
		
		
		
		boolean itemWasRemoved = false;
		
		while (currCollectionIter.hasNext()) 
		{
			
			T currentItemCollection = (T) currCollectionIter.next();
			
			Iterator retainIter = listToBeRetained.iterator();
			boolean currentItemInBoth = false;
			
			
			while (retainIter.hasNext()) 
			{
				T currentItemRetain = (T) retainIter.next();
				if (currentItemCollection.equals(currentItemRetain))
				{
					currentItemInBoth = true;
				}
			}
			if (!currentItemInBoth) 
			{
				
				itemWasRemoved = true;
				currCollectionIter.remove();
			}
		}
		return itemWasRemoved;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		
		Object returnArray[] = new Object[data.length];
		
		for (int i = 0; i < size; i++) 
		{
			returnArray[i] = (Object) data[i];
		}
		return returnArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		for (int i = 0; i < this.size() - 1; i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < this.size(); j++)
			  if (cmp.compare(data[j], data[minIndex]) < 0)
			  minIndex = j;
			  T temp = data[i];
			  data[i] = data[minIndex];
			  data[minIndex] = temp;
			  }
		ArrayList<T> sortedList = new ArrayList<T>(Arrays.asList(data));
		return sortedList;
	}

	private class ArrayCollectionIterator implements Iterator<T>
	{
		
		T iteratorArray[];
		
		int iterPointer;
		
		boolean removeIsPossible = false;
		
		int originalSize;
		
		
		public ArrayCollectionIterator()
		{
			iteratorArray = (T[]) new Object[size];
			initializeIteratorArray();
			iterPointer = 0;
			originalSize = 0;
		}

		
		public void initializeIteratorArray() {
			for (int i = 0; i < size; i++) 
			{
				iteratorArray[i] = data[i];
			}
		}
		
		
		public boolean hasNext() {
			return iterPointer < originalSize;
		}

		
		public T next() {
			
			if (!hasNext()) 
			{
				throw new NoSuchElementException();
			}
			
			removeIsPossible = true;
			return iteratorArray[iterPointer++];
		}
		
		
		public void remove() {
			
			
			if (!removeIsPossible) {
				throw new IllegalStateException();
			}
			
			ArrayCollection.this.remove(iteratorArray[iterPointer - 1]);
			
			
			removeIsPossible = false;
		}

	}

}
