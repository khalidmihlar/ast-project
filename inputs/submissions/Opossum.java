//comments: 50
//comment length: 4211
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
		
		T temp_data[] = (T[]) new Object[2 * data.length];
		
		
		for (int i = 0; i < data.length; i++) {
			temp_data[i] = data[i];
		}
		
		
		data = temp_data;
	}

	
	public boolean add(T arg0) {
		for (T element : data) {
			if (arg0.equals(element)) { 
				return false;
			}
		}
		
		if (size > (data.length - 1)) { 
			grow();
		}
		
		data[size] = arg0;
		
		size++;
		
		return true;
	}

	
	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean testPassed = false;
		
		for (T o: arg0) {
			
			if (o == null) 
				break;
			
			if(add(o)) testPassed = true;
			
		}
		
		return testPassed;
	}

	
	
	@SuppressWarnings("unchecked")
	public void clear() {
		data = (T[]) new Object[10]; 
		
		
		size = 0;
	}

	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (arg0.equals(data[i])) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean containsAll(Collection<?> arg0) {
		for (Object arg : arg0) {
			if (arg == null) { 
				break;
			}
			
			if (!(contains(arg))) {
				return false;
			}
		}
		
		return true;
	}

	
	
	public boolean isEmpty() {
		
		if (size == 0) return true;
		
		else return false;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		int tracker = -1;
		
		for (int i = 0; i < (size - 1); i++) {
			if (arg0.equals(data[i])) {
				tracker = i;
			}
		}
		
		if (tracker != -1) {
			for (int i = tracker; i < (size - 1); i++) {
				if ((i + 1) == size) { 
					data[i] = null;
					break;
				}
				
				data[i] = data[i + 1];
			}
			
			size--;
			
			return true;
		}
		
		return false;
	}

	public boolean removeAll(Collection<?> arg0) {

		boolean passedTest = false;
		
		for (Object arg : arg0) {
			if (arg == null) {  
				break;
			}
			
			if (this.remove(arg)) passedTest = true;
			
		}
		
		return passedTest;
	}

	
	@SuppressWarnings("unchecked")
	public boolean retainAll(Collection<?> arg0) {
		
		Iterator<?> iter = this.iterator();
		T temp[] = (T[]) new Object[data.length];
		int indexOfTemp = 0;
		boolean retain = false;
		boolean removedAnyElements = false;
		
		while (iter.hasNext()) {
			
			Object nextElement = iter.next();
			
			for (Object obj : arg0) {
				if (obj == null) {
					break;
				}
				
				retain = false;
				
				if (nextElement.equals(obj)) {
					retain = true;
					break;
				}


			}
			




			
			if (retain) { 
				
				temp[indexOfTemp] = (T) nextElement;
				indexOfTemp++;
				
			}
			else {
				removedAnyElements = true;
			}
			
		}
		
		data = temp;
		
		return removedAnyElements;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] arr = new Object[size];
		for (int i = 0; i < size; i++) {
			arr[i] = data[i];
		}
		
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> arr = new ArrayList<T>();
		
		for (int i = 0; i < (size - 1); i++) {
			arr.add(data[i]); 
		}
		
		for (int i = 0; i < arr.size(); i++) { 
			int j, minIndex; 
			
			for ( j = i+1, minIndex = i; j < (size - 1); j++) {
				if (cmp.compare(arr.get(j), arr.get(minIndex)) < 0) { 
					minIndex = j;
				}
				
			}
			T temp = arr.get(i); 
			arr.set(i, arr.get(minIndex)); 
			arr.set(minIndex, temp);
			
		}
		
		return arr;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		
		private int tracker;
		private boolean calledTwice;
		
		public ArrayCollectionIterator()
		{
			
			tracker = 0; 
			calledTwice = false; 
			
		}

		public boolean hasNext() {
			
			try {
				
				
				if (data[tracker] == null) {
					return false;
				}
				data[tracker] = data[tracker];
				calledTwice = false;
				return true; 
				
			} catch(ArrayIndexOutOfBoundsException e) {
				
				return false; 
			}
			
			
		}

		public T next() {
			
			
			if (hasNext()) return data[tracker++];
			
			
			else throw new NoSuchElementException();
			
			
		}

		public void remove() {
			
			if (tracker > 0 && !calledTwice) { 
				
				ArrayCollection.this.remove(data[tracker - 1]);
				calledTwice = true;
				
			}
			else throw new IllegalStateException(); 
			
		}

	}

}
