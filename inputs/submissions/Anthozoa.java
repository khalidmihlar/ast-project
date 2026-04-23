//comments: 42
//comment length: 5858
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
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		T tempData[];
		tempData = (T[]) new Object[data.length * 2];
		for (int index = 0; index < size; index++) {
			if (data[index] != null) {
				tempData[index] = data[index];
			}
		}
		data = tempData;
		
		
	}


	public boolean add(T arg0) {
		
		if(arg0 == null)
		{
			return false;
		}
		if(this.contains(arg0))
		{
			return false;
		}
		if(data.length < size + 1 )
		{
			this.grow();
		}
		
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean added = false;
		for(T thing: arg0)
		{
			if(this != null)
			{
			if(this.add(thing))
				{
				added = true;
				}
			}
		}
		return added;
	}

	
	public void clear() {
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		
		for(int index = 0; index < size; index++)
		{
			if(data[index].equals(arg0))
			{
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
	Iterator argumentIter = arg0.iterator();
	while(argumentIter.hasNext())
	{
		Object temp = argumentIter.next();
		if(temp == null)
		{
			return false;
		}
		if(!this.contains(temp))
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
		
		for(int index = 0; index < size; index++)
		{
			if(data[index].equals(arg0))
			{
				for(int jindex = index+1; jindex < size; jindex++)
				{
					data[jindex-1] = data[jindex];
				}
				size--;
				return true;
			}
		}
		
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		
		boolean removed = false;
		Iterator argumentIter = arg0.iterator();
		while(argumentIter.hasNext())
		{
			Object temp = argumentIter.next();
			if(temp == null)
			{
				continue;
			}
			if(this.remove(temp))
			{
				removed = true;
			}
		}
		
			return removed;
	}
	
	public boolean retainAll(Collection<?> arg0) {
		
		boolean removed = false;
		Iterator arrayCollIter = new ArrayCollectionIterator();
		while(arrayCollIter.hasNext()) {
			Object temp = arrayCollIter.next();
			if(!arg0.contains(temp)) {
				arrayCollIter.remove();
				removed = true;
			}
		}
		return removed;
	}
	
	public int size() {
		
		return size;
	}
	
	public Object[] toArray() {
		
		Object[] tempArray = new Object[size];
		for (int index = 0; index < size; index++)
		{
			tempArray[index] = data[index];
		}
		return tempArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> toSort = new ArrayList<T>();
		for(int index = 0; index < size;index++)
		{
			toSort.add(data[index]);
		}
		
		for (int i = 0; i < toSort.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < toSort.size(); j++)
				if (cmp.compare(toSort.get(j), toSort.get(minIndex)) < 0)
					minIndex = j;
			T temp = toSort.get(i);
			toSort.set(i, toSort.get(minIndex));
			toSort.set(minIndex, temp);
		}

		return toSort;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int index;
		private boolean canRemove;
		public ArrayCollectionIterator() {
			
			index = 0;
			canRemove = false;
		}

		
		public boolean hasNext() {
			
			
			return index < size;
		}
		
		public T next() {
			
			if (!this.hasNext())
				throw new NoSuchElementException();	
			canRemove = true;
			return data[index++];
		}
		
		public void remove() {
			
			if(!canRemove) {
				throw new IllegalStateException();
			}
			
			index--;
			ArrayCollection.this.remove(data[index]);
			canRemove = false;
			
		}

	}

}
