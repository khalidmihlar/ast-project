//comments: 41
//comment length: 3630
package assignment3;

import java.lang.reflect.Array;
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

		if (contains(arg0) == false)
		{
			data[size] = arg0;
			size++;
			return true;
		}

		return false;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		Iterator<? extends T> arg0Iter = arg0.iterator();
		boolean flag = false;

		while (arg0Iter.hasNext()) {
			boolean attempt = this.add(arg0Iter.next());
														
			if (attempt == true) {
				flag = true;
			}
		}
		return flag;
	}

	public void clear() {
		for (int i = 0; i < data.length; i++) {
			if (isEmpty() == false)
			{
				remove(data[i]);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public boolean contains(Object arg0) {
		if (size == 0) {
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (data[i].equals((T) arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for (int i = 0; i < size - 1; i++) {
			if (arg0.contains(data[i])) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		for (int i = 0; i < data.length; i++)
			if (size == 0) {
				return true;
			}
		return false;
	}

	public boolean remove(Object arg0) {
		int token = 0;
		
		for (int idx = 0; idx < size - 1; idx++) {
			if (data[idx] == arg0) {
				data[idx] = null;
				token = idx;
			}
		}
		if(contains(arg0) == false)
		{
			return false;
		}
		
		
		for (int i = token; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean flag = false;
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			if (arg0.contains(iter.next())) {
				iter.remove();
				flag = true;
			}
		}
		return flag;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		@SuppressWarnings("rawtypes")
		ArrayCollection<?> token = new ArrayCollection();
		Iterator<?> arg0Iter = arg0.iterator();
		boolean flag = false;

		while (arg0Iter.hasNext()) {
			boolean attempt = this.contains(arg0Iter.next());
			if (attempt == true) {
				flag = true;
			}
		}
		if (flag == true) {
			return true;
		} else
			return false;
	}

	
	public int size() {
		return size;
	}

	
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		T[] arr = (T[]) new Object[data.length];
		for (int i = 0; i <= data.length - 1; i++) {
			arr[i] = data[i];
		}
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>(this);

		for (int i = 0; i < this.size(); i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < this.size(); j++) {
				if (cmp.compare(list.get(j), list.get(minIndex)) < 0) {
					minIndex = j;
				}
			}
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
		return list;
	}

	public String toString() {

		return null;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	private class ArrayCollectionIterator implements Iterator<T> {

		private int index;
		boolean flag;

		
		public ArrayCollectionIterator() {
			index = 0;
			flag = false;
		}

		public boolean hasNext() {
			if (index < data.length) {
				return true;
			} else
				return false;
		}

		public T next() {
			if (!(hasNext())) {
				throw new NoSuchElementException();
			} else
				flag = true;
			return data[index++];
		}

		public void remove() {
			
			if (!(hasNext())) {
				throw new IllegalStateException();
			}
			if (flag == true)
				ArrayCollection.this.remove(data[index]);
			flag = false;
		}
	}
}
