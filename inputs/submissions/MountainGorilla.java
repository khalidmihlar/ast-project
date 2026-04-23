//comments: 32
//comment length: 3630

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
		int newSize = data.length * 2;
		T[] tempData = (T[]) new Object[newSize];
		for (int i = 0; i < data.length; i++) {
			tempData[i] = data[i];
		}
		data = tempData;
	}

	
	public boolean add(T arg0) {
		if (size + 1 > data.length)
			this.grow();
		if (!(this.contains(arg0)) && arg0 != null) {
			data[size++] = arg0;
			return true;
		}
		return false;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		int tempSize = size;
		for (T element : arg0) {
			if (!(this.contains(element))) {
				add(element);
			}
		}
		return (size == tempSize);
	}

	
	public void clear() {
		
		T[] newData = (T[]) new Object[10];
		size = 0;
		data = newData;




	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for (Object element : arg0) {
			if (!(this.contains(element)) && !(element == null))
				return false;
		}
		return true;
	}

	
	public boolean isEmpty() {
		return (size == 0);
	}

	
	public Iterator<T> iterator() {
		return new <T>ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				for (int j = i + 1; j < size; j++) {
					data[i++] = data[j];
				}
				data[--size] = null;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		int sizeDupe = size;
		for (Object element : arg0) {
			remove(element);
		}
		return (size != sizeDupe);
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean sizeChange = false;
		int SizeAndTempIndex = 0;
		T[] newData = (T[]) new Object[10];
		for (int i = 0; i < size; i++) {
			if (arg0.contains(data[i]) && data[i] != null) {
				newData[SizeAndTempIndex++] = data[i];
				sizeChange = true;
			}

		}
		data = newData;
		size = SizeAndTempIndex;
		return sizeChange;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] collectionArray = new Object[size];
		for (int i = 0; i < size; i++) {
			collectionArray[i] = data[i];
		}
		return collectionArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> sorted = new <T>ArrayList();
		for (int i = 0; i < this.size(); i++) {
			sorted.add(data[i]);
		}
		for (int i = 0; i < sorted.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < sorted.size(); j++)
				if (cmp.compare(sorted.get(j), sorted.get(minIndex)) < 0)
					minIndex = j;
			T temp = sorted.get(minIndex);
			sorted.set(minIndex, sorted.get(i));
			sorted.set(i, temp);
		}
		return sorted;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int index = 0;
		private boolean legalToCallRemove = false;

		public ArrayCollectionIterator() {
			
		}

		
		public boolean hasNext() {
			return (index <= size);
		}

		
		public T next() {
			if (hasNext() == false)
				throw new NoSuchElementException();
			legalToCallRemove = true;
			return data[index++];
		}

		
		public void remove() {
			if (legalToCallRemove) {
				ArrayCollection.this.remove(data[--index]);
				legalToCallRemove = false;
			} else {
				throw new IllegalStateException();
			}
		}

	}

}
