//comments: 62
//comment length: 6778
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
		T temp []; 
		temp = (T[]) new Object[(data.length) * 2];
		System.arraycopy(data, 0, temp, 0, data.length);
		data = temp;
	}

	
	
	public boolean add(T arg0) {
		
		
		
		if(this.size == this.data.length)
			grow();
		
		
		Iterator<T> iterD = new ArrayCollectionIterator();
		while(iterD.hasNext())
			
			
			if(iterD.next().equals(arg0))
				return false;
		
		
		
		data[size++] = arg0;
		return true;
	}

	
	
	
	@SuppressWarnings("unchecked")
	public boolean addAll(Collection<? extends T> arg0) {
		boolean toAdd = false;
		boolean flag = false;

		
		
		for(Object o : arg0) {
			for(T t : this) {
				if(t.equals(o)) { 
					toAdd = false;
					break;
				}
				else {
					toAdd = true; 
				}
			}
			if(toAdd == true) {
				
				this.add((T) o);
				flag = true;
			}
		}
		return flag;
		
	}
	
	
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
			size = 0;
	}
	

	
	public boolean contains(Object arg0) {
		
		
		if(this.isEmpty()) {
			return false;
		}
		
		
		for(T t : this) {
			if(t == null) 
				break;
			if(t.equals(arg0))
				return true;
		}
		return false;
	}

	
	
	public boolean containsAll(Collection<?> arg0) {
		boolean flag = false;
		Iterator<T> iterD;
		
		
		
		for(Object o : arg0) {
			iterD = new ArrayCollectionIterator();
			while(iterD.hasNext())
				if(o == iterD.next())
					
					
					flag = true;
		}
		return flag;
	}

	
	
	public boolean isEmpty() {
		boolean flag = false;
		
		
		
		if (size == 0) {
			flag = true;
		}
		return flag;
	}

	
	
	public Iterator<T> iterator() {
		Iterator<T> iter = new ArrayCollectionIterator();
		return iter;
	}

	
	
	public boolean remove(Object arg0) {
		int idx = size; 
		boolean flag = false;
		
		
		if(this.isEmpty()) {
			return false;
		}
		
		
		
		for(int i = 0; i < size; i++) {
			if(this.data[i].equals(arg0)) {
				data[i] = null;
				idx = i;
				flag = true;
			}
		}
		
		
		for(int i = idx; i < size - 1; i++) {
			this.data[i] = this.data[i + 1];
		}
		
		size--;
		return flag;
	}


	
	public boolean removeAll(Collection<?> arg0) {
		boolean flag = false; 
		
		
		for(Object o : arg0) {
			
			for(T t : this) {
				
				if(t.equals(o)) {
					this.remove(t);
					flag = true;
				}
			}
				
		}
		return flag;	
		
	}

	
	
	public boolean retainAll(Collection<?> arg0) {
		boolean flag = false;

			
			
			for(int i = 0; i < this.size(); i++) { 
				if(!arg0.contains(this.data[i])) {
					this.remove(this.data[i]);
					i--;
					flag = true;
				}
			
			}
		return flag;
	}

	
	
	public int size() {
		return size;
	}
	
	

	
	public Object[] toArray() {
		Object newArray[] = new Object[size];
		
		for(int i = 0; i < size; i++) {
			newArray[i] = data[i]; 
		}
		
		return newArray;
	}

	
	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}
	  
	  
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		  ArrayList<T> newArray = new ArrayList<>();

		  
		  for (int i = 0; i < this.size() - 1; i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < this.size(); j++)
				  if (cmp.compare(this.data[j], this.data[minIndex]) < 0)
					  minIndex = j;
			  T temp = this.data[i];
 			  this.data[i] = this.data[minIndex];
			  this.data[minIndex] = temp; 
			  }
		  
		  for(T t : this) 
			  newArray.add(t);
		  
		  return newArray;
	}


	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int idx;
		private boolean nextFlag;
				
		
		public ArrayCollectionIterator()
		{
			this.idx = 0;
			this.nextFlag = false;
		}

		
		public boolean hasNext() {
			return idx < size; 
			
		}

		
		public T next() {
			if (idx == size) {
				throw new NoSuchElementException(); 
				
			}
			nextFlag = true;
			return data[idx++];
		}

		
		public void remove() {
			if (nextFlag == true) { 
				ArrayCollection.this.remove(data[idx]); 
				
			}
			nextFlag = false;
		}

	}

}
