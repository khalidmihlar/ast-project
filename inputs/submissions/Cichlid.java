//comments: 36
//comment length: 4261
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
		T dataNew[] = (T[]) new Object[size*2];
		for(int i=0;i<size;i++) {
			dataNew[i]=data[i];
		}
		data=dataNew;
	}
	
	public boolean add(T item) {
		if(!this.contains(item)) {
			if(size==data.length)
				grow();
			data[size]=item;
			size++;
			return true;
		}
		return false;
	}
	
	public boolean addAll(Collection<? extends T> items) {
		boolean addedItem=false;
		for(T item:items) {
			if(this.add(item)) 
				addedItem=true;
		}
		return addedItem;
	}
	
	public void clear() {
		size=0;
	}
	
	public boolean contains(Object item) {
		for(int i=0;i<size;i++) {
			if(item.equals(data[i])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAll(Collection<?> items) {
		if (items.size()>size) 
			return false;
		for(Object item:items) {
			if(!this.contains(item)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isEmpty() {
		return size==0;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}
	
	public boolean remove(Object item) {
		for(int i=0;i<size;i++)
			if(data[i].equals(item)) {
				for(int j=i;j<size-1;j++) {
					data[j]=data[j+1]; 
				}
				size--; 
				return true;
			}
		return false;
	}
	
	public boolean removeAll(Collection<?> items) {
		boolean removedItem=false;
		for(Object item:items) {
			if(this.remove(item))
				removedItem=true; 
		}
		return removedItem;
	}
	
	public boolean retainAll(Collection<?> items) {
		boolean retainedItem=false;
		Iterator<T> itr = this.iterator();
		while (itr.hasNext())
			if(!items.contains(itr.next())) {
				itr.remove();
			}
			else 
				retainedItem=true;
		return retainedItem;
	}

	public int size() {
		return size;
	}
	
	public Object[] toArray() {
		Object returnArr[] = new Object[size];
		for(int i=0;i<size;i++) {
			returnArr[i]=data[i];
		}
		return returnArr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}


	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sortedList= new ArrayList<T>();
		for (int i=0;i<size;i++) {
			sortedList.add(data[i]);
		}
		for (int i = 0; i < size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare(sortedList.get(j), sortedList.get(minIndex)) < 0) 
					minIndex = j;
			T temp = sortedList.get(i);
			sortedList.set(i, sortedList.get(minIndex));
			sortedList.set(minIndex, temp);
		}
		return sortedList;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		private boolean nextCalledSinceRemove;
		private int currentPos;
		public ArrayCollectionIterator()
		{
			currentPos=0;
			nextCalledSinceRemove = false;
			
		}
		
		public boolean hasNext() {
			return(currentPos<size);
		}
		
		public T next() {
			if (this.hasNext()) {
				nextCalledSinceRemove = true;
				return data[currentPos++]; 
			}
			throw new NoSuchElementException();
		}
		
		public void remove() {
			if(nextCalledSinceRemove) {
			ArrayCollection.this.remove((Object) data[currentPos-1]);
			currentPos--; 
			nextCalledSinceRemove=false;
			}
			else
				throw new IllegalStateException();
		}

	}

}
