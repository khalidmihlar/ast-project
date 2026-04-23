//comments: 97
//comment length: 6796
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
	public ArrayCollection() {
		
		
		size = 0;
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		T[] newArray = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			newArray[i] = data[i];
		}
		data = newArray;
	}

	
	public boolean add(T newItem) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(newItem)) { 
				return false;
			}
		}
		if (data.length == size) { 
			this.grow();
		}

		data[size] = newItem; 
		size++; 
		return true;
	}

	
	public boolean addAll(Collection<? extends T> otherCollection) {
		boolean wereItemsAdded = false; 
		for (T i : otherCollection) { 
			if (this.add(i))
				wereItemsAdded = true; 
		}

		return wereItemsAdded; 
	}

	
	public void clear() {
		@SuppressWarnings("unchecked")
		T[] newArray = (T[]) new Object[10]; 
		data = newArray; 
		size = 0; 
	}

	
	public boolean contains(Object item) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(item)) 
				return true;
		}
		return false; 
	}

	
	public boolean containsAll(Collection<?> otherCollection) {
		for (Object i : otherCollection) {
			if (!this.contains(i)) 
									
									
				return false;
		}
		return true; 
	}

	
	public boolean isEmpty() {
		if (this.size == 0)
			return true;
		return false;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}
	
	
	public boolean remove(Object item) {
		for (int i = 0; i < size; i++) { 
			if (data[i].equals(item)) { 
				for (int j = i; j < size - 1; j++) { 
													
													
					data[j] = data[j + 1];
				}
				data[size] = null; 
								
				size--; 
				return true;
			}
		}
		return false; 
	}
	
	
	public boolean removeAll(Collection<?> otherCollection) {
		boolean hasBeenRemoved = false; 
		for (Object i : otherCollection) {
			if (this.contains(i)) { 
									
				this.remove(i); 
				hasBeenRemoved = true; 
										
			}
		}
		return hasBeenRemoved;
	}

	
	public boolean retainAll(Collection<?> otherCollection) {
		boolean hasBeenRemoved = false; 
		Iterator<T> itr = this.iterator(); 
										
										
		T temp; 
				
		while (itr.hasNext()) {
			temp = itr.next(); 
			if (!otherCollection.contains(temp)) { 
												
				itr.remove(); 
				hasBeenRemoved = true; 
									
			}
		}
		return hasBeenRemoved;
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

	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> sortedList = new ArrayList<T>(); 
		sortedList.addAll(this); 
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
		private int nextIndex; 
		public boolean legalToRemove; 
									
									

		public ArrayCollectionIterator() {
			nextIndex = 0; 
			legalToRemove = false; 
		}

		
		public boolean hasNext() {
			return nextIndex < size;
		}

		
		public T next() {
			if (!this.hasNext()) { 
				throw new NoSuchElementException(); 
			}
			legalToRemove = true; 
			return data[nextIndex++]; 
		}

		
		public void remove() {
			if (legalToRemove == false) 
				throw new IllegalStateException(); 
			ArrayCollection.this.remove(data[--nextIndex]); 
															
															
															
			legalToRemove = false; 
		}
	}
}