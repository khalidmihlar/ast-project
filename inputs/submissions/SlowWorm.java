//comments: 26
//comment length: 3736
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
		T newArr[] = (T[]) new Object[size * 2];
		for(int i = 0; i < data.length; i++) {
			newArr[i] = data[i];
		}
		data = newArr;
		
	}

	
	public boolean add(T arg0) {
		if(size == data.length) {
			grow();
		}
		
		if(this.contains(arg0)) {
			return false;
		}
		data[size] = arg0;
		size++;
		return true;
	}
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean added = false;
		for(T element : arg0) {
			if(this.add(element)) {
				added = true;
			}
		}
		return added;
	}

	
	public void clear() {
		size = 0;
	}
	
	
	public boolean contains(Object arg0) {
		for(int i = 0; i < size; i++) {
			if(arg0.equals(data[i])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAll(Collection<?> arg0) {
		for(Object element : arg0) {
			if(!this.contains(element)) {
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

	
	public boolean remove(Object arg0) {
		if (!this.contains(arg0))
			return false;

		Iterator<T> iterator = this.iterator();
			while (iterator.hasNext()) {
				if (iterator.next().equals(arg0)){
					iterator.remove();
					return true;
				}
			}
			return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean removed = false;
			for(Object element : arg0)
				if(this.remove(element))
					removed = true;
		return removed;
	}
	
	
	public boolean retainAll(Collection<?> arg0) {
		if(this.size == 0) { return false; }
		boolean removed = false;
		Iterator<T> iter = this.iterator();
		while(iter.hasNext()) {
			if(!arg0.contains(iter.next())) {
				iter.remove();
				removed = true;
			}
		}
		return removed;
	}

	
	public int size() {
		return this.size;
	}
	
	
	public Object[] toArray() {
		Object arr[] = new Object[size];
		for(int i = 0; i < size; i++) {
			arr[i] = data[i];
		}
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sorted = new ArrayList<T>();
		for (int i = 0; i < size; i++)
			sorted.add(data[i]);

		for (int i = 0; i < sorted.size(); i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < sorted.size(); j++)
				if (cmp.compare(sorted.get(j), sorted.get(minIndex)) < 0)
					minIndex = j;
			T temp = sorted.get(i);
			sorted.set(i, sorted.get(minIndex));
			sorted.set(minIndex, temp);
		  }
		return sorted;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		int pointer = 0;
		boolean legalToRemove = false;
		public ArrayCollectionIterator()
		{
		}
		
		
		public boolean hasNext() {
			return !(pointer == size);
		}
		
		
		public T next() {
			if(!this.hasNext()) {
				throw new NoSuchElementException();
			}
			pointer++;
			legalToRemove = true;
			return data[pointer - 1];
		}
		
		
		public void remove() {
			if(!legalToRemove) {
				throw new IllegalStateException();
			}
			
			pointer--;
			for(int i = pointer; i < size + 1; i++) {
				data[i] = data[i + 1];
			}
			size--;
		}

	}

}
