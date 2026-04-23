//comments: 25
//comment length: 4786
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	private T data[]; 
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
		T temp[] = (T[]) new Object[size() * 2];
		for (int i = 0; i < size(); i++) {
			temp[i] = data[i];
		}
		data = temp;
	}
	
	
	public boolean add(T arg0) {
		if (!this.contains(arg0)) {
			if (size() == data.length) {
				this.grow();
			}
			data[size()] = arg0;
			this.size++;
			return true;
		}
		return false;
	}
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		if (this.containsAll(arg0)) {
			return false;
		}
		for (T t : arg0) {
			this.add(t);
		}
		return true;
	}
	
	
	public void clear() {
		if (this.isEmpty()) {
			return;
		}
		for (int i = 0; i < size(); i++) {
			data[i] = null;
		}
		this.size = 0;	
	}
	
	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size(); i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean containsAll(Collection<?> arg0) {
		if (arg0.size() > size()) {
			return false;
		}
		for (Object o : arg0) {
			if (!this.contains(o)) {
				return false;
			}
		}
		return true;
	}
	
	
	public boolean isEmpty() {
		if (size() == 0) {
			for (int i = 0; i < size(); i++) {
				if (data[i] != null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}
	
	
	public boolean remove(Object arg0) {
		if (this.contains(arg0)) {
			for (int i = 0; i < size(); i++) {
				if (data[i].equals(arg0)) {
					data[i] = null;
					for (int j = i; j < size() - 1; j++) {
						data[j] = data[j + 1];
					}
					size--;
					return true;
				}
			}
		}
		return false;
	}
	
	
	public boolean removeAll(Collection<?> arg0) {
		boolean b = false;
		for (Object o : arg0) {
			if (this.contains(o)) {
				this.remove(o);
				if (!b) {
					b = true;
				}
			}
		}
		return b;
	}
	
	
	public boolean retainAll(Collection<?> arg0) {
		boolean b = false;
		Iterator i = (ArrayCollectionIterator) iterator();
		while (i.hasNext()) {
			if (!arg0.contains(i.next())) {
				i.remove();
				if (!b) {
					b = true;
				}
			}
		}
		return b;
	}
	
	
	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		Object[] temp = new Object[size];
		for (int i = 0; i < size; i++) {
			temp[i] = data[i];
		}
		return temp;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sortedList = new ArrayList<T>();
		sortedList.addAll(this);
		for (int i = 0; i < size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size(); j++) {
				if (cmp.compare(sortedList.get(j), sortedList.get(minIndex)) < 0) {
					minIndex = j;
				}
			}
			T temp = sortedList.get(i);
			sortedList.set(i, sortedList.get(minIndex));
			sortedList.set(minIndex, temp);
		}
		return sortedList;
	}
	
	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int nextIndex = 0;
		private boolean legalRemove = false;
		public ArrayCollectionIterator()
		{
			
		}
		
		
		public boolean hasNext() {
			return nextIndex < size;
		}
		
		
		public T next() {
			if (data[nextIndex] == null) {
				throw new NoSuchElementException();
			}
			legalRemove = true;
			return data[nextIndex++];
		}
		
		
		public void remove() {
			if (!legalRemove) {
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(data[--nextIndex]);
			if (nextIndex != 0) {
				nextIndex--;
			}
			legalRemove = false;
		}

	}

}
