//comments: 26
//comment length: 3647


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
		T temp[] = (T[]) new Object[data.length * 2];
		
		for(int i = 0; i < size; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	
	public boolean add(T arg0) {
		
		if(contains(arg0)) return false;
		
		if(size == data.length) grow();
		
		data[size] = arg0;
		size++;
		
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		int origSize = size;
		
		for(T item: arg0) {
			add(item);
		}
		
		if(size > origSize) return true;
		
		return false;
	}

	
	public void clear() {
		for(int i = 0; i < size; i++) {
			data[i] = null;
		}
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for(T d: data) {
			if(arg0.equals(d)){
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for(Object item: arg0) {
			if(!contains(item))
				return false;
		}
		return true;
	}

	
	public boolean isEmpty() {
		if(size == 0) return true;
		return false;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		if(!contains(arg0)) return false;
		
		for(int i = 0; i < size; i++) {
			if(data[i].equals(arg0)) {
				data[i] = null;
				regroup(i);
				size--;
				break;
			}
		}
		
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		int origSize = size;
		
		for(Object item: arg0) {
			if(contains(item))
				remove(item);
		}
		
		if(size < origSize) return true;
		return false;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		int origSize = size();
		ArrayCollectionIterator itr = new ArrayCollectionIterator();
		boolean exists;
		
		while(itr.hasNext()) {
			exists = false;
			T item = itr.next();
			for(Object el: arg0) {
				if(el.equals(item)) {
					exists = true;
				}
			}
			if(!exists) {
				itr.remove();
			}
		}
		
		if(origSize > this.size()) return true;
		return false;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] newArray = new Object[size];
		
		for(int i= 0; i < size; i++)
			newArray[i] = data[i];
		
		return newArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sortedData = new ArrayList<T>();
		sortedData.addAll(this);
		for(int j = 0; j < size; j++)
			for(int i = j + 1; i < size; i++) {
				if(cmp.compare(sortedData.get(i), sortedData.get(j)) < 0) {
					T temp = sortedData.get(j);
					sortedData.set(j, sortedData.get(i));
					sortedData.set(i, temp);
				}
			}
		return sortedData;
	}
	
	
	private void regroup(int position) {
		
		for(int i = position; i < size-1; i++) {
			data[i] = data[i+1];
		}
		
		data[size-1] = null;
	}
	
	
	private int getLength() {
		return data.length;
	}


	private class ArrayCollectionIterator implements Iterator<T>
	{
		int ind;
		boolean state = false;
		
		public ArrayCollectionIterator()
		{
			ind = 0;
			state = false;
		}

		
		public boolean hasNext() {
			if(ind >= ArrayCollection.this.size())
				return false;
			return true;
		}

		
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			state = true;
			return ArrayCollection.this.data[ind++];
		}

		
		public void remove() {
			if(!state)
				throw new IllegalStateException();
			
			ArrayCollection.this.remove(ArrayCollection.this.data[--ind]);
			state = false;
		}

	}

}
