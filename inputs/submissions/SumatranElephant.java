//comments: 80
//comment length: 7989
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
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		
		
		

		T[] temp = (T[]) new Object[data.length * 2]; 

		for (int iter = 0; iter < data.length; iter++) {
			temp[iter] = data[iter];
		}
		data = temp;
	}

	
	public boolean add(T arg0) {
		
		
		if (size != 0) {
			for (int iter = 0; iter < data.length; iter++) {
				if (data[iter] != null && data[iter].equals(arg0))
					return false;
			}

			
			if (size == data.length) {
				grow();
			}
		}
		
		data[size++] = arg0;

		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		

		
		boolean returnResult = false;

		
		for (T iter : arg0) {
			if (add(iter))
				returnResult = true;
		}
		return returnResult;
	}

	
	public void clear() {
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		

		if (size == 0)
			return false;

		
		for (int iter = 0; iter < size; iter++) {
			if (arg0.equals(data[iter]))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		

		
		
		if (arg0.isEmpty())
			return true;

		
		boolean returnResult = true;

		
		for (Object iter : arg0) {
			if (!contains(iter)) {
				returnResult = false;
			}
		}

		return returnResult;
	}

	
	public boolean isEmpty() {
		
		return (size == 0);
	}

	
	public Iterator<T> iterator() {
		
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		

		
		if (size == 0)
			return false;

		
		if (contains(arg0)) {
			
			for (int iter = 0; iter <= size - 1; iter++) {
				if (data[iter].equals(arg0)) {

					
					if (iter == size - 1) {
						size = size - 1;
					}

					

					else {
						size = size - 1;
						for (int iter2 = iter; iter2 < size; iter2++) {

							data[iter2] = data[iter2 + 1];

						}
					}
					return true;
				}
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		

		
		if (size == 0)
			return false;

		
		boolean returnResult = false;

		
		for (Object iter : arg0) {
			if (remove(iter))
				returnResult = true;
		}

		return returnResult;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		if (arg0.size() == 0) {
			size = 0;
			return true;
		}
		
		if (size == 0 || arg0.size() == 0)
			return false;

		
		boolean returnResults = false;
		T temp;
		ArrayCollectionIterator retainIter = new ArrayCollectionIterator();
		
		
		while (retainIter.hasNext()) {
			temp = retainIter.next();
			if (arg0.contains(temp)) {
				continue;
			}
			retainIter.remove();
			returnResults = true;
		}

		return returnResults;

	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		
		
		Object[] passingArray = new Object[size];

		
		for (int iter = 0; iter < size; iter++) {
			passingArray[iter] = data[iter];
		}
		return passingArray;
	}

	
	public <T> T[] toArray(T[] arg0) {

		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {

		
		ArrayList<T> sorted = new ArrayList<T>();
		for (int iter = 0; iter < size; iter++) {
			sorted.add(data[iter]);
		}

		
		
		for (int i = 0; i < sorted.size() - 1; i++) {
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

	
	private class ArrayCollectionIterator implements Iterator<T> {

		private int iterator;
		private boolean hasNextBeenCalled;

		
		public ArrayCollectionIterator() {
			
			iterator = 0;
			hasNextBeenCalled = false;
		}

		
		public boolean hasNext() {
			

			

			if (iterator >= size)
				return false;
			return true;
		}

		
		public T next() throws NoSuchElementException {
			

			
			if (iterator > size)
				throw new NoSuchElementException();
			
			iterator++;

			
			hasNextBeenCalled = true;

			return data[iterator - 1];
		}

		
		public void remove() throws IllegalStateException {
			

			
			if (!hasNextBeenCalled)
				throw new IllegalStateException();
			hasNextBeenCalled = false;
			
			
			iterator--;
			
			ArrayCollection.this.remove(data[iterator]);

		}

	}

}
