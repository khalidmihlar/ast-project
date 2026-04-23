//comments: 45
//comment length: 4675
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
		
		
		

		T[] newData = (T[]) new Object[data.length * 2];

		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}

		this.data = newData;
	}

	public boolean add(T arg0) {
		

		
		if (this.contains(arg0)) {
			return false;
		}

		if (size == data.length) {
			grow();
		}

		data[size++] = arg0;

		return true;

	}

	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean added = false;
		for (T item : arg0) {
			added = this.add(item);
		}

		return added;
	}

	public void clear() {
		
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
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
		
		
		for (Object item : arg0) {
			if (!this.contains(item)) {
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
				if (i == size - 1) {
					data[i] = null;
				}
				for (int j = i; j < size - 1; j++) {
					data[j] = data[j + 1];
					data[j + 1] = null;
				}
				size--;
				return true;
			}
		}
		return false;
	}

	public boolean removeAll(Collection<?> arg0) {
		
		

		boolean remove = false;

		Iterator<T> i = this.iterator();

		while (i.hasNext()) {
			T element = i.next();
			if (arg0.contains(element)) {
				i.remove();
				remove = true;
			}
		}
		return remove;
	}

	public boolean retainAll(Collection<?> arg0) {
		
		

		return true;
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

	private static <T> void sort(ArrayList<T> list, Comparator<? super T> c) {
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		
		ArrayList<T> result = new ArrayList<T>();
		for (T t : this.data) {
			result.add(t);
		}
		sort(result, cmp);

		return result;
	}

	private class ArrayCollectionIterator implements Iterator<T> {

		int index;
		boolean nextOneRemove;

		public ArrayCollectionIterator() {
			
			index = -1;
			nextOneRemove = false;
		}

		public boolean hasNext() {
			
			
			if (data.length > index + 1 && data[index + 1] != null) {
				return true;
			}
			return false;
		}

		public T next() {
			
			
			if (hasNext()) {
				nextOneRemove = false;
				return data[++index];

			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			
			
			if (!nextOneRemove) {
				nextOneRemove = true;
				ArrayCollection.this.remove(data[index--]);
			} else {
				throw new IllegalStateException();
			}

		}

	}

}
