//comments: 26
//comment length: 2174

package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
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
		
		
		
		T doubleData[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			doubleData[i] = data[i];
		}
		data = doubleData;
	}

	public boolean add(T arg0) {
		
		if (size == data.length) {
			grow();
		}
		if (!contains(arg0)) {
			data[size] = arg0;
			size++;
			return true;
		}
		return false;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		
		int sizeChange = size;
		for (T i : arg0) {
			add(i);
		}
		return size != sizeChange;
	}

	public void clear() {
		

		data = (T[]) new Object[10];
		size = 0;
	}

	public boolean contains(Object arg0) {
		
		for (int i = 0; i < this.size(); i++) {
			if (this.data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		
		for (Object i : arg0) {
			if (!contains(i)) {
				return false;
			}
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
		
		boolean removeBool = false;
		if (!contains(arg0))
			return removeBool;

		int locate = 0;
		for (int i = 0; i < size; i++) {
			{
				if (contains(data[i])) {
					locate = i;
				}
			}
		}
		for (int j = locate; j < size; j++) {
			data[j] = data[j + 1];
			removeBool = true;
		}
		size--;
		return removeBool;
	}

	public boolean removeAll(Collection<?> arg0) {

		int counter = 0;
		for (Object i : arg0) {
			if (contains(i)) {
				remove(i);
				counter++;
			}
		}
		if (counter != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean retainAll(Collection<?> arg0) {

		boolean retainBool = false;

		for (int i = 0; i < size; i++) {
			Object l = this.data[i];
			if (!arg0.contains(l)) {
				remove(l);
				retainBool = true;
			}
		}
		return retainBool;
	}

	public int size() {
		return size;
	}

	public Object[] toArray() {
		
		Object[] objectArray = new Object[size];
		for (int i = 0; i < size; i++) {
			objectArray[i] = data[i];
		}
		return objectArray;
	}

	
	public <T> T[] toArray(T[] arg0) {

		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		
		for (int i = 0; i < list.size() -1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size() -1; j++) 
				
					minIndex = j;
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
		return list;
	}

	private class ArrayCollectionIterator implements Iterator<T> {

		private int cursor = 0;
		public int backCursor = cursor - 1;
		boolean removeNext = false;

		public ArrayCollectionIterator() {
			
		}

		public boolean hasNext() {

			return (cursor < ArrayCollection.this.size() && data[cursor] != null);

		}
		
		public T next() {
			if (cursor >= size) {
				removeNext = false;
				throw new NoSuchElementException();
			}
			removeNext = true;
			return data[cursor++];
		}

		public void remove() {

			if (cursor < 0 || removeNext == false || cursor>= size) {
				throw new IllegalStateException();
			}

			ArrayCollection.this.remove(cursor - 1);
			cursor = backCursor;
			removeNext = false;
		}
	}
}
