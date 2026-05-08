//comments: 30
//comment length: 4166
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
		T[] newArrayCollection = (T[]) new Object[(data.length * 2) + 1];

		for (int i = 0; i < data.length; i++) {

			newArrayCollection[i] = data[i];

		}
		data = newArrayCollection;
		

	}

	

	public boolean add(T arg0) {
		if (size == data.length) {
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
		boolean booleanHolder = false;
		for (T Item : arg0) {
			if (this.add(Item)) {

				booleanHolder = true;
			}
		}

		return booleanHolder;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		data = (T[]) new Object[10];
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

		boolean holder = false;

		for (Object Item : arg0) {
			if (this.contains(Item)) {

				holder = true;
			} else {
				return false;
			}
		}

		return holder;
	}

	

	public boolean isEmpty() {
		return size == 0;
	}

	

	public Iterator<T> iterator() {

		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {

		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {

				for (int j = i; j < size; j++) {
					data[j] = data[j + 1];

				}
				size--;
				return true;
			}
		}

		return false;

	}

	

	public boolean removeAll(Collection<?> arg0) {

		boolean holder = false;
		for (Object Item : arg0) {
			if (this.remove(Item)) {
				holder = true;
			}
		}
		return holder;
	}

	
	public boolean retainAll(Collection<?> arg0) {

		boolean holder = false;
		Iterator<T> i = this.iterator();
		while (i.hasNext()) {
			T element = i.next();
			if (!arg0.contains(element)) {

				i.remove();

				holder = true;

			}
		}

		return holder;
	}

	

	public int size() {

		return size;
	}

	
	@SuppressWarnings("unchecked")
	public Object[] toArray() {

		Object[] arr = (T[]) new Object[size()];
		for (int i = 0; i < size(); i++) {
			arr[i] = data[i];
		}

		return arr;

	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {

		ArrayList<T> list = new ArrayList<T>();

		for (int k = 0; k < size; k++) {
			list.add(this.data[k]);
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

		

		public ArrayCollectionIterator() {
			nextIdx = 0;
		}

		
		public boolean hasNext() {

			return nextIdx < size;
		}

		
		public T next() {

			if (nextIdx > size) {
				throw new NoSuchElementException();
			}
			return data[nextIdx++];

		}

		

		public void remove() {
			ArrayCollection.this.remove(data[nextIdx]);
		}

	}

	@Override

	public <T> T[] toArray(T[] arg0) {

		return null;
	}
}