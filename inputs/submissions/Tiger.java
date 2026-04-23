//comments: 37
//comment length: 4967
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
		
		
		T tmp_data[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < size; i++) {
			tmp_data[i] = data[i];
		}
		data = tmp_data;
	}

	
	public boolean add(T arg0) {
		
		if (size == this.data.length) {
			this.grow();
		}

		if (!this.contains(arg0)) {
			data[size++] = arg0;
			return true;
		}
		return false;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean hasAdded = false;

		for (T item : arg0) {
			if (this.add(item)) {
				hasAdded = true;
			}
		}
		return hasAdded;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		data = (T[]) new Object[10];
		size = 0;
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
		if (arg0.size() < 1) {
			return true;
		}
		for (Object item : arg0) {
			if (!this.contains(item)) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null) {
				return false;
			}
		}
		return true;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		Iterator<T> itr = iterator();
		int idx = 0;

		while (itr.hasNext()) {
			idx++;

			if (itr.next().equals(arg0)) {
				itr.remove();

				shiftArray(idx - 1);

				size--;
				return true;
			}
		}

		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		if (arg0.size() < 1) {
			return false;
		}

		boolean didRemoveValue = false;

		for (Object item : arg0) {
			if (this.remove(item)) {
				didRemoveValue = true;
			}
		}

		return didRemoveValue;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		
		if (this.size < 1) {
			return false;
		}

		if (arg0.size() < 1) {
			this.clear();
			return true;
		}

		boolean didRemoveValue = false;

		ArrayList<T> matches = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			if (!arg0.contains(data[i])) {
				matches.add(data[i]);
				didRemoveValue = true;
			}
		}

		this.removeAll(matches);

		return didRemoveValue;
	}

	
	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		Object[] arr = new Object[this.size];

		for (int i = 0; i < this.size; i++) {
			arr[i] = data[i];
		}

		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {

		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> ACCopy = new ArrayList<T>();

		for (int i = 0; i < size; i++) {
			ACCopy.add(data[i]);
		}

		sort(ACCopy, cmp);

		return ACCopy;
	}

	
	private void shiftArray(int nullIndex) {
		for (int i = nullIndex; i < (size - 1); i++) {
			data[i] = data[i + 1];
		}

		data[size - 1] = null;
	}

	private class ArrayCollectionIterator implements Iterator<T> {

		int nextIdx;
		boolean isRemoveable;

		
		public ArrayCollectionIterator() {
			this.nextIdx = 0;
			this.isRemoveable = false;
		}

		
		public boolean hasNext() {
			return nextIdx < size;
		}

		
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			this.isRemoveable = true;
			return data[nextIdx++];
		}

		public void remove() {
			if (!isRemoveable) {
				throw new IllegalStateException();
			}

			data[nextIdx - 1] = null;
			this.isRemoveable = false;
		}

	}

	
	private static <T> void sort(ArrayList<T> ACCopy, Comparator<? super T> cmp) {
		for (int i = 0; i < ACCopy.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < ACCopy.size(); j++)
				if (cmp.compare(ACCopy.get(j), ACCopy.get(minIndex)) < 0)
					minIndex = j;
			T temp = ACCopy.get(i);
			ACCopy.set(i, ACCopy.get(minIndex));
			ACCopy.set(minIndex, temp);
		}
	}

	protected class orderByInt implements Comparator<Integer> {





		
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2;
		}
	}

}
