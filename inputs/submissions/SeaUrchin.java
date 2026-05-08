//comments: 34
//comment length: 6411
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
		this.size = 0;
		
		
		this.data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {

		T tempData[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < size; i++) {
			tempData[i] = data[i];
		}
		this.data = tempData;
		
		
	}

	
	public boolean add(T item) {
		if (item == null) {
			return false;
		}
		if (contains(item)) {
			return false;
		}
		if (size == this.data.length) {
			grow();
		}
		this.data[size++] = item;

		return true;
	}

	
	public boolean addAll(Collection<? extends T> items) {

		boolean contains = false;
		for (T item : items) {
			if (add(item)) {
				contains = true;
			}
		}
		return contains;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {

		this.size = 0;
		T[] tempArr = (T[]) new Object[data.length];
		this.data = tempArr;

	}

	
	public boolean contains(Object item) {
		if (item == null) {
			return false;
		}
		@SuppressWarnings("unchecked")
		T element = (T) item;
		for (int i = 0; i < size; i++) {
			if (element.equals(data[i])) {
				return true;
			}
		}
		return false;
	}

	
	@SuppressWarnings("unchecked")
	public boolean containsAll(Collection<?> items) {
		boolean contains = true;
		for (Object item : items) {
			if (!contains((T) item)) {
				contains = false;
			}
		}
		return contains;
	}

	
	public boolean isEmpty() {
		return size == 0;
	}

	
	@SuppressWarnings("unchecked")
	public boolean remove(Object item) {
		if (contains((T) item)) {
			int index = findIndex((T) item);
			for (int indexNum = index; indexNum < size; indexNum++) {
				data[indexNum] = data[indexNum + 1];
			}
			data[size] = null;
			size--;
			return true;
		}
		return false;
	}

	
	private int findIndex(T item) {
		int num = 0;
		for (T element : data) {
			if (element.equals(item)) {
				return num;
			}
			num++;
		}
		return 0;
	}

	
	public boolean removeAll(Collection<?> items) {
		boolean contains = false;
		for (Object item : items) {
			if (remove(item)) {
				contains = true;
			}
		}
		return contains;
	}

	
	public boolean retainAll(Collection<?> items) {
		boolean hasChanged = false;
		Object[] copyData = (Object[]) data;
		ArrayList<Object> list = new ArrayList<>();
		for(int index = 0; index < size; index++) {
			if(!items.contains(copyData[index])) {
				list.add(copyData[index]);
			}
		}
		if(this.removeAll(list)) {
			hasChanged = true;
		}
		return hasChanged;
	}

	
	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		Object[] temp = new Object[size];
		for (int index = 0; index < size; index++) {
			temp[index] = data[index];
		}
		return temp;
	}

	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		if(cmp == null) {
			throw new NullPointerException("Need a real Comparator");
		}
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) this.toArray();
		ArrayList<T> list = new ArrayList<>(Arrays.asList(temp));
		return sort(list, cmp);
	}

	
	private ArrayList<T> sort(ArrayList<T> list, Comparator<? super T> cmp) {
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

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		
		int pointer;
		int integerBooleanNum;
		int sizeOfCurrentLength;
		T[] copy;
		
		public ArrayCollectionIterator() {
			
				pointer = 0;
				integerBooleanNum = -1;
				sizeOfCurrentLength = size;
				copy = data;
			
			
		}

		
		public boolean hasNext() {
			
			return pointer != sizeOfCurrentLength;
		}

		
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException("Too Far");
			}
			integerBooleanNum = pointer;
			return copy[pointer++];
		}
		
		
		private int findIndex(T item) {
			int num = 0;
			for (T element : data) {
				if (element.equals(item)) {
					return num;
				}
				num++;
			}
			return 0;
		}

		
		public void remove() {
			if(integerBooleanNum < 0) {
				throw new IllegalStateException("");
			}
			int index = findIndex(copy[pointer - 1]);
			for(int indexNum = index; indexNum < sizeOfCurrentLength; indexNum++) {
				data[indexNum] = data[indexNum + 1];
			}
			pointer--;
			size--;
			sizeOfCurrentLength--;
			data = copy;
			integerBooleanNum = -1;
		}

	}

}
