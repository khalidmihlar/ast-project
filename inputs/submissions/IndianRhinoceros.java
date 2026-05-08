//comments: 50
//comment length: 4541
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

		T temp[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i]; 
		}
		data = temp;

	}

	

	public boolean add(T arg0) {
		if (data.length == size) { 
			grow();
		}
		if (this.contains(arg0)) { 
			return false;

		}
		data[size] = arg0; 
		size++;
		return true;

	}

	

	public boolean addAll(Collection<? extends T> arg0) {
		boolean flip = false;
		for (T item : arg0) {
			if (add(item) != false) { 
				flip = true;
			}
		}
		return flip;
	}

	

	public void clear() {
		for (int i = 0; i < size; i++) { 
			this.data[i] = null; 
		}
		size = 0; 
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size(); i++) {
			if (this.data[i].equals(arg0)) { 
												
				return true;
			}
		}
		return false;
	}

	

	public boolean containsAll(Collection<?> arg0) {
		boolean flip = true;
		for (Object item : arg0) {
			if (this.contains(item) != false) { 
				flip = true;
			} else {
				return false;
			}
		}
		return flip;
	}

	

	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	

	public Iterator<T> iterator() {
		ArrayCollectionIterator itr = new ArrayCollectionIterator();
		return itr;
	}

	

	public boolean remove(Object arg0) {
		if (this.contains(arg0) != true) { 
			return false;
		}
		int removeIndex = 0; 
		for (int i = 0; i < size(); i++) {
			if (data[i] == arg0) {
				removeIndex = i;
			}
		}
		for (int j = removeIndex; j < size(); j++) { 
														
			data[j] = data[j + 1];
		}
		size--;
		return true;
	}

	

	public boolean removeAll(Collection<?> arg0) {
		boolean flip = false;
		for (Object collection : arg0) {
			if (remove(collection) != false) { 
				flip = true;
			}
		}
		return flip;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean flip = false;
		Iterator<?> itr = this.iterator();
		while (itr.hasNext()) { 
			Object item = itr.next(); 
			if (arg0.contains(item) != true) {
				itr.remove();
				flip = true;
			}
		}
		return flip;
	}

	public int size() {
		return size; 
	}

	
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		T newData[] = (T[]) new Object[size()]; 
		for (int i = 0; i < size(); i++) {
			newData[i] = this.data[i];
		}
		return newData;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		for (int i = 0; i < this.size; i++) {
			list.add(this.data[i]); 
		}
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

	
	private class ArrayCollectionIterator implements Iterator<T> {

		private int nextIdx;
		private boolean testCallNext;

		public ArrayCollectionIterator() {
			nextIdx = 0;
		}

		
		public boolean hasNext() {
			if (nextIdx < size) {
				return true;
			}
			return false;
		}

		
		public T next() {
			if (nextIdx > size) {
				throw new NoSuchElementException();
			}
			testCallNext = true;
			return data[nextIdx++];
		}

		
		public void remove() {
			if (testCallNext == false) { 
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(data[nextIdx-1]);
			nextIdx--; 
			testCallNext = false;
			 
		}

	}

}
