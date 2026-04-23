//comments: 30
//comment length: 4378

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
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}

	
	public boolean add(T arg0) {
		for (T d : data) {
			if (d != null && d.equals(arg0)) {
				return false;
			}
		}
		if (size == data.length) {
			grow();
		}
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean check = false;
		for (T a : arg0) {
			if (add(a))
				check = true;
		}
		return check;
	}

	
	public void clear() {
		data = (T[]) new Object[10];
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for (T d : data) {
			if (d != null && d.equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		boolean check = true;
		for (Object e : arg0) {
			if (!contains(e)) {
				check = false;
			}
		}
		return check;
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
				data[size--] = null;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean check = false;
		for (Object e : arg0) {
			if (remove(e)) {
				check = true;
			}
		}
		return check;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<T> dataIt = iterator();
		Boolean check = false;

		while (dataIt.hasNext()) {
			T token = dataIt.next();
			Boolean removeItem = true;
			for (Object e : arg0) {
				if (token.equals(e)) {
					removeItem = false;
					break;
				}
			}
			if (removeItem) {
				dataIt.remove();
				check = true;
			}
		}
		return check;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		T arrayData[] = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			arrayData[i] = data[i];
		}
		return arrayData;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		for (T d : data) {
			if (d != null)
				list.add(d);
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
		private int index;
		private boolean removeLegal;

		
		public ArrayCollectionIterator() {
			index = 0;
			removeLegal = false;
		}

		
		public boolean hasNext() {
			return index < size;
		}

		
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			removeLegal = true;
			return data[index++];
		}

		
		public void remove() {
			if (!removeLegal) {
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(data[--index]);
			removeLegal = false;
		}

	}

}