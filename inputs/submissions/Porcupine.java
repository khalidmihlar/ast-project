//comments: 34
//comment length: 4099
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
	private void grow() {
	
		T[] tempArr = (T[]) new Object[data.length];
		for(int i = 0; i < size ; i++) { 
			tempArr[i] = data[i];
		}
	
		data = (T[]) new Object[data.length*2];
		for(int i = 0; i < size; i++) {
			data [i] = tempArr[i];
		}
	}
	
	public boolean add(T arg0) {
		if(contains(arg0)) {
			return false;
		}
		if (data.length == size()) {
			grow();
		}
		data[size] = arg0;
		size++;
		
		return true;
	}
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean b = false;
		
		for (T x: arg0) {
			if(add(x))
				b = true;
				
		}
		
		return b;
	}
	
	public void clear() {
		data = (T[]) new Object[10];
		size = 0;
		
	}
	
	
	public boolean contains(Object arg0) {
		for(int i = 0; i < size ; i++) {
			if(data[i].equals(arg0))
				return true;

		}
		return false;
	}
	
	public boolean containsAll(Collection<?> arg0) {
		
		for (Object x: arg0) {
			 if (!contains(x)) {
				 return false;
			 }
			
		}
		return true;
	}
	
	
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;

		}
	

	public Iterator<T> iterator() {
		ArrayCollectionIterator arrIterator = new ArrayCollectionIterator();
		return arrIterator;
	}
	
	
	public boolean remove(Object arg0) {
		
		for(int i =0; i < size; i++) {
			if (data[i].equals(arg0)) {
				for(int x =i; x < size - 1; x++) {
					data[x]= data[x+1];
				}
				size--; 
				return true;
			}
		}
		return false;
	}
	
	
	public boolean removeAll(Collection<?> arg0) {
		boolean b = false;
		
		for (Object x: arg0) {
			 if (remove(x)) {
				 b = true;
			 }
			
		}
		return b;
	}
	
	
	public boolean retainAll(Collection<?> arg0) {
		boolean b = false;
		
		Iterator<T> iterator = this.iterator();
		while(iterator.hasNext()) {
			if(!arg0.contains(iterator.next())) {
				iterator.remove();
					b = true;
				
			}
		
		}
		
		
		return b;
	}

	public int size() {
		return size;
	}

	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) new Object[size];
		for(int i = 0; i < size; i++)
			arr[i] = data[i];
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sorted = new ArrayList<T>();
		for ( int count = 0; count < size; count++) {
			sorted.add(data[count]);
		}
		for (int i =0; i < sorted.size()-1; i++) {
			int j, minIndex;
			for(j = i +1, minIndex = i; j < sorted.size(); j++)
			if (cmp.compare(sorted.get(j) , sorted.get(minIndex)) < 0)
				  minIndex = j;
			T temp = sorted.get(i);
			 sorted.set(i, sorted.get(minIndex));
			 sorted.set(minIndex, temp);
		}
		return sorted;
	}

	

	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int index;
		private boolean removePossible;
		
		public ArrayCollectionIterator()
		{
			index = 0;
			removePossible = false;
		}
		
		public boolean hasNext() {
			if (index < size) {
				return true;
			}

			return false;
		}
		
		
		public T next() {
			
			
			if(this.hasNext()) {
				index++;
				removePossible = true;
				return data[index-1];
			}
			else throw new NoSuchElementException();
			
		}
		
		public void remove() {
			if (removePossible) {
				ArrayCollection.this.remove(data[index-1]);
				removePossible = false;
				index--;
			}
			else {
				throw new IllegalStateException();
			}
		}

	}

}
