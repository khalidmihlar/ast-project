//comments: 33
//comment length: 2925
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
		
		
		T temp[] = (T[]) new Object[data.length*2];
		for(int counter =0; counter < data.length; counter++) {
			temp[counter] = data[counter];
		}
		data = temp;
	}


	public boolean add(T arg0) {
		
		
		for(int counter =0; counter < data.length; counter++) {
			if(arg0.equals(data[counter])) {
				return false;
			}
		}
		if(data[data.length-1]!=null)	{
			this.grow();
		}
		for(int counter =0; counter < data.length; counter++) {
			if(data[counter]==null) {
				data[counter]= arg0;
				break;
			}
		}
		
		return true;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean end = false;
		Iterator<?> iter = arg0.iterator();
		while(iter.hasNext()) {
			boolean present = false;
			for(int counter =0; counter < data.length; counter++) {				
				if(data[counter]== iter) {
					present = true;
				}
			}
			if(present = false) {
			 this.add((T) iter.next());
			 end = true;
			}
		}
		return end;
	}

	public void clear() {
		
		for(int x = 0; x < data.length; x++) {
			data[x]=null;
		}
	}

	public boolean contains(Object arg0) {
		
		
		for(int counter =0; counter < data.length; counter++) {
			if(arg0.equals(data[counter])) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		
		Iterator<?> iter = arg0.iterator();
		while(iter.hasNext()) {
		if(!this.contains(iter.next())==false) {
			return false;
		}
		}
		return true;
	}

	public boolean isEmpty() {
		
		if(data[0]==null) {
			return true;
		}
		return false;
	}

	public Iterator<T> iterator() {
		
		this.iterator();
		return null;
	}

	public boolean remove(Object arg0) {
		
		
		for(int counter =0; counter < data.length; counter++) {
			if(arg0.equals(data[counter])) {
				for(int x = counter; x < data.length-1; x++) {
					data[x] = data[x+1]; 
				}
				data[this.size()] = null;
				return true;
				
			}
		}
		return false;
	}

	public boolean removeAll(Collection<?> arg0) {
		
		boolean hasRemoved = false;
		Iterator<?> iter = arg0.iterator();
		while(iter.hasNext()) {
			T temp = (T) iter.next();
		if(this.contains(temp)==true) {
			this.remove(temp);
			hasRemoved = true;
		}
		}
		return hasRemoved;
	}

	public boolean retainAll(Collection<?> arg0) {
		
		
		
		Iterator<?> iter = arg0.iterator();
		T tempf[] = (T[]) new Object[data.length];
		int counter = 0;
		while(iter.hasNext()) {
			T temp = (T) iter.next();
			if(this.contains(temp)==true) {
				tempf[counter] = temp;
				counter ++;
			}
		}
		int size = this.size();
		this.clear();
		for(int x = 0; x < tempf.length; x++) {
			this.add(tempf[x]);
		}
		int size2 = this.size();
		if(size != size2) {
			return true;
		}
		return false;
	}

	public int size() {
		
		
		int size = 0;
		for(int counter =0; counter < data.length; counter++) {
			if(data[counter]!=null) {
				size++;
			}
		}
		return size;
	}

	public Object[] toArray() {
		
		T temp[] = (T[]) new Object[data.length];
		for(int counter =0; counter < data.length; counter++) {
			temp[counter]= data[counter];
			}
		return temp;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> temp = (ArrayList<T>) new ArrayList(data.length);
			for (int i = 0; i < data.length - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < data.length; j++)
			if (cmp.compare(data[j], data[minIndex]) < 0)
			minIndex = j;
			temp = (ArrayList<T>) data[i];
			}
		return temp;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int index;
		private boolean hasNext;
		public ArrayCollectionIterator()
		{
			index = 0;
			hasNext = false;
			
		}

		public boolean hasNext() {
			
			if(this.hasNext()) {
				return true;
			}
			return false;
		}

		public T next() {
			
			hasNext = true;
			index ++;
			return this.next();
		}

		public void remove() {
			if(hasNext == true) {
			hasNext= false;
			data[index]= null;
			}
			
			
		}

	}

}
