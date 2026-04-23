//comments: 35
//comment length: 3656
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
		T[] expandedArray = (T[]) new Object[data.length * 2];
		
		for(int i = 0; i < data.length; i++) {
			expandedArray[i] = data[i];
		}
		
		data = expandedArray;
	}


	
	public boolean add(T arg0) {
		
		
		if (arg0 == null) {
			return false;
		}
		if(contains(arg0) == true)
		{
			return false;
		}
		
		if(data.length == size + 1) {
			grow();
		}
		
		data[size] = arg0;
		
		
		size += 1;
		
		return true;
		
	}
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		for(T obj : ((ArrayCollection<T>) arg0).data()) 
		{
			this.add(obj);
		}
		
		return true;
	}
	
	
	public void clear() {
		int i = 0;
		int sizeTracker = size;
		
		while(i < sizeTracker) {
			
			remove(data[0]);
			i += 1;
		}
	}
	
	public boolean contains(Object arg0) {
		
		for(int i = 0; i < size; i++) {
			if(data[i].equals(arg0)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean containsAll(Collection<?> arg0) {
		for(T arg0objects : ((ArrayCollection<T>) arg0).data()) {
			
			
			if(arg0objects == null)
			{
				continue;
			}
			
			if(!(this.contains(arg0objects))) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		
		
		for(int i = 0; i < this.size; i++) {
			if(!(this.data[i].equals(null))){
				return false;
			}
		}
		
		return true;
	}
	
	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		
		if(contains(arg0) == false) {
			return false;
		}
		
		
		for(int i = 0; i <= this.size; i++) {
			if(this.data[i].equals(arg0)) {
				this.data[i] = null;
				
				moveBack(i);
				this.size--;
				return true;
			}
		}
		return false;
	}
	
	
	public T[] data() {
		return data;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean removed = false;
		
		
		for(T objects : ((ArrayCollection<T>) arg0).data()) {
			if(this.remove(objects)) {
				removed = true;
			}
		}
		
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		ArrayCollection <T> notSharedData =  new ArrayCollection<T>();
		
		
		for(int i = 0; i < this.size(); i++) {
			if(!(arg0.contains(this.data[i])))
				notSharedData.add(this.data[i]);
		}
		
		this.removeAll(notSharedData);
		
		if(isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] array = new Object[data.length];
		int indx = 0;
		
		
		for(T obj : data) {
			if(obj == null) {
				continue;
			}
			array[indx] = obj;
			indx ++;
		}
		
		return array;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sortedArrayList = new ArrayList<T>();
		
		for (int i = 0; i < size - 1; i++) 
		{
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare(data[j], data[minIndex]) < 0)
					minIndex = j;
			T temp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = temp;
			sortedArrayList.add(data[minIndex]);
		}
		
		
		return sortedArrayList;
	}
	
	
	public void moveBack(int index) {
		for(int i = index; i < size; i++) {
			data[i] = data[i + 1];
		}
	}
	
	public int lengthSizeMethod()
	{
		return data.length;
	}
	
	public String toString()
	{
		return "";
	}


	private class ArrayCollectionIterator implements Iterator<T>
	{
		boolean wasNext;
		T wasNextValue;
		
		public ArrayCollectionIterator()
		{
			wasNext = false;
			wasNextValue = null;
		}

		
		public boolean hasNext() {
				if(data[size] == null) {
					return false;
				}
				else {
					return true;
				}
		}
		
		
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			if(wasNext == false) {
				wasNext = true;
			}
			else {
				wasNext = false;
			}
			wasNextValue = data[size++];
			return wasNextValue;
		}
		
		
		public void remove() {
			if(wasNext == false) {
				throw new IllegalStateException();
			}
			
			ArrayCollection.this.remove(data[size]);
		}
		
	}

}
