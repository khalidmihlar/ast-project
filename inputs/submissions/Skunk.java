//comments: 30
//comment length: 4780
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
		
		T[] temp = (T[]) new Object[size * 2];
		for(int idx = 0; idx < data.length; idx++) {
			temp[idx] = data[idx];
		}
		
		data = temp;
		
	}

	
	public boolean add(T obj) {
		
		if(data.length == size) {
			this.grow();
		}
		
		if(!this.contains(obj)) {
			data[size] = obj;
			size++;
			return true;
		}
		
		return false;
	}

	
	
	public boolean addAll(Collection<? extends T> collection) {
		
		Iterator<? extends T> iter = collection.iterator();
		boolean isAdded = false;
		while(iter.hasNext()) {
			if(this.add(iter.next())) { 
				isAdded = true;
			}
		}
		
		return isAdded;
	}

	
	public void clear() {
		
		size = 0;
		
	}

	
	public boolean contains(Object obj) {
		
		for(int idx = 0; idx < size; idx++) {
			if(data[idx].equals(obj)) {
				return true;
			}
		}
		
		return false;
	}

	
	public boolean containsAll(Collection<?> collection) {
		
		Iterator<?> iter = collection.iterator();
		boolean containsAll = true;
		while(iter.hasNext()) {
			if(!this.contains(iter.next())) {
				containsAll = false;
			}
		}
		
		return containsAll;
	}

	
	public boolean isEmpty() {
		
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object obj) {
		for (int idx = 0; idx < size; idx++) {
			if (data[idx].equals(obj)) {
				size--;
				for (int jdx = idx; jdx < size; jdx++) {
					data[jdx] = data[jdx + 1];
				}
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> collection) {
		Iterator<?> iter = collection.iterator();
		boolean isRemoved = false;
		while (iter.hasNext()) {
			if (this.remove(iter.next())) {
				isRemoved = true;
			}
		}
		return isRemoved;
	}

	
	public boolean retainAll(Collection<?> collection) {
		
		boolean isRemoved = false;
		Iterator<T> iter = this.iterator();
		
		while(iter.hasNext()) {
			if(!collection.contains(iter.next())) {
				iter.remove();
				isRemoved = true;
			}
		}
		
		return isRemoved;
	}

	
	public int size() {

		return size;
	}

	
	public Object[] toArray() {
		
		Object[] temp = new Object[size];
		for(int idx = 0; idx < size; idx++) {
			temp[idx] = data[idx];
		}
		
		return temp;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	@SuppressWarnings("unchecked")
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> temp = new ArrayList<T>();
		for(int idx = 0; idx < size; idx++) {
			temp.add(data[idx]);
		}
		
		sort(temp, cmp);
		
		return temp;
	}
	
	
	private static <T> void sort(ArrayList<T> list, Comparator<? super T> c) { 
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}


	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int location;
		
		public ArrayCollectionIterator()
		{
			location = 0;
		}
		
		public boolean hasNext() {
			return location < size;
		}
		
		public T next() {
			if (hasNext()) {
				return data[location++]; 
			}
			throw new NoSuchElementException();
		}
		
		public void remove() {
			if (location == 0) {
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(data[--location]); 
		}

	}

}
