//comments: 35
//comment length: 2381
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
		T[] newData = (T[]) new Object[data.length * 2];
		
		for(int i = 0; i < data.length; i++)
		{
			newData[i] = data[i];
		}
		this.data = newData;
	}


	
	public boolean add(T arg0) {
		for(int i = 0; i < data.length; i++) 
		{
			
			if(arg0.equals(data[i]))
				return false;
		}
		if (size == data.length)
		{
			grow();
			data[size++] = arg0;
			
			return true;
		}
		
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean added = false;
		boolean addedTotal = false;
		Iterator<?> argIt = arg0.iterator();
		while(argIt.hasNext()) {
			added = this.add((T)argIt.next());
			if(added) {
				addedTotal = true;
			}
		}
		return addedTotal;
	}

	
	public void clear() {
		for (int i=0; i < size; i++)
		{
			data[i] = null;
			size = 0;
		}
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < data.length; i++)
		{
			if(arg0.equals(data[i]))
				return true;
		}
		
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		Iterator<?> argIt = arg0.iterator();
		
		while(argIt.hasNext())
		{
			Object value = argIt.next();
			if(!(contains(value)))
					return false;
		}
		
		return true;
	}

	
	public boolean isEmpty() {
		if(size == 0)
		{
			return true;
		}
		
		return false;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		T[] newData = (T[]) new Object[data.length];
		boolean found = false;
		int currentSize = 0;
		for(int i = 0; i < data.length; i++) {
			if(arg0.equals(data[i])) {
				found = true;
			}else if(!arg0.equals(null)){
				newData[currentSize] = data[i];
				currentSize++;
			}
		}
		
		this.data = newData;
		this.size = currentSize;
		return found;
	}

	public boolean removeAll(Collection<?> arg0) {
		Iterator<?> argIt = arg0.iterator();
		boolean itemRemoved = false;
		while(argIt.hasNext())
		{
			boolean removed = this.remove(argIt.next());
			if(removed) {
				itemRemoved = true;
			}
		}
		
		return itemRemoved;
	}

	public boolean retainAll(Collection<?> arg0) {
		boolean removed = false;
		Iterator<?> argIt = arg0.iterator();
		
		while(argIt.hasNext())
		{
			if(!arg0.contains(argIt.next()))
			{
				argIt.remove();
				removed = true;
			}
		}















		return removed;
	}

	public int size() {
		return size;
	}

	public Object[] toArray() {
		Object[] result = new Object[size];
		
		for(int i=0; i<size; i++)
		{
			result[i] = data[i];
		}
		return result;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> newData = new ArrayList<T>();
		for(int x = 0; x < data.length; x++) {
			newData.add(data[x]);
		}
		
		for(int i=0; i < size - 1; i++)
		{
			int j, minIndex;
				for(j = i+1, minIndex = i; j < newData.size(); j++)
					if(cmp.compare(newData.get(j), newData.get(minIndex)) < 0)
						minIndex = j;
				T temp = newData.get(i);
				newData.set(i, newData.get(minIndex));
				newData.set(minIndex, temp);
				
		}
		
		return newData;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int position;
		private boolean flag = false;
		
		public ArrayCollectionIterator()
		{
			position = 0;
		}

		public boolean hasNext() {
			if(position < size) {
				return true;
			}
			return false;
		}

		public T next() {
			T value = data[position];
			position++;
			flag = true;
			return value;
		}

		public void remove() {
			if(flag){
				position--;
				ArrayCollection.this.remove(data[position]);
				
			}
			flag = false;
		}

	

	}
	
	public int getLength() {
		return data.length;
	}
	
	public boolean equals(ArrayCollection<?> arr) {
		if(this.size() != arr.size()) {
			return false;
		}
		for(int i = 0; i > this.size(); i++) {
			if(this.data[i] != arr.data[i]) {
				return false;
			}
		}
		return true;
	}
	
	public boolean equals(Object[] arr) {
		if(this.size != arr.length) {
			return false;
		}
		for(int i = 0; i > this.getLength(); i++) {
			if(this.data[i] != arr[i]) {
				return false;
			}
		}
		return true;
	}
	

}
