//comments: 60
//comment length: 5303
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
	int capacity; 


	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
		capacity = 10;
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		
		
		if (size == capacity) {
			capacity *= 2;
			T newData[] = (T[]) new Object[capacity];
			for (int i=0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}
			
	}


	
	public boolean add(T arg0) {
		
		if (size == capacity) 
			this.grow();
		for (int i=0; i < size; i++) {
			if (data[i].equals(arg0))
				return false;
		}
		
		data[size] = arg0;
		
		size++;
		


		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean result = false;
		for (Object object : arg0) {
			if (object != null && this.add((T)object))
				result = true;
		}
		return result;
	}

	
	public void clear() {
		
		data = null;
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		
		for (int i=0; i < size; i++) {
			if (data[i].equals(arg0))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		
		boolean result = true;
		for (Object object : arg0) {
			if (object != null && this.contains(object))
				result = result && true;
			else
				result = result && false;
		}
		return result;
	}

	
	public boolean isEmpty() {
		
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		
		if (arg0 == null) 
			throw new IllegalStateException();
			
		
			
		boolean result = false;
		
		int index = 0;
		for (int i=0; i < size; i++) {
			if (data[i].equals(arg0)) {
				
				index = i;
				result = true;
			}
		}
		
		if (result) {
			for (int j=index; j < size-1; j++) { 
				data[index] = data[index + 1];
				data[size-1] = null;
			}
			size--;
		}
		
		
		return result;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		
		boolean result = false;
		for (Object object : arg0) {
			if (object != null && this.remove((T)object))
				result = true;
		}
		return result;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		boolean result = false;
		Iterator<T> ir = this.iterator();
		T current = null;
		while (ir.hasNext()) {
			current = ir.next();
			if (!arg0.contains(current)) {
				this.remove(current);
				result = true;
			}
		}
		return result;
	}

	
	public int size() {
		
		return size;
	}
	
	
	public int capacity() {
		return capacity;
	}

	
	public Object[] toArray() {
		
		Object[] obj = new Object[size];
		for (int i=0; i < size; i++) {
			obj[i] = data[i];
		}
		return obj;
	}

	
	public <T> T[] toArray(T[] arg0) {
		T[]obj=(T[])new Object[size];
		for(int i=0;i<size; i++) {
			obj[i]=(T) data[i];
		}
		return obj;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		for (int i = 0; i < size - 1; i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < size; j++) {
				  int x=cmp.compare(data[j], data[minIndex]);
				  if (x < 0) {					 
					  minIndex = j;
				  }
			  }
		
			  T temp = data[i];
			  data[i]=data[minIndex];
			  data[minIndex]=temp;
			
			 
		}
		ArrayList<T> result = new ArrayList<T>(Arrays.asList(data));
		return result;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		int index; 
		T next; 
		
		
		public ArrayCollectionIterator()
		{
			
			index = -1;
			next = null;
		}

		
		public boolean hasNext() {
			
			if (data[index + 1] != null && index + 1 < size) {
				return true;
			}
			return false;
		}

		
		public T next() {
			
			if (this.hasNext()) {
				index++;
				
				
				next = data[index];
				
				return next;
				
			}
			else 
				throw new NoSuchElementException();
		}

		
		public void remove() {
			
		
			ArrayCollection.this.remove(next);
			next = null;
			index--;
		}

	}

}
