//comments: 23
//comment length: 3946
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
		if(this.size == this.data.length) {
			T temAry[] = (T[]) new Object[this.size * 2];
			for(int idx = 0; idx < this.size; idx++) {
				temAry[idx] = this.data[idx];
			}
			this.data = temAry;
		}
	}
	
	private int search(T arg0) {
		for(int idx = 0; idx <= this.size; idx++) {
			if(this.data[idx] == arg0)
				return idx;
		}
		return -1;
	}

	
	public boolean add(T arg0) {
		if(this.search(arg0) == -1) {
			this.data[size] = arg0;
			size++;
			this.grow();
			return true;
		}
		return false;
	}
	

	public boolean addAll(Collection<? extends T> arg0) {
		boolean added = false;
		for(T e : arg0) {
			if(add(e))
				added = true;
		}
		return added;
	}

	
	public void clear() {
		this.data = (T[]) new Object[10];
		this.size = 0;
	}

	
	public boolean contains(Object arg0) {
		if (this.search((T) arg0) != -1)
			return true;
		
		return false;
	}
	
	

	public boolean containsAll(Collection<?> arg0) {
	
		for(Object e : arg0) {
			if(!contains(e)) 
				return false;
		}
		return true;
	}

	
	public boolean isEmpty() {
		if(this.size == 0)
			return true;
		
		return false;
	}
	
	
	public Iterator<T> iterator() {
		
		return new ArrayCollectionIterator(data, size);
	}

	
	
	public boolean remove(Object arg0) {
		int local = this.search((T) arg0);
		
		if(local == -1)
			return false;
		
		for(int idx = local; idx < size -1; idx++) {
			this.data[idx] = this.data[idx + 1];
		}
		this.data[size - 1] = null;
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean removedSomething = false;
		for (Object obj : arg0) {
			if(this.remove(obj))
				removedSomething = true;
		}
		return removedSomething;
	}
	
	
	public boolean retainAll(Collection<?> arg0) {
		ArrayList<T> toBeRemoved = new ArrayList<T>();
		boolean wasRemoved = false;
		for (T e : this.data) {
			if(e != null) {
				boolean isThere = false;
				for(Object obj : arg0) {
					if(e == obj)
						isThere = true;
					}
					if(!isThere) {
						toBeRemoved.add(e);
						wasRemoved = true;
				}
			}
		}
		this.removeAll(toBeRemoved);
		return wasRemoved;
	}
	
	
	public int size() {
		return this.size;
	}
	
	
	public Object[] toArray() {
		Object[] objArray = new Object[size];
		for (int idx = 0; idx < size; idx++) {
			objArray[idx] = data[idx];
		}
		return objArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayCollection<T> copy = this;
		ArrayList<T> arraySorted = new ArrayList<T>();
		
			while(copy.size > 0) {
				T min = copy.data[0];
				for(int jdx = 1; jdx < copy.size; jdx++) {
					if(cmp.compare(min, copy.data[jdx]) > 0)
						min = copy.data[jdx];
				}
				arraySorted.add(min);
				copy.remove(min);
			}
		return arraySorted;
	}


	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		T[] dataArray;
		int size;
		int placeHolder;
		
		public ArrayCollectionIterator(T[] dataArray, int size)
		{
			this.dataArray = dataArray;
			this.size = size;
		}

		public boolean hasNext() {
			if (placeHolder < size)
				return true;
			
			return false;
		}

		public T next() 
		{
			return dataArray[placeHolder + 1];
		}

		public void remove() {
			dataArray[placeHolder] = null;
		}

	}

}
