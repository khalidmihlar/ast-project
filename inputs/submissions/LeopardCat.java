//comments: 63
//comment length: 3530
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;


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
		
		if(size < data.length) {
			
			T[] dataCopy = (T[]) new Object[data.length + 10];
			
			for(int i = 0; i < size; i++) {
				dataCopy[i] = data[i];
			}
			
			data = dataCopy;
		}
			
	}

	
	public boolean add(T arg0) {
		
		grow();
		data[size] = arg0; 
		size++; 
		
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean didAdd = false; 
		
		for(T arg00 : arg0) { 
			add(arg00); 
			didAdd = true; 
		}
		
		return didAdd;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		
		data = (T[]) new Object[1]; 
		size = 0; 
		
	}

	
	public boolean contains(Object arg0) {
		
		if(isEmpty()) 
			return false;
		
		for(int i = 0; i < size; i++) { 
			if(data[i].equals(arg0)) 
				return true;
		}
			
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
					
		if(isEmpty()) 
			return false;
		
		for(Object i : arg0) { 
			
			if(!this.contains(i)) 
				return false;
			
		}
		
		return true;
	}

	
	public boolean isEmpty() { 
		
		if(size == 0)
			return true;
		
		return false;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator(); 
	}

	
	public boolean remove(Object arg0) {	
		if(!contains(arg0))
			return false;
			
		for(int i = 0; i < size; i++) 
			data[i] = data[i + 1];
					
		size--;
		
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) { 

		ArrayCollectionIterator s = new ArrayCollectionIterator(); 
		
		boolean itemRemoved = false;
		
		while(s.hasNext()) { 
			
			T other = s.next(); 
			
			if(arg0.contains(other)) {
				itemRemoved = remove(other); 
			}
	
		}

		return itemRemoved;
	}
	
	
	public boolean retainAll(Collection<?> arg0) { 
		
		ArrayCollectionIterator s = new ArrayCollectionIterator(); 
		
		while(s.hasNext()) {
			
			T other = s.next();
			
			if(arg0.contains(other)) { 
				
				remove(other);
				
				return true;
			}
	
		}

		return false;
	}

	
	public int size() { 
		return size;
	}

	
	public Object[] toArray() { 
		
		Object[] newArg000 = new Object[size]; 
		
		for(int i = 0; i < size; i++) { 
			newArg000[i] = data[i]; 
		}
		
		return newArg000; 
	}

	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T[] toArray(T[] arg0) {
				
		T[] newArg000 = (T[]) new Object[size];
		
		int i = 0;
		
		for(T arg00 : arg0) {
			
			newArg000[i] = arg00;
			
			i++;
		}
		
		return newArg000;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		
		@SuppressWarnings("unchecked")
		T[] newArray = (T[]) new Object[size];
		newArray = data.clone();
		
		for (int i = 0; i < size; i++) {
						  
			  int j, minIndex;
			  
			  for (j = i + 1, minIndex = i; j < size; j++) {
				  
				  if (cmp.compare(newArray[j], newArray[minIndex]) < 0) {
					  
					  minIndex = j;
					  
					  T temp = data[i];
					  
					  newArray[i] = newArray[minIndex];
					  
					  newArray[minIndex] = temp;
					  
				  }
				  
			  }
			  
		}
		
		ArrayList<T> newArrayList = new ArrayList<T>();
		
		for(int i = 0; i < size; i++) {
			
			newArrayList.add(newArray[i]);
			
		}
		
		return newArrayList;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
	
		int index = 0; 
		boolean legal = false; 
		
		
		public ArrayCollectionIterator() {
			super(); 
		}

		
		public boolean hasNext() { 
			return index < size;
		}

		
		public T next() { 
			
			if(hasNext()) {
				legal = true;
				return(data[index++]);
			}
			
			return null;
		}

		
		public void remove() { 

			if(!legal) {
				throw new IllegalStateException(); 
			}
			
			for(int i = index; i < size; i++) {
				
				data[i] = data[i + 1];
				data[size] = null;
				size--;
				legal = false;
				
			}
		}
		
	}
	
}
