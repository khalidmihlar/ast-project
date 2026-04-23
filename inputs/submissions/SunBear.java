//comments: 52
//comment length: 5154
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
		
		T[] temp = (T[]) new Object[size*2];
		
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		
		data = temp;
	}


	
	public boolean add(T value) {
		
		if (this.contains(value)) {
			return false;
		}
		if (this.size == data.length) {
			this.grow();
		}
		
		data[size++] = value;
		
		return true;
	}

	
	public boolean addAll(Collection<? extends T> collect) {
		
		boolean result = false;
		
		for (T item : collect) {
			if (this.add(item)) {
				result = true;
			}
		}
		
		return result;
	}

	
	public void clear() {
		
		this.size = 0;
		data = (T[]) new Object[10];
	}

	
	public boolean contains(Object collect) {
		
		for (T item : data) {
			if (collect.equals(item)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> collect) {
		
		if (collect.size() == 0) {
			return false;
		}
		
		for (Object item : collect) {
			if (!this.contains(item)) {
				return false;
			}
		}
		
		return true;
	}

	
	public boolean isEmpty() {
		return this.size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object value) {	
		
		for (int i = 0; i < this.size; i++) {
			
			if (data[i].equals(value)) {
				
				for (int j = i; j < this.size - 1; j++) {
					data[j] = data[j + 1];
				}
				size--;
				return true;
			}
		}
		
		return false;
	}

	
	public boolean removeAll(Collection<?> collect) {
		
		boolean result = false;
		
		for (Object item : collect) {
			if (this.remove(item)) {
				result = true;
			}
		}
		
		return result;
	}

	
	public boolean retainAll(Collection<?> collect) {
		Iterator<T> iter = this.iterator();
		
		boolean result = false;
		
		while (iter.hasNext()) {
			
			T next = iter.next();
			
			if (!collect.contains(next)) {
				this.remove(next);
				result = true;
			}
		}
		
		return result;
	}

	
	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		Object[] result = new Object[size];
		
		for (int i = 0; i < size; i++) {
			result[i] = data[i];
		}
		
		return result;
	}

	
	public <T> T[] toArray(T[] array) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		
		Object[] tem = this.toArray();
		ArrayList<T> list = new ArrayList<T>();
		for (int k = 0; k < tem.length; k++) {
			list.add((T)tem[k]);
		}
		
		for (int i = 0; i < list.size() - 1; i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < list.size(); j++)
				  if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
					  	minIndex = j;
			  T temp = list.get(i);
			  list.set(i, list.get(minIndex));
			  list.set(minIndex, temp);
		  }
		
		return list;
	}


	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		
		private int position;
		private T lastSeen;
		
		
		public ArrayCollectionIterator()
		{
			position = 0;
			lastSeen = null;
		}

		
		public boolean hasNext() {
			return position < size;
		}

		
		public T next() throws NoSuchElementException {
			if (this.hasNext()) {
				
				lastSeen = data[position];
				
				return data[position++];
			} else {
				throw new NoSuchElementException();
			}
		}

		
		public void remove() throws IllegalStateException{
			
			if (lastSeen == null) {
				throw new IllegalStateException();
			}
			
			for (int j = position; j < size - 1; j++) {
				data[j] = data[j + 1];
			}
			
			size--;
			lastSeen = null;
		}
	}

}
