//comments: 29
//comment length: 4504
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
		T temp[] = (T[]) new Object[this.size() * 2];
		for (int i = 0; i < this.size(); i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	
	public boolean add(T arg0) {
		for (int i = 0; i < this.size(); i++) {
			if (arg0.equals(data[i]))
				return false;
		}
		if (data.length == size) {
			this.grow();
		}
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		Iterator<? extends T> itr = arg0.iterator();
		int check = size;
		while (itr.hasNext()) {
			this.add(itr.next());
		}
		if (size - check > 0) {
			return true;
		}
		return false;
	}

	
	public void clear() {
		for (int i = 0; i < data.length; i++)
			data[i] = null;
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < data.length; i++) {
			if (arg0.equals(data[i])) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		Iterator<?> itr = arg0.iterator();
		int check = 0;
		while (itr.hasNext())
			if (this.contains(itr.next())) {
				check++;
			}
		if (check == arg0.size() && arg0.size() > 0)
			return true;
		return false;
	}

	
	public boolean isEmpty() {
		if (size != 0) {
			return false;
		}
		return true;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i] == arg0) {
				for (int j = i; j < size - 1; j++) {
					data[j] = data[j + 1];
				}
				data[size - 1] = null;
				size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		int check = size;
		Object[] temparray = arg0.toArray();
		for (int i = 0; i < temparray.length; i++) {
			this.remove(temparray[i]);
		}
		if (check > size) {
			return true;
		}
		return false;

	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<?> itr = this.iterator();
		int check = size;
		while (itr.hasNext()) {
			
			if (!arg0.contains(itr.next())) {
				itr.remove();
				check++;
			}
			
		}
		if (check > size)
			return true;
		return false;

	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] arrayCollection = new Object[size];
		for (int i = 0; i < size; i++) {
			arrayCollection[i] = data[i];
		}
		return arrayCollection;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> collection = new ArrayList<T>();
		collection.addAll(this);
		sort(collection, cmp);
		return collection;
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

	
	private class ArrayCollectionIterator implements Iterator<T> {

		private int nextElement = 0;
		private boolean nextCall = false; 

		public ArrayCollectionIterator() {
		}

		
		public boolean hasNext() {
			if (data[nextElement] == null) {
				return false;
			}
			return true;
		}

		
		public T next() {
			if (data[nextElement] == null) {
				throw new NoSuchElementException();
			}
			nextCall = true;
			return data[nextElement++];
		}

		
		public void remove() {
			if (!nextCall) {
				throw new IllegalStateException();
			}
			int elementToRemove = nextElement - 1;
			data[elementToRemove] = data[nextElement];
			for (int i = nextElement; i < size; i++) {
				data[i] = data[i + 1];

			}
			size--;
			nextCall = false;
		}

	}

}
