//comments: 29
//comment length: 2544
package assignment3;

import java.util.ArrayList;
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
		T newData[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < size; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}

	
	public boolean add(T arg0) {
		if (!this.contains(arg0)) {
			if (size >= data.length) {
				this.grow();
			}
			data[size] = arg0;
			size++;
			return true;
		}
		return false;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean added = false;
		Iterator<? extends T> newIt = arg0.iterator();
		while (newIt.hasNext()) {
			if (this.add(newIt.next()))
				added = true;
		}
		return added;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		
		size = 0;
		data = (T[]) new Object[data.length];
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (arg0.equals(data[i]))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		Iterator<?> newIt = arg0.iterator();
		while (newIt.hasNext()) {
			if (!this.contains(newIt.next())) {
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

	
	public boolean remove(Object arg0) {

		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				for (int j = i; j < size - 1; j++) {
					data[j] = data[j + 1];
				}
				size--;
				return true;
			}
		}

		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {

		boolean removed = false;
		Iterator<?> newIt = arg0.iterator();
		while (newIt.hasNext()) {
			if (this.remove(newIt.next()))
				removed = true;

		}
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean retained = false;
		boolean newRet = false;
		Iterator<T> aIt = this.iterator();
		while (aIt.hasNext()) {
			T current = aIt.next();
			Iterator<?> newIt = arg0.iterator();
			while (newIt.hasNext()) {
				if (current.equals(newIt.next())) {
					
					retained = true;
					newRet = true;
				}
			}
			if (!newRet)
			{
				aIt.remove();
			}
			newRet = false;

		}
		return retained;
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

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		list.addAll(this);
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
		private int location;
		private boolean nextCalled;

		public ArrayCollectionIterator() {
			location = 0;
			nextCalled = false;
		}

		
		public boolean hasNext() {

			return location < size;
		}

		
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			} else {
				nextCalled = true;
				return data[location++];
			}
		}

		
		public void remove() {
			if (nextCalled) {
				for (int i = location; i < size; i++) {
					data[i - 1] = data[i];
				}
				location--;
				size--;
			} else {
				throw new IllegalStateException();
			}
		}

	}

}
