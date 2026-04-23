//comments: 92
//comment length: 10722
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;


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
		
		T dataCopy[] = (T[]) new Object[data.length*2];
		
		
		for(int i = 0; i < data.length; i++) {
			dataCopy[i] = data[i];
			
		}
		
		
		data = dataCopy;
	}

	
	public boolean add(T arg0) {
		
		
		for(int i = 0; i<data.length; i++) {
			
			
			if(arg0.equals(data[i]))
				return false;
		}	
		
		
		if(data.length==size) {
			this.grow();
		}
		
		
		data[size]= arg0;
		
		size++;
		
		return true;
	}

	
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		
		
		boolean anyAdded = false;
		
		
		for (T item : arg0) {
			
			
			if(this.add(item))
				anyAdded = true;
		}
		
		return anyAdded;
	}

	
	
	public void clear() {
		
		
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		
		
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		if (arg0 == null)
			return false;
		
		
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0))
				return true;
		}
		
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
				
		
		for(Object item: arg0) {
			if(!this.contains(item))
				return false;
		}
		return true;
	}

	
	public boolean isEmpty() {
		
		
		if (size == 0)
			return true;
		
		return false;
	}

	
	public Iterator<T> iterator() {
	
		return new ArrayCollectionIterator();
	}

	
	
	public boolean remove(Object arg0) {
		
		
		
		if(!this.contains(arg0))
			return false;
		
		
		for(int i = 0; i<size; i++) {
			if(data[i].equals(arg0)) {
				data[i] = null;
				if (i == size - 1) {
					size = size - 1;
					return true;
				}
				
				
				for(int j = i+1; j<size + 1; j++) {
					
					data[j-1] = data[j];
					
					if(j==size-1) {
						data[j]=null;
						size = size - 1;
						return true;
					}			
				}	
			}
		}	
		return false;
	}

	
	
	public boolean removeAll(Collection<?> arg0) {
		
		boolean removedAny = false;
		
		
		for (Object item : arg0) {
			
			
			if(this.remove(item))
				removedAny=true;
		}
		
		return removedAny;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		boolean removedAny = false;
		boolean itemExists = false;
		
		ArrayCollectionIterator itr = new ArrayCollectionIterator();
		
		while(itr.hasNext()) {
			Object cur = itr.next();

			
			itemExists = false;
			
			
			for(Object item: arg0) {
				if (arg0 == null)
					break;
				
				
				if(item.equals(cur)) {
					itemExists = true;
					break;
				}
			}
			
			if(!itemExists) {
				itr.remove();
				removedAny = true;
			}		
		}
		
		
		return removedAny;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		
		
		Object[] arr = new Object[size];
		
		
		for (int i = 0; i < size; i++) {
			arr[i] = data[i];
		}
		return arr;
				
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		
		ArrayList<T> dataCopy = new ArrayList<T>();
		for (int k = 0; k < size; k++) {
			dataCopy.add(data[k]);
		}

		
		for (int i = 0; i < dataCopy.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < dataCopy.size(); j++)
				if (cmp.compare(dataCopy.get(j), dataCopy.get(minIndex)) < 0)
					minIndex = j;
			T temp = dataCopy.get(i);
			dataCopy.set(i, dataCopy.get(minIndex));
			dataCopy.set(minIndex, temp);
		}
		return dataCopy;
	}


	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		
		private int current;
		
		
		
		
		private boolean canRemove = false;
		
		public ArrayCollectionIterator()
		{
			
			
			current = -1;
		}

		
		public boolean hasNext() {

			return (current<(size-1));
			
		}

		
		public T next() {
			
			current++;
			canRemove = true;
			return data[current];

		}

		
		public void remove() {
			
			
			
			if(!canRemove)
				throw new IllegalStateException();
			
			ArrayCollection.this.remove(data[current]);
			canRemove = false;
			
			
			current--;
		}

	}

}
