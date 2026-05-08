//comments: 27
//comment length: 3484
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
		T newData[] = (T[]) new Object[data.length * 2];
		for(int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}


	
	public boolean add(T arg0) {
		if(this.contains(arg0))
			return false;
		if(data.length == size)
			this.grow();
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean added = false;
		T[] addArray = (T[]) arg0.toArray();
		for(int i = 0; i < arg0.size(); i++) {
			if(this.add(addArray[i]))
				added = true;
		}
		return added;
	}

	
	public void clear() {
		for(int i = 0; i < size -1; i++)
			data[i] = null;
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for(int i = 0; i < size; i++) {
			if(data[i].equals(arg0))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		boolean contains = true;
		Object[] arg0Array = arg0.toArray();
		for(int i = 0; i <arg0.size(); i++) {
			if(!this.contains(arg0Array[i]))
				contains = false;	
		}
		return contains;
	}

	
	
	public boolean isEmpty() {
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		if(contains(arg0) != true)
			return false;
		int position = 0;
		for(int i = 0; i < size; i++) { 
			if(data[i].equals(arg0)) {
				position = i;
				break;
			}
		}
		if(position == size - 1) { 
			data[position] = null;
			size--;
			return true;
		}
		for(int i = position; i < size; i++) { 
			data[i] = data[i+1];
		}
		data[size - 1] = null;
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean removed = false;
		T[] deleteArray = (T[]) arg0.toArray();
		for(int i = 0; i < arg0.size(); i++) {
			if(this.remove(deleteArray[i]))
				removed = true;
		}
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean deleted = false;
		Iterator itr = this.iterator();
		while(itr.hasNext())
			if(!arg0.contains(itr.next())) {
				itr.remove();
				deleted = true;
			}
		return deleted;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] newArray = new Object[size];
		for(int i = 0; i < size; i++)
			newArray[i] = this.data[i];
		return newArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}


	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		T[] sizedArray  =(T[]) this.toArray();
		
	    for (int i = 0; i < size; i++) {
	    	int j, minIndex;
	    	for (j = i + 1, minIndex = i; j < size; j++)
	    		if (cmp.compare(sizedArray[j], sizedArray[minIndex]) < 0)
	    			minIndex = j;
	    	T temp = sizedArray[i];
	    	sizedArray[i] = sizedArray[minIndex];
	    	sizedArray[minIndex] = temp;
	    }
	    ArrayList<T> sorted = new ArrayList<T>(Arrays.asList(sizedArray));
	    return sorted;
	}


	private class ArrayCollectionIterator implements Iterator<T>
	{
		boolean nextAvailable;
		int iteratorPos;
		
		public ArrayCollectionIterator()
		{
			nextAvailable = false;
			iteratorPos = 0;
			
		}

		
		public boolean hasNext() {
			return (iteratorPos < size);
		}

		
		public T next() {
			if(!this.hasNext())
				throw new NoSuchElementException("No such element!");
			nextAvailable = true;
			return data[iteratorPos++];
		}

		
		public void remove() {
			if(nextAvailable == false)
				throw new IllegalStateException("Illegal state exception!");
			ArrayCollection.this.remove(data[--iteratorPos]);
			nextAvailable = false;
			
		}

	}

}
