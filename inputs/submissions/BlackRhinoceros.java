//comments: 77
//comment length: 12551
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

		
		T tempData[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			tempData[i] = data[i];
		}

		
		
		data = tempData;
	}

	
	public boolean add(T input) {

		
		if (size >= data.length) {
			grow();
		}

		
		if (input == null) {
			return false;
		}

		
		if (contains(input)) {
			return false;
		}

		
		data[size] = input;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> input) {

		
		
		boolean addedObject = false;
		Iterator<? extends T> inputITR = input.iterator();

		
		
		while (inputITR.hasNext()) {
			T currentObject = (T) inputITR.next();
			if (add(currentObject)) {
				addedObject = true;
			}
		}

		
		return addedObject;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {

		
		data = (T[]) new Object[10];
		size = 0;
	}

	
	public boolean contains(Object input) {

		
		Iterator<? extends T> itr = this.iterator();
		while (itr.hasNext()) {
			if (itr.next().equals(input)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> input) {

		
		
		Iterator<?> inputITR = input.iterator();
		while (inputITR.hasNext()) {
			Object currentObject = inputITR.next();
			if (!(this.contains(currentObject))) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object input) {

		
		
		
		Iterator<? extends T> itr = this.iterator();
		while (itr.hasNext()) {
			T currentObject = (T) itr.next();
			if (currentObject.equals(input)) {
				itr.remove();
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> input) {

		
		
		Iterator<?> inputITR = input.iterator();
		boolean ifRemoved = false;
		while (inputITR.hasNext()) {
			Object currentObject = inputITR.next();
			if (remove(currentObject)) {
				ifRemoved = true;
			}
		}

		
		return ifRemoved;
	}

	
	public boolean retainAll(Collection<?> input) {

		
		
		boolean inBothArrays = false;
		boolean removedItems = false;

		
		
		
		Iterator<? extends T> currentITR = this.iterator();
		while (currentITR.hasNext()) {
			T currentObject = (T) currentITR.next();
			Iterator<?> inputITR = input.iterator();
			while (inputITR.hasNext()) {
				Object currentInputObject = inputITR.next();
				if (currentObject.equals(currentInputObject)) {
					inBothArrays = true;
				}
			}
			if (!inBothArrays) {
				currentITR.remove();
				removedItems = true;
			}
			inBothArrays = false;
		}
		return removedItems;
	}

	
	public int size() {
		return size;
	}

	
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		T dataToArray[] = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			dataToArray[i] = data[i];
		}
		return dataToArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {

		
		
		ArrayList<T> sortedList = new ArrayList<T>(this);
		for (int i = 0; i < sortedList.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < sortedList.size(); j++) {
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

	
	private class ArrayCollectionIterator implements Iterator<T> {

		
		private int indexInArrayCollection;

		
		
		private boolean removeLegal;

		
		public ArrayCollectionIterator() {
			indexInArrayCollection = 0;
			removeLegal = false;
		}

		
		public boolean hasNext() {
			if (indexInArrayCollection >= size) {
				return false;
			}
			return true;
		}

		
		public T next() throws NoSuchElementException {
			
			
			if (indexInArrayCollection >= size) {
				throw new NoSuchElementException();
			}

			
			removeLegal = true;

			
			
			return data[indexInArrayCollection++];
		}

		
		@SuppressWarnings("unchecked")
		public void remove() throws IllegalStateException {

			
			
			
			if (!removeLegal) {
				throw new IllegalStateException();
			}
			
			
			
			for (int i = indexInArrayCollection; i < size; i++) {
				data[i - 1] = data[i];
			}

			
			
			removeLegal = false;

			
			
			

			
			
			indexInArrayCollection--;
			size--;

		}

	}

}