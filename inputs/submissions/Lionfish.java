//comments: 49
//comment length: 5494
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
		
		
		T temp[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++)
			temp[i] = data[i];

		data = temp;
	}

	
	public boolean add(T arg0) {

		
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				if (data[i].equals(arg0))
					return false;
			}
		}

		
		if (size == data.length)
			grow();

		data[size] = arg0;
		size++;

		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {

		boolean atLeastOneElementAdded = false;
		for (T t : arg0) {
			if (!contains(t)) {
				add(t);
				atLeastOneElementAdded = true;
			}
		}

		return atLeastOneElementAdded;
	}

	
	public void clear() {

		
		for (int index = 0; index < size; index++)
			data[index] = null;

		
		size = 0;
	}

	
	public boolean contains(Object arg0) {

		
		
		for (int index = 0; index < size; index++) {
			if (arg0.equals(data[index]))
				return true;
		}

		return false;

	}

	
	public boolean containsAll(Collection<?> arg0) {

		for (Object o : arg0) {
			if (!contains(o))
				return false;
		}

		return true;
	}

	
	public boolean isEmpty() {
		return (size == 0);
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		boolean itemWasRemoved = false;

		
		if (!contains(arg0))
			return false;

		
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				index = i;
				break;
			}

		}

		
		if (index == size - 1) {
			itemWasRemoved = true;
		}

		
		else if (index == 0) {
			for (int i = 0; i < size - 1; i++) {
				data[i] = data[i + 1];
			}
			itemWasRemoved = true;
		}

		
		else {
			for (int i = index; i < size - 1; i++) {
				data[i] = data[i + 1];
			}
			itemWasRemoved = true;
		}

		
		data[size - 1] = null;
		size--;

		return itemWasRemoved;
	}

	
	public boolean removeAll(Collection<?> arg0) {

		boolean elementWasRemoved = false;
		for (Object obj : arg0) {
			if (contains(obj)) {
				remove(obj);
				elementWasRemoved = true;
			}
		}

		return elementWasRemoved;
	}

	
	public boolean retainAll(Collection<?> arg0) {

		boolean itemWasRemoved = false;
		Iterator<?> iter = this.iterator();
		Object item;
		boolean needToRemoveItem;
		while (iter.hasNext()) {
			needToRemoveItem = true;
			item = iter.next();
			for (Object o : arg0) {
				if (item.equals(o)) {
					needToRemoveItem = false;
					break;
				}
			}
			if (needToRemoveItem) {
				iter.remove();
				itemWasRemoved = true;
			}
		}
		return itemWasRemoved;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] arr = new Object[data.length];
		for (int i = 0; i < size; i++)
			arr[i] = data[i];

		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();

		for (int i = 0; i < size; i++) {
			list.add(data[i]);
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
		private int nextIndex;
		private boolean canCallRemove;

		public ArrayCollectionIterator() {
			nextIndex = 0;
			canCallRemove = false;
		}

		
		public boolean hasNext() {

			
			
			return nextIndex < size;
		}

		
		public T next() {
			
			if (!hasNext())
				throw new NoSuchElementException();

			canCallRemove = true;
			return data[nextIndex++];
		}

		
		public void remove() {

			
			if (!canCallRemove)
				throw new IllegalStateException();

			
			if (nextIndex == 0)
				throw new IllegalStateException();

			
			
			if (canCallRemove)
				ArrayCollection.this.remove(data[nextIndex - 1]);

			
			nextIndex--;

			canCallRemove = false;
		}
	}
}
