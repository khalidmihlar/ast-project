//comments: 57
//comment length: 5620
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	private T data[]; 
	private int size; 


	
	@SuppressWarnings("unchecked")
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		int tempSize = data.length * 2; 

		T tempArray[] = (T[]) new Object[tempSize];
		for (int i = 0; i < data.length; i++) { 
			tempArray[i] = data[i]; 
		}
		data = tempArray; 
	}

	
	public boolean add(T arg0) {
		if (arg0 == null)
			return false;
		if (contains(arg0)) 
			return false;
		if (size < data.length) { 
			data[size++] = arg0; 
		} else {
			grow();
			data[size++] = arg0;
		}
		return true;
	}
	
	public boolean addAll(Collection<? extends T> arg0) {
		Iterator<? extends T> iter = arg0.iterator();
		Boolean allAdded = false;
		
		while (iter.hasNext()) {
			T next = iter.next();
			if (add(next)) {
				if (allAdded == false)	
					allAdded = true;
			}
		}

		return allAdded;
	}

	

	public void clear() {
		size = 0;
		data = (T[]) new Object[10];
	}

	
	@Override
	public boolean contains(Object arg0) {
		if (arg0 == null)	
			return false;
		T newArg0 = (T) arg0;
		for (int i = 0; i < size; i++) {	
			if (newArg0.equals(data[i]))	
				return true;
		}

		return false;
	}
	
	public boolean containsAll(Collection<?> arg0) {
		Iterator<?> iter = arg0.iterator();

		while (iter.hasNext()) {			
			@SuppressWarnings("unchecked")	
			T next = (T) iter.next();	
			if (!contains(next))	
				return false;
		}

		return true;
	}

	
	public boolean isEmpty() {
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		if (arg0 == null)
			return false;
		Boolean itemPresent = false;
		T newArg0 = (T) arg0;
		int location = 0;
		
		for (int i = 0; i < size; i++) {	
			if (newArg0.equals(data[i])) {
				location = i;
				itemPresent = true;
				break;
			}
		}

		if (!itemPresent)	
			return false;

		for (int j = location; j < (size - 1); j++) { 
			data[j] = data[j + 1];
		}
		data[--size] = null; 

		return itemPresent;
	}
	
	

	public boolean removeAll(Collection<?> arg0) {
		Iterator<?> iter = (Iterator<?>) arg0.iterator();
		Boolean allRemoved = false;
		while (iter.hasNext()) {			
			T next = (T) iter.next();
			if (remove(next)) {				
				if (allRemoved == false)	
					allRemoved = true;
			}
		}

		return allRemoved;
	}

	
	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<T> iter = this.iterator();
		Boolean itemRemoved = false;
		while (iter.hasNext()) {		
			T next = iter.next();
			if (!arg0.contains(next)) {	
				iter.remove();
				itemRemoved = true;
			}
		}
		return itemRemoved;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] newArray = new Object[size];
		for (int i = 0; i < size; i++) {
			newArray[i] = data[i];
		}

		return newArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> sortedList = new ArrayList<T>();

		for (int i = 0; i < size; i++) {
			sortedList.add(data[i]);		
		}	

		for (int i = 0; i < sortedList.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < sortedList.size(); j++)				
				if (cmp.compare(sortedList.get(j), sortedList.get(minIndex)) < 0)	
					minIndex = j;													
			T temp = sortedList.get(i);
			sortedList.set(i, sortedList.get(minIndex));
			sortedList.set(minIndex, temp);
		}

		return sortedList; 
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {

		protected int current;
		protected Boolean removable;

		
		public ArrayCollectionIterator() {
			current = 0;
			removable = false;
		}

		
		public boolean hasNext() {
			return current < (size - 1);	
		}

		
		public T next() {
			if (!this.hasNext())
				throw new NoSuchElementException();
			removable = true;
			return data[current++];
		}

		
		public void remove() {
			if (removable == false)
				throw new IllegalStateException();	

			for (int j = current - 1; j < size - 1; j++) { 
				data[j] = data[j + 1];
			}
			data[--size] = null; 
			current--; 			
			removable = false; 
		}

	}

}
