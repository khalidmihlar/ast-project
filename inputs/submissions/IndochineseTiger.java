//comments: 56
//comment length: 6139
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
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

		T[] tempDataArr = (T[]) new Object[data.length * 2];

		for (int i = 0; i < data.length; i++) {
			tempDataArr[i] = data[i];
		}
		data = tempDataArr;
	}

	
	public boolean add(T arg0) {

		
		if (arg0 == null) {
			return false;
		}

		
		if (this.size() == data.length) {
			this.grow();
		}

		
		if (this.contains(arg0)) {
			return false;
		} else {
			data[size] = arg0;
			size++;
			return true;
		}
	}

	
	public boolean addAll(Collection<? extends T> arg0) {

		Iterator<? extends T> argIterator = arg0.iterator();
		T addObj;
		boolean hasAddedOne = false;

		
		while (argIterator.hasNext()) {
			addObj = argIterator.next();
			
			
			if (!this.contains(addObj)) {
				this.add(addObj);
				hasAddedOne = true;
			}
		}
		return hasAddedOne;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		T[] clearArray = (T[]) new Object[data.length];

		data = clearArray;
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		
		if (arg0 == null) {
			throw new IllegalArgumentException();
		}

		
		for (int i = 0; i < this.size; i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {

		
		
		
		if (!this.isEmpty() && arg0.isEmpty()) {
			return false;
		}

		Iterator<?> inputIterator = arg0.iterator();

		while (inputIterator.hasNext()) {
			if (this.contains(inputIterator.next())) {
				continue;
			}
			return false;
		}
		return true;
	}

	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		
		if (!this.contains(arg0)) {
			return false;
		}
		
		int removalIndex = 0;

		
		for (int i = 0; i < this.size; i++) {
			if (data[i] == arg0) {
				removalIndex = i;
			}
		}
		
		
		if (removalIndex == data.length - 1) {
			this.data[removalIndex] = null;
		}

		
		for (; removalIndex < data.length - 1; removalIndex++) {
			this.data[removalIndex] = this.data[removalIndex + 1];
		}
		
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {

		if (this.isEmpty()) {
			return false;
		}

		Iterator<T> arrCollectionIterator = this.iterator();
		Object removalObject;
		boolean hasRemovedOne = false;

		
		while (arrCollectionIterator.hasNext()) {
			removalObject = arrCollectionIterator.next();
			
			if (arg0.contains(removalObject)) {
				arrCollectionIterator.remove();
				hasRemovedOne = true;
			}
		}
		return hasRemovedOne;
	}

	
	public boolean retainAll(Collection<?> arg0) {

		if (this.isEmpty()) {
			return false;
		}

		Iterator<T> arrCollectionIterator = this.iterator();
		Object removalObject;
		boolean hasRemovedOne = false;

		
		while (arrCollectionIterator.hasNext()) {
			removalObject = arrCollectionIterator.next();
			
			if (!arg0.contains(removalObject)) {
				arrCollectionIterator.remove();
				hasRemovedOne = true;
			}
		}
		return hasRemovedOne;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		T[] newArray = (T[]) new Object[this.size];

		for (int i = 0; i < this.size; i++) {
			newArray[i] = this.data[i];
		}

		return newArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {

		
		ArrayList<T> arrList = new ArrayList<T>();
		
		
		if (!this.isEmpty()) {
			for (int i = 0; i < this.size; i++) {
				arrList.add(this.data[i]);
			}
		}

		
		selectionSort(arrList, cmp);

		return arrList;
	}
	
	
	private ArrayList<T> selectionSort(ArrayList<T> arrList, Comparator<? super T> cmp) {
		for (int i = 0; i < arrList.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < arrList.size(); j++)
				if (cmp.compare(arrList.get(j), arrList.get(minIndex)) < 0)
					minIndex = j;
			T temp = arrList.get(i);
			arrList.set(i, arrList.get(minIndex));
			arrList.set(minIndex, temp);
		}

		return arrList;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
		
		
		public ArrayCollectionIterator() {
		}

		private int index = 0;

		private boolean removeIsLegalState = false;

		
		public boolean hasNext() {
			return index < size;
		}

		
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			
			removeIsLegalState = true;
			return data[index++];
		}

		
		public void remove() {
			if (!removeIsLegalState) {
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(data[--index]);
			
			
			removeIsLegalState = false;
		}

	}

}
