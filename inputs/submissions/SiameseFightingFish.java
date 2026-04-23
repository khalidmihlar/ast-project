//comments: 5
//comment length: 1356

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
		T[] temp = (T[]) new Object[data.length * 2];
		for (int i = 0; i < temp.length / 2; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	public boolean add(T arg0) {
		if (size == data.length)
			grow();
		if (contains(arg0))
			return false;
		data[size] = arg0;
		size++;
		return true;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		for (T elements : arg0)
			if (add(elements))
				return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		T[] temp = (T[]) new Object[0];
		data = temp;
	}

	public boolean contains(Object arg0) {
		for (T elements : data) {
			if (arg0.equals(elements)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		for (Object elements : arg0) {
			if (!contains(elements)) {
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
		int change = 0;
		for (Object elements : arg0) {
			if (contains(elements)) {
				remove(elements);
				change++;
			}
		}
		if (change != 0)
			return true;
		return false;
	}

	public boolean retainAll(Collection<?> arg0) {
		Iterator<?> something = new ArrayCollectionIterator();
		int sum = 0;
		while (something.hasNext()) {
			if (!arg0.contains(something.next())) {
				something.remove();
				sum++;
			}
		}
		if (sum != 0)
			return true;
		return false;
	}

	public int size() {
		return this.size;
	}

	public Object[] toArray() {
		Object[] array = new Object[size];
		for (int i = 0; i < size; i++) {
			array[i] = data[i];
		}
		return array;
	}
	

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>(this);

		for (int i = 0; i < size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare(list.get(j), list.get(minIndex)) < 0) {
					minIndex = j;
				}
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
		return list;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int nextIndex;

		private boolean canRemove;

		public ArrayCollectionIterator() {
			nextIndex = 0;
			canRemove = false;
		}

		public boolean hasNext() {
			return nextIndex < size;
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			canRemove = true;
			return data[nextIndex++];
		}

		public void remove() {
			if (!canRemove)
				throw new IllegalStateException();
			nextIndex--;
			canRemove = false;
			ArrayCollection.this.remove(data[nextIndex]);
		}

	}

}
