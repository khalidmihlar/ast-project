//comments: 30
//comment length: 3111
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
		int length = data.length;
		length = length * 2;
		T[] temp = (T[]) new Object[length];
		for (int idx = 0; idx < data.length; idx++) {
			temp[idx] = data[idx];
		}
		data = temp;
	}

	
	public boolean add(T arg0) {
		if ((data.length - 2) <= size) {
			grow();
		}
		if (contains(arg0)) {
			return false;
		}
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean addedElement = false;
		Iterator<? extends T> iter = arg0.iterator();
		while (iter.hasNext()) {
			if (add(iter.next()))
				addedElement = true;
		}
		return addedElement;
	}

	
	public void clear() {
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		if (size == 0) {
			return false;
		}
		for (int index = 0; index < size; index++) {
			if (data[index].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		int incomeing = arg0.size();
		int comparedTo = this.size();
		if (incomeing > comparedTo) {
			return false;
		}
		boolean addedElement = true;
		Iterator<?> iter = arg0.iterator();
		while (iter.hasNext()) {
			if (!contains(iter.next()))
				addedElement = false;
		}
		return addedElement;
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
		for (int count = 0; count < size; count++) {
			if (data[count].equals(arg0)) {
				helpCycleOutRemove(count);
				size--;
				return true;
			}
		}
		return false;
	}

	
	private void helpCycleOutRemove(int counter) {
		int idx;
		for (idx = counter; idx < size - 1; idx++) {
			data[idx] = data[idx + 1];
		}
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean addedElement = false;
		
		Iterator<?> iter = arg0.iterator();
		while (iter.hasNext()) {
			if (remove(iter.next())) {
				addedElement = true;
			}
		}
		return addedElement;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean hasChnged = false;
		Iterator<?> iter = this.iterator();
		while (iter.hasNext()) {
			Object element = iter.next();
			if (!(arg0.contains(element))) {
				iter.remove();
				hasChnged = true;
			}
		}
		return hasChnged;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] arrTemp = new Object[size];
		for (int place = 0; place < size; place++) {
			arrTemp[place] = data[place];
		}
		return arrTemp;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> tempReturn = new ArrayList<>();
		
		for (int cnt = 0; cnt < size; cnt++) {
			tempReturn.add(data[cnt]);
		}
		int count;
		for (int index = 0; index < size; index++) {
			int minIndex = index;
			for (count = index + 1; count < size; count++) {
				if (cmp.compare(tempReturn.get(count), tempReturn.get(minIndex)) < 0) {
					minIndex = count;
				}
			}
			T tempLocVal = tempReturn.get(index);
			tempReturn.set(index, tempReturn.get(minIndex));
			tempReturn.set(minIndex, tempLocVal);
		}
		return tempReturn;
	}

	private class ArrayCollectionIterator implements Iterator<T> {

		int placeHolder;
		boolean NextHasBeenCalled;

		
		public ArrayCollectionIterator() {
			placeHolder = 0;
			NextHasBeenCalled = false;
		}

		
		public boolean hasNext() {
			if (size > placeHolder) {
				return true;
			}
			return false;
		}

		
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException("There must be a next element in order to preform this function.");
			}
			placeHolder++;
			NextHasBeenCalled = true;
			return data[placeHolder - 1];
		}

		
		public void remove() {
			if (!NextHasBeenCalled) {
				throw new IllegalStateException("You must call next before using this method");
			}
			placeHolder--;
			ArrayCollection.this.remove(data[placeHolder]);
			NextHasBeenCalled = false;
		}

	}

}
