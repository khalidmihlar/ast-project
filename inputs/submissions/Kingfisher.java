//comments: 32
//comment length: 4126
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
		T dataTemp[] = (T[]) new Object[data.length *2];
		for (int i = 0; i < data.length; i++) {
			dataTemp[i] = data[i];
		}
		data = dataTemp;
	}


	
	public boolean add(T arg0) {
		
		if (this.contains(arg0))
			return false;
		
		
		if (size == data.length) 
			this.grow();
		
		
		data[size] = arg0;
		size++;
		return true;
	}
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		int count = 0;
		for (T element : arg0) {
			if (this.add(element)) {
				count++;

			}
		}
		if (count == arg0.size())
			return true;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void clear() {
		T dataTemp[] = (T[]) new Object[10];
		data = dataTemp;
		size = 0;
	}
	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (arg0.equals(data[i])) 
				return true;
		}
		return false;
	}
	
	public boolean containsAll(Collection<?> arg0) {
		int count = 0;
		for (Object element : arg0) {
			if (this.contains(element)) {
				count++;
				continue;
			}
		}
		if (count == arg0.size()) {
			return true;
		}
		return false;
			
	}
	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}
	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}
	
	public boolean remove(Object arg0) {
		int counter = 0;
		if (!(this.contains(arg0)))
			return false;
		for (int i = 0; i < this.size; i++) {
			if (i == this.size) {
				data[i] = null;
			}
			if (data[i].equals(arg0)) {
				data[i] = null;
				counter++;
				for (int j = i; j < size - 1; j++)
					data[j] = data[j + 1];
				size--;
			}
		}
			if (counter > 0)
				return true;
		return false;
	}
	
	public boolean removeAll(Collection<?> arg0) {
		int counter = 0;
		for (Object element : arg0) {
			if (this.contains(element)) {
				this.remove(element);
				counter++;
			}
		}
		if (counter > 0) 
			return true;
		return false;
	}
	
	
	public boolean retainAll(Collection<?> arg0) {
		int counter = 0;
		T dataClone[] = data.clone();
		for (Object item : dataClone) {
			if (!(arg0.contains(item))) {
				this.remove(item);
				counter++;
			}
		}
		if (counter > 0)
			return true;
		return false;
	}
	
	
	public int size() {
		return size;
	}
	
	
	public Object[] toArray() {
		Object[] newArray = new Object[data.length];
		for (int i = 0; i < data.length; i++) {
			newArray[i] = data[i];
		}
		return newArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}


		
		public ArrayList<T> toSortedList(Comparator<? super T> cmp)
		{
			ArrayList<T> finalArray = new ArrayList<T>();
			
			
			for (int i = 0; i < size - 1; i++) {
				int j, minIndex;
				for (j = i + 1, minIndex = i; j < size; j++)
					if (cmp.compare(data[j], data[minIndex]) < 0)
						minIndex = j;
				T temp = data[i];
				data[i] = data[minIndex];
				data[minIndex] = temp;
			}
			for (int i = 0; i < size; i++) {
				finalArray.add(i, data[i]);
			}
			return finalArray;
		}



		private class ArrayCollectionIterator implements Iterator<T>
		{
			private int pointer; 
			
			private boolean nextCalled;
			
			
			public ArrayCollectionIterator()
			{
				pointer = 0;
				nextCalled = false;
			}
			
			
			public boolean hasNext() {
				if (pointer + 1 <= size)
					return true;
				return false;
			}

			
			public T next() {
				if (this.hasNext()) {
					T next = data[pointer];
					nextCalled = true;
					pointer++;
					return next;
				}
				throw new NoSuchElementException();
			}
			
			
			public void remove() {
				if (this.nextCalled) {
					ArrayCollection.this.remove(data[pointer]);					
					pointer--;
					nextCalled = false;	
				}
				else 
					throw new IllegalStateException();
			}

		}

	}
